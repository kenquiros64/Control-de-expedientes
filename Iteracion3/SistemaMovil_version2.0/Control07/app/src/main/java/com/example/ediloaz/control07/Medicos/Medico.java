package com.example.ediloaz.control07.Medicos;

/**
 * Created by Dell on 13/1/2017.
 */

public class Medico {

    private String nombre,apellido1,apellido2,cedula,codigo,nacionalidad, email;
    private int id, activo;


    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Medico(String nombre, String apellido1, String apellido2, String cedula, String codigo, String nacionalidad, String email, int id, int activo) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.nacionalidad = nacionalidad;
        this.apellido2 = apellido2;

        this.email = email;

        this.cedula = cedula;
        this.codigo = codigo;
        this.id = id;
        this.activo = activo;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }


    public Medico(int id, String nombre, String apellido1, String apellido2) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;

    }
    public Medico(String nombre, String apellido1, String apellido2) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
