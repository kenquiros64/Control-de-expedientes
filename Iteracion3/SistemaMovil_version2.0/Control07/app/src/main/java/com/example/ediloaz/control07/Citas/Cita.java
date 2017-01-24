package com.example.ediloaz.control07.Citas;

import com.example.ediloaz.control07.Medicos.Medico;
import com.example.ediloaz.control07.Pacientes.Paciente;

/**
 * Created by Dell on 16/1/2017.
 */

public class Cita {

    private String fecha,hora;
    private Medico medico;
    private Paciente paciente;

    private int id;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Cita(int id, String fecha, String hora, Medico medico, Paciente paciente) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.medico = medico;
        this.paciente = paciente;
    }
}
