package com.dh.g1.apicard.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MessageError {
    CUSTOMER_WITH_CARD("El cliente no puede tener mas de una tarjeta"),
    CUSTOMER_NOT_HAVE_CARD("El cliente no tiene tarjeta de credito registrada"),
    CUSTOMER_NOT_FOUNDS("El cliente no tiene fondos suficientes");

    String message;
}
