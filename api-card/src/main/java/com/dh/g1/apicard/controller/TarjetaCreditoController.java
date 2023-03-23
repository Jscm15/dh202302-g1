package com.dh.g1.apicard.controller;

import com.dh.g1.apicard.model.TarjetaCredito;
import com.dh.g1.apicard.service.TarjetaCreditoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vi/apiCard/tarjeta")
public class TarjetaCreditoController {

    private final TarjetaCreditoService tarjetaCreditoService;

    public TarjetaCreditoController(TarjetaCreditoService tarjetaCreditoService) {
        this.tarjetaCreditoService = tarjetaCreditoService;
    }


    @PostMapping()
    public void save() {
        tarjetaCreditoService.save();
    }

    @GetMapping()
        public List<TarjetaCredito> getAll(){
            return tarjetaCreditoService.getAll();
        }
}
