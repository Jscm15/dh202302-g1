package com.dh.g1.exceptions;

public class CustomerException extends Throwable {
    public CustomerException(MessageError messageError) {super(messageError.message);}
}
