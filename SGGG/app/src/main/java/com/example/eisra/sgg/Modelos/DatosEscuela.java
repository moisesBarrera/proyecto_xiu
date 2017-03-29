package com.example.eisra.sgg.Modelos;

/**
 * Created by moisesbarrerakeb on 29/03/17.
 */

public class DatosEscuela {
    String mensaje;
    int idEscuela;
    String nombre;
    int turno;
    String cct;
    String sector;
    String zonaEscolar;
    String ubicacion;
    int usuarios_idusuarios;

    public DatosEscuela(String mensaje, int idEscuela, String nombre, int turno, String cct, String sector, String zonaEscolar, String ubicacion, int usuarios_idusuarios) {
        this.mensaje = mensaje;
        this.idEscuela = idEscuela;
        this.nombre = nombre;
        this.turno = turno;
        this.cct = cct;
        this.sector = sector;
        this.zonaEscolar = zonaEscolar;
        this.ubicacion = ubicacion;
        this.usuarios_idusuarios = usuarios_idusuarios;
    }

    public String getMensaje() {
        return mensaje;
    }

    public int getIdEscuela() {
        return idEscuela;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTurno() {
        return turno;
    }

    public String getCct() {
        return cct;
    }

    public String getSector() {
        return sector;
    }

    public String getZonaEscolar() {
        return zonaEscolar;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public int getUsuarios_idusuarios() {
        return usuarios_idusuarios;
    }
}
