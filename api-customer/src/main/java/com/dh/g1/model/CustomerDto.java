package com.dh.g1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class CustomerDto implements Serializable {

    private Long id;
    private String tipoDocumento;
    private String nroDocumento;
    private String nombre;
    private String apellido;
    private String genero;
    private LocalDate fechaNacimiento;
}
