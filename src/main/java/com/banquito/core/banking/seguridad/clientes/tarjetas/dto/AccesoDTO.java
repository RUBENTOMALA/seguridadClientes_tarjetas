package com.banquito.core.banking.seguridad.clientes.tarjetas.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AccesoDTO {
    private String cuenta;
    private LocalDateTime fechaAcceso;
    private String accion;
}
