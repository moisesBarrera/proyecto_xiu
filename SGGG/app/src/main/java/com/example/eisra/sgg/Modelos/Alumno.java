package com.example.eisra.sgg.Modelos;

/**
 * Created by eisra on 30/03/2017.
 */

public class Alumno {
    private String mensaje;
    private int id_Alumnos;
    private String nombre;
    private String apellido;
    private String genero;
    private String curp;
    private String matricula;
    private int usuarios_idusuarios;
    private String observaciones;
    private int grupo_idgrupo;

    public Alumno(String mensaje, int id_Alumnos, String nombre, String apellido, String genero, String curp, String matricula, int usuarios_idusuarios, String observaciones,int grupo_idgrupo)
    {
        this.setMensaje(mensaje);
        this.setId_Alumnos(id_Alumnos);
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setGenero(genero);
        this.setCurp(curp);
        this.setMatricula(matricula);
        this.setUsuarios_idusuarios(usuarios_idusuarios);
        this.setObservaciones(observaciones);
        this.setGrupo_idgrupo(grupo_idgrupo);
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getId_Alumnos() {
        return id_Alumnos;
    }

    public void setId_Alumnos(int id_Alumnos) {
        this.id_Alumnos = id_Alumnos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getUsuarios_idusuarios() {
        return usuarios_idusuarios;
    }

    public void setUsuarios_idusuarios(int usuarios_idusuarios) {
        this.usuarios_idusuarios = usuarios_idusuarios;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getGrupo_idgrupo() {
        return grupo_idgrupo;
    }

    public void setGrupo_idgrupo(int grupo_idgrupo) {
        this.grupo_idgrupo = grupo_idgrupo;
    }
}
