package com.example.eisra.sgg.Modelos;

/**
 * Created by eisra on 29/03/2017.
 */

public class DatosMaestro {
     String mensaje;
     int idMaestro;
     String nombre;
     String apellido;
     String rfc;
     String cedulaProfesional;
     String gradoMaxEstudios;
     String curp;
     String domicilioParticular;
     String telefono;
     int usuario_idusuario;


    public DatosMaestro(String mensaje, String nombre, String apellido, String rfc, String cedulaProfesional, String gradoMaxEstudios, String curp, String domicilioParticular, String telefono, int usuario_idusuario)
    {
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setRfc(rfc);
        this.setCedulaProfesional(cedulaProfesional);
        this.setGradoMaxEstudios(gradoMaxEstudios);
        this.setCurp(curp);
        this.setDomicilioParticular(domicilioParticular);
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCedulaProfesional() {
        return cedulaProfesional;
    }

    public void setCedulaProfesional(String cedulaProfesional) {
        this.cedulaProfesional = cedulaProfesional;
    }

    public String getGradoMaxEstudios() {
        return gradoMaxEstudios;
    }

    public void setGradoMaxEstudios(String gradoMaxEstudios) {
        this.gradoMaxEstudios = gradoMaxEstudios;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getDomicilioParticular() {
        return domicilioParticular;
    }

    public void setDomicilioParticular(String domicilioParticular) {
        this.domicilioParticular = domicilioParticular;
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
