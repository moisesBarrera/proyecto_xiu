package com.example.eisra.sgg.Modelos;

/**
 * Created by eisra on 02/04/2017.
 */

public class Grupo {
    private int idgrupo;
    private String nombre;
    private int usuarios_idusuarios;

    public Grupo(int idgrupo, String nombre, int usuarios_idusuarios)
    {
        this.setIdgrupo(idgrupo);
        this.setNombre(nombre);
        this.setUsuarios_idusuarios(usuarios_idusuarios);
    }

    public int getIdgrupo() {
        return idgrupo;
    }

    public void setIdgrupo(int idgrupo) {
        this.idgrupo = idgrupo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getUsuarios_idusuarios() {
        return usuarios_idusuarios;
    }

    public void setUsuarios_idusuarios(int usuarios_idusuarios) {
        this.usuarios_idusuarios = usuarios_idusuarios;
    }
}
