package com.dh.g1.apicard.controller;

import com.dh.g1.apicard.exception.CardException;
import com.dh.g1.apicard.model.MovimientosTarjetaCredito;
import com.dh.g1.apicard.model.TarjetaCredito;
import com.dh.g1.apicard.service.TarjetaCreditoService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vi/apiCard/tarjeta")
public class TarjetaCreditoController {

    private final TarjetaCreditoService tarjetaCreditoService;

    public TarjetaCreditoController(TarjetaCreditoService tarjetaCreditoService) {
        this.tarjetaCreditoService = tarjetaCreditoService;
    }

    /*
    @PostMapping()
    public void save() {
        tarjetaCreditoService.save();
    }

    @GetMapping()
        public List<TarjetaCredito> getAll(){
            return tarjetaCreditoService.getAll();
        }



    @GetMapping("/{tipoDocumento}/{numeroDocumento}")
    public Optional<TarjetaCredito> findByTipoAndNumeroDocumento(@PathVariable String tipoDocumento, @PathVariable String numeroDocumento) throws CardException {
        return tarjetaCreditoService.findByTipoDocumentoAndNumeroDocumento(tipoDocumento, numeroDocumento);
    }
 */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String crear(@Valid @RequestBody TarjetaCredito tarjetaCredito) throws CardException {
        return this.tarjetaCreditoService.crear(tarjetaCredito);
    }


    @PutMapping("/debito")
    public void debitar(@RequestBody MovimientosTarjetaCredito movimiento) throws CardException {
        this.tarjetaCreditoService.debitar(movimiento);
    }

    @PutMapping("/pago")
    public void pagar(@RequestBody @Valid PagoTarjetaCreditoDTO pagoTarjetaCreditoDTO) throws CardException {
        this.tarjetaCreditoService.pagar(pagoTarjetaCreditoDTO);
    }
    public record PagoTarjetaCreditoDTO(
            @NotNull
            Integer numeroCredito,
            @NotNull
            String tipoDocumento,
            @NotNull
            String numeroDocumento
    ) {
    }



}
