package com.banquito.core.banking.seguridad.clientes.tarjetas.domain;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Acceso {

    private String cuenta;
    @Field("fecha_acceso")
    private LocalDateTime fechaAcceso;
    private String accion;

}
