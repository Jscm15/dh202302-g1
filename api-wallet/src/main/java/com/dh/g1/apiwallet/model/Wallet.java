package com.dh.g1.apiwallet.model;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table
@Getter
@Setter
@Builder
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipoDocumento;
    private String nroDocumento;
    @ManyToOne
    @JoinColumn(name="codigo_moneda", nullable = false)
    private Moneda moneda;
    private Double balance;
}
