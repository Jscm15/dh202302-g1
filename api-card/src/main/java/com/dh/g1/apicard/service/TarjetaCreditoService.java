package com.dh.g1.apicard.service;

import com.dh.g1.apicard.feign.IMarginServiceClient;
import com.dh.g1.apicard.model.TarjetaCredito;
import com.dh.g1.apicard.repository.ITarjetaCreditoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class TarjetaCreditoService {

    private final ITarjetaCreditoRepository tarjetaCreditoRepository;

    private final IMarginServiceClient marginServiceClient;


    public TarjetaCreditoService(ITarjetaCreditoRepository tarjetaCreditoRepository, IMarginServiceClient marginServiceClient) {
        this.tarjetaCreditoRepository = tarjetaCreditoRepository;
        this.marginServiceClient = marginServiceClient;
    }

    private BigDecimal findlimiteCalificado (String tipoDocumento, String nroDocumento ){
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
    }
}
