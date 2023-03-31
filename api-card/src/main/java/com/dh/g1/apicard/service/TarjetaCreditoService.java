package com.dh.g1.apicard.service;

import com.dh.g1.apicard.controller.TarjetaCreditoController;
import com.dh.g1.apicard.exception.CardException;
import com.dh.g1.apicard.exception.MessageError;
import com.dh.g1.apicard.feign.IMarginServiceClient;
import com.dh.g1.apicard.feign.IWalletServiceClient;
import com.dh.g1.apicard.feign.model.Concept;
import com.dh.g1.apicard.model.MovimientosTarjetaCredito;
import com.dh.g1.apicard.model.TarjetaCredito;
import com.dh.g1.apicard.repository.IMovimientosTarjetaCreditoRepository;
import com.dh.g1.apicard.repository.ITarjetaCreditoRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class TarjetaCreditoService implements ITarjetaCreditoService{

    private final ITarjetaCreditoRepository tarjetaCreditoRepository;
    private final IMovimientosTarjetaCreditoRepository movimientosTarjetaCreditoRepository;
    private final IMarginServiceClient marginServiceClient;

    private final IWalletServiceClient walletServiceClient;


    public TarjetaCreditoService(ITarjetaCreditoRepository tarjetaCreditoRepository, IMovimientosTarjetaCreditoRepository movimientosTarjetaCreditoRepository, IMovimientosTarjetaCreditoRepository movimientosTarjetaCreditoRepository1, IMarginServiceClient marginServiceClient, IWalletServiceClient walletServiceClient) {
        this.tarjetaCreditoRepository = tarjetaCreditoRepository;
        this.movimientosTarjetaCreditoRepository = movimientosTarjetaCreditoRepository1;
        this.marginServiceClient = marginServiceClient;
        this.walletServiceClient = walletServiceClient;
    }

    /*private BigDecimal findlimiteCalificado (String tipoDocumento, String nroDocumento ){
       BigDecimal limiteCalificado = marginServiceClient.calculateCalification("DU","ABC123").getTotalMargin();
       return limiteCalificado;
    }

    //@CircuitBreacker (name= “clientInscription”, fallbackMethod = “findCourseFallBack”)
    public void save () {
        tarjetaCreditoRepository.save(
                TarjetaCredito.builder()
                        .id(UUID.randomUUID().toString())
                        .numeroTarjeta("XYZ123")
                        .tipoDocumento("DU")
                        .numeroDocumento("ABC123")
                        .moneda("USD")
                        .limiteCalificado(new BigDecimal(121313))
                        .limiteConsumido(new BigDecimal(100000))
                        .limiteDisponible(new BigDecimal(400000))
                        .build()
        );
    }

    public List<TarjetaCredito> getAll(){
        return tarjetaCreditoRepository.findAll();
    }*/

    @Override
    public Optional<TarjetaCredito> findByTipoDocumentoAndNumeroDocumento(String tipoDocumento, String numeroDocumento) throws CardException {
        return Optional.ofNullable(tarjetaCreditoRepository.findByTipoDocumentoAndNumeroDocumento(tipoDocumento, numeroDocumento).orElseThrow(() -> new CardException(MessageError.CUSTOMER_NOT_HAVE_CARD)));
    }

    @Override
    @CircuitBreaker(name= "calificaciones", fallbackMethod = "crearFallBack")
    public String crear(TarjetaCredito tarjetaCredito) throws CardException {
        BigDecimal totalMarginCard = BigDecimal.valueOf(0.0);
        if(tarjetaCreditoRepository.findByTipoDocumentoAndNumeroDocumento(tarjetaCredito.getTipoDocumento(), tarjetaCredito.getNumeroDocumento()).isPresent()){
            throw new CardException(MessageError.CUSTOMER_WITH_CARD);
        }
        IMarginServiceClient.Calification calification = marginServiceClient.calculateCalification(tarjetaCredito.getTipoDocumento(), tarjetaCredito.getNumeroDocumento());
        List<IMarginServiceClient.Calification.Sublimit> sublimits = calification.getSublimits();
        for (IMarginServiceClient.Calification.Sublimit sublimit : sublimits) {
            if(sublimit.getConcept().name().toString().equals("CARD")){
                totalMarginCard = sublimit.getTotalMargin();
            }
        }
        tarjetaCredito.setLimiteCalificado(totalMarginCard);
        tarjetaCredito.setLimiteDisponible(totalMarginCard);
        tarjetaCredito.setLimiteConsumido(BigDecimal.ZERO);
        tarjetaCreditoRepository.save(tarjetaCredito);
        return tarjetaCredito.getId();

    }

    public String crearFallBack(TarjetaCredito tarjetaCredito, Throwable t) throws CardException {
        log.info(t.getMessage());
        throw new CardException(MessageError.MARGINS_CALIFICACION);
    }


    @Override
    public void debitar(MovimientosTarjetaCredito movimiento) throws CardException {
        BigDecimal amount = movimiento.getImporte().getValor();
        TarjetaCredito tarjetaCredito = tarjetaCreditoRepository.findByTipoDocumentoAndNumeroDocumento(movimiento.getCobrador().getTipoDocumento(), movimiento.getCobrador().getNumeroDocumento()).orElseThrow(() -> new CardException(MessageError.CUSTOMER_NOT_HAVE_CARD));
        tarjetaCredito.setLimiteConsumido(tarjetaCredito.getLimiteConsumido().add(amount));
        tarjetaCredito.setLimiteDisponible(tarjetaCredito.getLimiteDisponible().subtract(amount));
        tarjetaCreditoRepository.save(tarjetaCredito);
        movimientosTarjetaCreditoRepository.save(movimiento);
    }

    @Override
    public void pagar(TarjetaCreditoController.PagoTarjetaCreditoDTO pagoTarjetaCreditoDTO) throws CardException {
        var tarjetaCredito = tarjetaCreditoRepository.findByTipoDocumentoAndNumeroDocumento(pagoTarjetaCreditoDTO.tipoDocumento(), pagoTarjetaCreditoDTO.numeroDocumento()).orElseThrow(() -> new CardException(MessageError.CUSTOMER_NOT_HAVE_CARD));
        var walletResult= walletServiceClient.findByDocumentoAndMoneda(pagoTarjetaCreditoDTO.tipoDocumento(), pagoTarjetaCreditoDTO.numeroDocumento(),tarjetaCredito.getMoneda());
        if( tarjetaCredito.getLimiteConsumido().doubleValue() > walletResult.getBalance().doubleValue() ){
            throw new CardException(MessageError.CUSTOMER_NOT_FOUNDS);
        }
        var consumido = tarjetaCredito.getLimiteConsumido();
        tarjetaCredito.setLimiteConsumido(BigDecimal.ZERO);
        tarjetaCredito.setLimiteDisponible(tarjetaCredito.getLimiteDisponible().add(consumido));
        tarjetaCreditoRepository.save(tarjetaCredito);
        walletServiceClient.updateBalance(walletResult.getId(),walletResult.getBalance()-consumido.doubleValue());
    }
}
