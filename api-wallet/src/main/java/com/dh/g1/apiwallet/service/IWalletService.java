package com.dh.g1.apiwallet.service;

import com.dh.g1.apiwallet.exception.WalletException;
import com.dh.g1.apiwallet.model.DTO.WalletDTO;
import com.dh.g1.apiwallet.model.Wallet;
import org.apache.catalina.LifecycleState;

import java.util.List;

public interface IWalletService {
    public void create(Wallet wallet) throws WalletException;
    public void updateBalance(WalletDTO walletDTO) throws WalletException;
    public void updateBalance(Long id, Double balance) throws WalletException;
    public Wallet findByDocumentoAndMoneda(String tipoDocumento, String nroDocumento, String codigo_moneda) throws  WalletException;
    public List<Wallet> findByDocumento(String tipoDocumento, String nroDocumento) throws  WalletException;
}
