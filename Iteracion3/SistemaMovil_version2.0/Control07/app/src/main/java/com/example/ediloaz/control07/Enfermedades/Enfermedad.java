package com.example.ediloaz.control07.Enfermedades;

/**
 * Created by Dell on 14/1/2017.
 */

public class Enfermedad {

    private String codigo;
    private String descripcion;
    private int id;

    public Enfermedad(String codigo, String descripcion, int pID) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.id = pID;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
