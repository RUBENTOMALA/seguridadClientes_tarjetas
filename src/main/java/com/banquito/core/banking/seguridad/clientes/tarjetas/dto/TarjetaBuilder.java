package com.banquito.core.banking.seguridad.clientes.tarjetas.dto;

import java.util.ArrayList;
import java.util.List;

import com.banquito.core.banking.seguridad.clientes.tarjetas.domain.Acceso;
import com.banquito.core.banking.seguridad.clientes.tarjetas.domain.Tarjeta;

public class TarjetaBuilder {

    public static Tarjeta toTarjeta(TarjetaDTO dto) {
        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setCodTarjeta(dto.getCodTarjeta());
        tarjeta.setNumTarjeta(dto.getNumTarjeta());
        tarjeta.setClaveTarjeta(dto.getClaveTarjeta());

        // Agregar accesos a la tarjeta
        /*tarjeta.setAccesos(new ArrayList<>());
        for (AccesoDTO accesoDTO : dto.getAccesos()) {
            Acceso acceso = new Acceso();
            acceso.setAccion(accesoDTO.getAccion());
            acceso.setCuenta(accesoDTO.getCuenta());
            acceso.setFechaAcceso(accesoDTO.getFechaAcceso());
            tarjeta.getAccesos().add(acceso);
        }*/

        return tarjeta;
    }

    public static TarjetaDTO toDTO(Tarjeta tarjeta){
        List<AccesoDTO> accesosDto = new ArrayList<>();
        for (Acceso acceso : tarjeta.getAccesos()) {
            AccesoDTO dto = AccesoDTO.builder()
                .cuenta(acceso.getCuenta())
                .fechaAcceso(acceso.getFechaAcceso())
                .accion(acceso.getAccion())
                .build();
            accesosDto.add(dto);
        }

        TarjetaDTO dto = TarjetaDTO.builder()
            .codTarjeta(tarjeta.getCodTarjeta())
            .numTarjeta(tarjeta.getNumTarjeta())
            .claveTarjeta(tarjeta.getClaveTarjeta())
            .accesos(accesosDto)
            .build();

        return dto;
    }

    public static Tarjeta copyTarjeta(Tarjeta source, Tarjeta destiny) {
        if (source.getCodTarjeta() != null) {
            destiny.setCodTarjeta(source.getCodTarjeta());
        }

        if (source.getNumTarjeta() != null) {
            destiny.setNumTarjeta(source.getNumTarjeta());
        }

        if (source.getClaveTarjeta() != null) {
            destiny.setClaveTarjeta(source.getClaveTarjeta());
        }

        // Copiar la lista de accesos
        destiny.setAccesos(source.getAccesos());

        return destiny;
    }
}
