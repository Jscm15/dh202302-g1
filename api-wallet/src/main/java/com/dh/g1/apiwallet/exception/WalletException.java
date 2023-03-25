package com.dh.g1.apiwallet.exception;

import com.dh.g1.apiwallet.model.Wallet;

public class WalletException extends Exception{

    public WalletException(MessageError messageError) {super(messageError.message);}
}
