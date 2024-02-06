package com.banquito.core.banking.seguridad.clientes.tarjetas.services;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.banquito.core.banking.seguridad.clientes.tarjetas.dao.TarjetaRepository;
import com.banquito.core.banking.seguridad.clientes.tarjetas.domain.Tarjeta;
import com.banquito.core.banking.seguridad.clientes.tarjetas.dto.TarjetaBuilder;
import com.banquito.core.banking.seguridad.clientes.tarjetas.dto.TarjetaDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TarjetaService {

    private final TarjetaRepository tarjetaRepository;

    public TarjetaService(TarjetaRepository tarjetaRepository) {
        this.tarjetaRepository = tarjetaRepository;
    }

    public void crear(TarjetaDTO dto) {
        try {
            Tarjeta tarjeta = TarjetaBuilder.toTarjeta(dto);
            tarjetaRepository.save(tarjeta);
            log.info("Se creó el registro de logueo de la tarjeta: {}", tarjeta);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el registro de logueo de la tarjeta");
        }
    }

    public Boolean validarClave(String numTarjeta, String claveTarjeta) {
        try {
            // String contrasenaHash = new DigestUtils("MD5").digestAsHex(claveTarjeta);
            String contrasenaHash = DigestUtils.md5DigestAsHex(claveTarjeta.getBytes());
            log.info("la cntrasena hasheada es " + contrasenaHash);
            Tarjeta tarjeta = this.tarjetaRepository.findByNumTarjetaAndClaveTarjeta(numTarjeta, contrasenaHash);
            log.info("el dato de tarjeta es " + tarjeta);
            if (tarjeta != null) {
                return true;
            } else {
                throw new RuntimeException("Credenciales incorrectas");
            }

        } catch (Exception e) {
            throw new RuntimeException("Credenciales incorrectas");
        }

    }

    

    public Boolean validarClaveTarjeta(String numTarjeta, String claveTarjeta) {
        try {
            // String contrasenaHash = new DigestUtils("MD5").digestAsHex(claveTarjeta);
            String contrasenaHash = DigestUtils.md5DigestAsHex(claveTarjeta.getBytes());
            log.info("La contraseña hasheada es {}", contrasenaHash);
            // Buscar por número de tarjeta
            Tarjeta tarjetaPorNumero = this.tarjetaRepository.findByNumTarjeta(numTarjeta);
            log.info("Resultado de búsqueda por número de tarjeta: {}", tarjetaPorNumero);
            // Buscar por número de tarjeta y clave de tarjeta
            Tarjeta tarjetaPorClave = this.tarjetaRepository.findByClaveTarjeta(claveTarjeta);
            log.info("Resultado de búsqueda por  clave de tarjeta: {}", claveTarjeta);

            // Verificar si ambas partes de la búsqueda devuelven resultados
            if (tarjetaPorNumero != null && tarjetaPorClave != null) {
                return true;
            } else {
                throw new RuntimeException("Credenciales incorrectas");
            }

        } catch (Exception e) {
            throw new RuntimeException("Credenciales incorrectas");
        }

    }

    @Transactional
    public Boolean actualizarClave(TarjetaDTO dto) {
        try {
            log.info("realiza busqueda del numero de la tarjeta: {}", dto.getNumTarjeta());
            Tarjeta tarjetaAux = this.tarjetaRepository.findByNumTarjeta(dto.getNumTarjeta());
            if (tarjetaAux != null) {
                log.info("Numero de tarjeta encontrada");
                // dto.setClaveTarjeta(new
                // DigestUtils("MD5").digestAsHex(dto.getClaveTarjeta()));
                dto.setClaveTarjeta(DigestUtils.md5DigestAsHex(dto.getClaveTarjeta().getBytes()));
                log.info("Clave de la tarjeta encriptada");
                Tarjeta tarjetaTmp = TarjetaBuilder.toTarjeta(dto);
                Tarjeta tarjeta = TarjetaBuilder.copyTarjeta(tarjetaTmp, tarjetaAux);
                tarjeta.setFechaUltimaModificacion(LocalDateTime.now());
                log.info("Guardando datos");
                this.tarjetaRepository.save(tarjeta);
                log.info("Se actualizo la contrasena del la tarjeta: {}", tarjeta.getNumTarjeta());
                return true;
            } else {
                log.error("Numero de tarjeta no encontrada");
                return false;
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la contrasena.", e);
        }
    }

    // public bool validarcontrasena(numero tarjeta , contrasena)
    // guardo el pin cifrado
    // recibo el pin y lo cifro
    // validar el numero de tarjeta

    // actualizar la contrasena

}
