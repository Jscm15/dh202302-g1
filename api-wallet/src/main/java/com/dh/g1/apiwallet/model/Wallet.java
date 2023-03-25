package com.dh.g1.apiwallet.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table
@Getter
@Setter
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
