package com.example.ediloaz.control07.Pacientes;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.ediloaz.control07.Citas.ActivityCitasNuevo;
import com.example.ediloaz.control07.Medicos.ActivityMedicosBuscar;
import com.example.ediloaz.control07.Medicos.ActivityMedicosInicio;
import com.example.ediloaz.control07.Medicos.Medico;
import com.example.ediloaz.control07.SessionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ediloaz on 11/01/2017.
 */

public class dbPacientesListado extends AsyncTask<String, Integer, String> {

    private String pass, name_activity, message;
    private boolean isUser;
    private int fila, id_medico;
    private Connection conn;
    private String codigo, nombre, cedula, apellido1,apellido2, nacionalidad, sexo, fechaNacimiento, fechaFallecimiento, email, descripcion,mensaje, type;
    private int activo,id;
    public ArrayList<Paciente> matriz_datos;
    private boolean estado;

    private final Activity activity;
    private  final ProgressBar progressBar;
    private SessionManager session;
    HashMap<String, String> medico;

    public dbPacientesListado(Activity pActivity,ProgressBar pProgressBar,String pDescripcion){
        activity = pActivity;
        progressBar = pProgressBar;
        descripcion = pDescripcion;
        matriz_datos = new ArrayList<Paciente>();

        session = new SessionManager(activity);
        medico = getSession().getUserDetails();
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        progressBar.setVisibility(View.GONE);
        if(estado == true){
            if(descripcion.equals("")){
                ((ActivityPacientesInicio)activity).llenarTabla(matriz_datos);
            }else{
                if (name_activity == "cita"){
                    ((ActivityCitasNuevo)activity).llenarTabla(matriz_datos);
                    name_activity = "";
                }else{
                    ((ActivityPacientesBuscar)activity).llenarTabla(matriz_datos);
                }
            }
        }
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(true);
    }


    public String getMessage(){
        return message;
    }

    public boolean isUser(){
        return isUser;
    }


    @Override
    protected String doInBackground(String... params) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://mysql.freehostia.com:3306/kenqui_expctlr", "kenqui_expctlr", "adminexpctlr");

            PreparedStatement stmt;
            id_medico = Integer.parseInt(medico.get(SessionManager.KEY_ID));
            Log.i("Medico actual ","abcd"+id_medico);
            if (descripcion.equals("")){
                stmt = conn.prepareStatement("SELECT DISTINCT id,fechaNacimiento, nombre, apellido1, apellido2, cedula, nacionalidad, sexo, fechaFallecimiento FROM pacientes P INNER JOIN medicos_pacientes MP ON MP.medico_id = " + id_medico + " AND MP.paciente_id = P.id;");
            }else{
                stmt = conn.prepareStatement("SELECT DISTINCT id,fechaNacimiento, nombre, apellido1, apellido2, cedula, nacionalidad, sexo, fechaFallecimiento" +
                        " FROM pacientes P INNER JOIN medicos_pacientes MP ON MP.medico_id = " + id_medico + " AND MP.paciente_id = P.id " +
                        " WHERE (apellido1 LIKE '%" + descripcion + "%' OR cedula LIKE '%" +
                        descripcion + "%'  OR nombre LIKE '%" + descripcion + "%' OR concat_ws(' ',nombre,apellido1 LIKE '%" +
                        descripcion + "%' OR concat_ws(' ',apellido1,apellido2) LIKE '%" + descripcion + "%'));");
            }

            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                id = rs.getInt("id");
                cedula = rs.getString("cedula");
                nombre = rs.getString("nombre");
                apellido1 = rs.getString("apellido1");
                apellido2 = rs.getString("apellido2");
                nacionalidad = rs.getString("nacionalidad");
                fechaNacimiento = rs.getString("fechaNacimiento");
                fechaFallecimiento = rs.getString("fechaFallecimiento");
                sexo = rs.getString("sexo");

                Paciente paciente = new Paciente(id, nombre,apellido1,apellido2,cedula,nacionalidad, sexo, fechaNacimiento, fechaFallecimiento);
                matriz_datos.add(paciente);
            }

            estado = true;
            conn.close();

        }catch (Exception e){
            Log.w("LoginActivity","ERROR: Conexión---" +e.getMessage());
        }
        return "";
    }

    public void setActivity(String name){
        name_activity = name;
    }
    public SessionManager getSession() {
        return session;
    }


}
