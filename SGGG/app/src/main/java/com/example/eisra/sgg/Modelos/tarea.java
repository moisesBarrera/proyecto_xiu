package com.example.eisra.sgg.Modelos;

/**
 * Created by moisesbarrerakeb on 02/04/17.
 */

public class tarea {
    private int idtarea;
    private String nombre;
    private String grupo_idgrupo;
    private int esEnEquipo;

    public tarea(int idtarea, String nombre, String grupo_idgrupo, int esEnEquipo) {
        this.idtarea = idtarea;
        this.nombre = nombre;
        this.grupo_idgrupo = grupo_idgrupo;
        this.esEnEquipo = esEnEquipo;
    }

    public int getIdtarea() {
        return idtarea;
    }

    public String getNombre() {
        return nombre;
    }

    public String getGrupo_idgrupo() {
        return grupo_idgrupo;
    }

    public int isEsEnEquipo() {
        return esEnEquipo;
    }
}
