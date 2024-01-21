package com.banquito.core.banking.seguridad.clientes.tarjetas.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Acceso {

    private String cuenta;
    private LocalDateTime fechaAcceso;
    private String accion;

}
