package com.example.ediloaz.control07.Observaciones;

/**
 * Created by Dell on 20/1/2017.
 */

public class Observacion {

    protected String descripcion;
    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Observacion(int id, String descripcion) {
        this.descripcion = descripcion;
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



}
