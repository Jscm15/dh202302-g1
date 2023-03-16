package com.dh.g1.apicard.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@Document(collection = "MovimientosTarjetaCredito")
public class MovimientosTarjetaCredito {

    @Id
    private String idMovimiento;
    private String numeroTarjeta;
    private TipoMovimiento tipoMovimiento;
    private Importe importe;
    private LocalDateTime fechaOperacion;
    private Cobrador cobrador;
    private String descripcion;
    private List<DetalleCompra> detalleCompra;
    private Estado estado;
    private Importe comisionBilletera;



    @Getter
    @Setter
    @Builder
    public static class Importe {
        private BigDecimal valor;
        private String moneda;
    }

    @Getter
    @Setter
    @Builder
    public static class Cobrador {
        private String tipoDocumento;
        private String numeroDocumento;
        private String razonSocial;
    }

    @Getter
    @Setter
    @Builder
    public static class DetalleCompra {
        private String articulo;
        private Integer cantidad;
        private Importe unitario;
        private Importe subtotal;
    }


}

