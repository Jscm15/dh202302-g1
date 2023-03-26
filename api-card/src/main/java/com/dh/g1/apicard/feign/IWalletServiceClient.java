package com.dh.g1.apicard.feign;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(name="api-wallet")
public interface IWalletServiceClient {

    @GetMapping("/api/v1/wallet/find-documento-moneda/{tipoDocumento}/{documento}/{codigo}")
    Wallet findByDocumentoAndMoneda(@PathVariable String tipoDocumento, @PathVariable String documento,
                                           @PathVariable String codigo);

    @PutMapping("/api/v1/wallet/{id}/{balance}")
    @ResponseStatus(code = HttpStatus.OK)
    void updateBalance(@PathVariable Long id, @PathVariable Double balance);

    @Getter
    @Setter
    class Wallet{
        private Long id;
        private Double balance;
    }

}
