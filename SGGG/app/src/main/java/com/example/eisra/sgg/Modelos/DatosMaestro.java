package com.example.eisra.sgg.Modelos;

/**
 * Created by eisra on 29/03/2017.
 */

public class DatosMaestro {
     String mensaje;
     int idMaestro;
     String nombre;
     String apellidos;
     String rfc;
     String cedula;
     String estudios;
     String curp;
     String direccion;
     String telefono;
     int usuario_idusuario;


    public DatosMaestro(String mensaje, String nombre, String apellidos, String rfc, String cedula,String estudios, String curp, String direccion, String telefono, int usuario_idusuario)
    {
        this.setNombre(nombre);
        this.setApellidos(apellidos);
        this.setRfc(rfc);
        this.setCedula(cedula);
        this.setEstudios(estudios);
        this.setCurp(curp);
        this.setDireccion(direccion);
        this.setTelefono(telefono);
        this.setUsuario_idusuario(usuario_idusuario);
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getIdMaestro() {
        return idMaestro;
    }

    public void setIdMaestro(int idMaestro) {
        this.idMaestro = idMaestro;
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

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getEstudios() {
        return estudios;
    }

    public void setEstudios(String estudios) {
        this.estudios = estudios;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getUsuario_idusuario() {
        return usuario_idusuario;
    }

    public void setUsuario_idusuario(int usuario_idusuario) {
        this.usuario_idusuario = usuario_idusuario;
    }
}
