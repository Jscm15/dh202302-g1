package com.dh.g1.apicard.controller;

import com.dh.g1.apicard.model.TarjetaCredito;
import com.dh.g1.apicard.repository.ITarjetaCreditoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/vi/apiCard/tarjeta")
public class TarjetaCreditoController {

    private final ITarjetaCreditoRepository tarjetaCreditoRepository;

    public TarjetaCreditoController(ITarjetaCreditoRepository tarjetaCreditoRepository) {
        this.tarjetaCreditoRepository = tarjetaCreditoRepository;
    }

    @PostMapping()
    public void save() {
        tarjetaCreditoRepository.save(
                TarjetaCredito.builder()
                        .id(UUID.randomUUID().toString())
                        .numeroTarjeta("XYZ123")
                        .tipoDocumento("DU")
                        .numeroDocumento("ABC123")
                        .moneda("USD")
                        .limiteCalificado(new BigDecimal(500000))
                        .limiteConsumido(new BigDecimal(100000))
                        .limiteDisponible(new BigDecimal(400000))
                        .build()
        );
    }
        @GetMapping()
        public List<TarjetaCredito> getAll(){
            return tarjetaCreditoRepository.findAll();
        }
}
