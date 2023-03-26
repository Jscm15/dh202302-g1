package com.dh.g1.apiwallet.controller;

import com.dh.g1.apiwallet.exception.WalletException;
import com.dh.g1.apiwallet.model.DTO.WalletDTO;
import com.dh.g1.apiwallet.model.Wallet;
import com.dh.g1.apiwallet.service.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wallet")
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/find-documento-moneda/{tipoDocumento}/{documento}/{codigo}")
    public Wallet findByDocumentoAndMoneda(@PathVariable String tipoDocumento,
                                           @PathVariable String documento,
                                           @PathVariable String codigo) throws Exception {
        return walletService.findByDocumentoAndMoneda(tipoDocumento, documento, codigo);
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public void create(@RequestBody Wallet wallet) throws WalletException {
        walletService.create(wallet);
    }

    @GetMapping("/find-documento/{tipoDocumento}/{documento}")
    public List<Wallet> findByDocumento(@PathVariable String tipoDocumento,
                                       @PathVariable String documento) throws WalletException {
        return walletService.findByDocumento(tipoDocumento, documento);
    }

    @PutMapping("/update-balance")
    @ResponseStatus(code = HttpStatus.OK)
    public void updateBalance(@RequestBody WalletDTO walletDTO) throws WalletException {
        walletService.updateBalance(walletDTO);
    }

    @PutMapping("/{id}/{balance}")
    @ResponseStatus(code = HttpStatus.OK)
    public void updateBalance(@PathVariable Long id, @PathVariable Double balance) throws WalletException {
        walletService.updateBalance(id, balance);
    }
}
