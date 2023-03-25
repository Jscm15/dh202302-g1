package com.dh.g1.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MessageError {
    CUSTOMER_NOT_FOUND("Customer not found"),
    CUSTOMER_EXISTS("Wallet exists");

    String message;
}
