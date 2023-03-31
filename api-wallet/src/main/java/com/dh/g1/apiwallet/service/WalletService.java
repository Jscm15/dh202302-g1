package com.dh.g1.apiwallet.service;

import com.dh.g1.apiwallet.client.ICustomerClient;
import com.dh.g1.apiwallet.exception.MessageError;
import com.dh.g1.apiwallet.exception.WalletException;
import com.dh.g1.apiwallet.model.DTO.WalletDTO;
import com.dh.g1.apiwallet.model.Wallet;
import com.dh.g1.apiwallet.repository.IWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletService implements IWalletService{

    private final IWalletRepository walletRepository;

    private final ICustomerClient customerClient;

    @Autowired
    public WalletService(IWalletRepository walletRepository, ICustomerClient customerClient) {
        this.walletRepository = walletRepository;
        this.customerClient = customerClient;
    }

    @Override
    public void create(Wallet wallet) throws WalletException {
        customerClient.getCustomer(wallet.getTipoDocumento(), wallet.getNroDocumento()).orElseThrow(() -> new WalletException(MessageError.CUSTOMER_NOT_FOUND));
        if(!(walletRepository.findBytipoDocumentoAndNroDocumentoAndMoneda_Codigo(wallet.getTipoDocumento(), wallet.getNroDocumento(), wallet.getMoneda().getCodigo()).isPresent())){
            walletRepository.save(wallet);
        };
    }

    @Override
    public void updateBalance(WalletDTO walletDTO) throws WalletException {
        Wallet wallet = walletRepository.findById(walletDTO.getId()).orElseThrow(() -> new WalletException(MessageError.WALLET_NOT_FOUND));
        wallet.setBalance(walletDTO.getBalance());
        walletRepository.save(wallet);
    }

    @Override
    public void updateBalance(Long id, Double balance) throws WalletException {
        Wallet wallet = walletRepository.findById(id).orElseThrow(() -> new WalletException(MessageError.WALLET_NOT_FOUND));
        wallet.setBalance(balance);
        walletRepository.save(wallet);
    }

    @Override
    public Wallet findByDocumentoAndMoneda(String tipoDocumento, String nroDocumento, String codigo_moneda) throws WalletException {
        return walletRepository.findBytipoDocumentoAndNroDocumentoAndMoneda_Codigo (tipoDocumento, nroDocumento, codigo_moneda).orElseThrow(() -> new WalletException(MessageError.WALLET_NOT_FOUND));
    }

    @Override
    public List<Wallet> findByDocumento(String tipoDocumento, String nroDocumento) throws WalletException {
        return walletRepository.findBytipoDocumentoAndNroDocumento(tipoDocumento,nroDocumento);
    }
}
