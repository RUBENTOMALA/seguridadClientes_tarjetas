package com.banquito.core.banking.seguridad.clientes.tarjetas.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.banquito.core.banking.seguridad.clientes.tarjetas.domain.Acceso;

public interface AccesoRepository extends MongoRepository<Acceso, String> {
    

}