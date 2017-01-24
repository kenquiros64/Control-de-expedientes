package com.example.ediloaz.control07.Pacientes;

import android.util.Log;

/**
 * Created by Dell on 16/1/2017.
 */

public class Paciente {

    private int id;
    private String nombre, apellido1, apellido2, cedula, nacionalidad, sexo, nacimiento, fallecimiento;

    public Paciente(int id, String nombre, String apellido1, String apellido2, String cedula, String nacionalidad, String sexo, String nacimiento, String fallecimiento) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.cedula = cedula;
        this.nacionalidad = nacionalidad;
        this.sexo = sexo;
        this.nacimiento = nacimiento;
        this.fallecimiento = fallecimiento;
    }

    public Paciente(String nombre, String apellido1, String apellido2) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
    }

    public Paciente(int id, String nombre, String apellido1, String apellido2) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

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

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }


    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }



    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }



    public String getFallecimiento() {
        return fallecimiento;
    }

    public void setFallecimiento(String fallecimiento) {
        this.fallecimiento = fallecimiento;
    }


}
