package com.banquito.core.banking.seguridad.clientes.tarjetas.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Document(collection = "Tarjetas")
public class Tarjeta {

    @Id
    private String id;
    @Field("numero_tarjeta")
    private String numTarjeta;
    private Number pin;
    @Field("fecha_creacion")
    private LocalDateTime fechaCreacion;
    @Field("fecha_ultima_modificacion")
    private LocalDateTime fechaUltimaModificacion;
    @Version
    private Long version;

    private List<Acceso> accesos;

}
