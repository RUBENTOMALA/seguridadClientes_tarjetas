package com.banquito.core.banking.seguridad.clientes.tarjetas.dto;

import java.util.ArrayList;
import java.util.List;

import com.banquito.core.banking.seguridad.clientes.tarjetas.domain.Acceso;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TarjetaDTO {
    private String idTarjeta;
    private String codTarjeta;
    private String numTarjeta;
    private String claveTarjeta;
    private List<AccesoDTO> accesos;

    public void addAcceso(AccesoDTO acceso) {
        if (this.accesos == null) {
            this.accesos = new ArrayList<>();
        }
        this.accesos.add(acceso);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TarjetaDTO other = (TarjetaDTO) obj;
        if (idTarjeta == null) {
            if (other.idTarjeta != null)
                return false;
        } else if (!idTarjeta.equals(other.idTarjeta))
            return false;
        return true;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idTarjeta == null) ? 0 : idTarjeta.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "TarjetaDTO [idTarjeta=" + idTarjeta + ", codTarjeta=" + codTarjeta + ", numTarjeta=" + numTarjeta
                + ", claveTarjeta=" + claveTarjeta + "]";
    }

}
