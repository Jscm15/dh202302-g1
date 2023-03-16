package com.dh.g1.apicard.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@Document (collection = "TarjetaCredito")
public class TarjetaCredito {

    @Id
    private String id;
    private String numeroTarjeta;
    private String tipoDocumento;
    private String numeroDocumento;
    private String moneda;
    private BigDecimal limiteCalificado;
    private BigDecimal limiteConsumido;
    private BigDecimal limiteDisponible;

}
