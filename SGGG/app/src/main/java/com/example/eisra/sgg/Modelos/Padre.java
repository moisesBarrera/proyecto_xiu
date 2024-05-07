package com.example.eisra.sgg.Modelos;

/**
 * Created by eisra on 30/03/2017.
 */

public class Padre {
    private String mensaje;
    private int idPadresFamilia;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String direccion;
    private String email;
    private int usuarios_idusuarios;

    public Padre(String mensaje, int idPadresFamilia, String nombre, String apellidos, String telefono, String direccion, String email, int usuarios_idusuarios)
    {
        this.setMensaje(mensaje);
        this.setIdPadresFamilia(idPadresFamilia);
        this.setNombre(nombre);
        this.setApellidos(apellidos);
        this.setTelefono(telefono);
        this.setDireccion(direccion);
        this.setEmail(email);
        this.setUsuarios_idusuarios(usuarios_idusuarios);
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getIdPadresFamilia() {
        return idPadresFamilia;
    }

    public void setIdPadresFamilia(int idPadresFamilia) {
        this.idPadresFamilia = idPadresFamilia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUsuarios_idusuarios() {
        return usuarios_idusuarios;
    }

    public void setUsuarios_idusuarios(int usuarios_idusuarios) {
        this.usuarios_idusuarios = usuarios_idusuarios;
    }
}
