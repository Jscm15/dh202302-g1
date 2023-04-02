package com.dh.g1.apiwallet.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table
@Getter
@Setter
@Builder
public class Moneda {
    @Id
    private String codigo;
    private String descripcion;
}
