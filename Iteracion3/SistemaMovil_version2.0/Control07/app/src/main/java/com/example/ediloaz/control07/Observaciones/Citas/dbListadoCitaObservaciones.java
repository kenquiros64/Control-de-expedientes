package com.example.ediloaz.control07.Observaciones.Citas;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ediloaz.control07.Observaciones.Observacion;
import com.example.ediloaz.control07.Observaciones.Pacientes.ActivityPacienteObservacionesInicio;
import com.example.ediloaz.control07.Observaciones.Pacientes.ActivityPacienteObservacionesNuevo;
import com.example.ediloaz.control07.SessionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Dell on 22/1/2017.
 */

public class dbListadoCitaObservaciones extends AsyncTask<String, Integer, String> {

    private Connection conn;
    private ArrayList<Observacion> matriz_datos;
    private String mensaje;
    private ProgressBar progressBar;
    private final ActivityCitaObservacionesInicio activity;
    private boolean correctFinished;
    private int cita_id;


    public dbListadoCitaObservaciones(ActivityCitaObservacionesInicio pActivity, ProgressBar pProgressBar, int cita_id){
        this.matriz_datos = new ArrayList<Observacion>();
        this.activity = pActivity;
        this.progressBar = pProgressBar;
        this.correctFinished = false;
        this.cita_id = cita_id;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        progressBar.setVisibility(View.GONE);

        if(correctFinished == true) {
            activity.llenarTabla(matriz_datos);
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(true);
    }


    @Override
    protected String doInBackground(String... params) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://mysql.freehostia.com:3306/kenqui_expctlr", "kenqui_expctlr", "adminexpctlr");
            Log.w("LoginActivity", "Conexión");

            PreparedStatement stmt;

            stmt = conn.prepareStatement("SELECT DISTINCT id, descripcion FROM observacions O INNER JOIN observacions_citas OP ON OP.citum_id = " + cita_id + " AND OP.observacion_id = O.id;");
            ResultSet rs = stmt.executeQuery();
            String descripcion;
            while(rs.next()) {
                descripcion = rs.getString(2);
                int id = Integer.parseInt(rs.getString(1));

                mensaje = "La descripcion de la observacion es " + descripcion;

                Observacion observacion = new Observacion(id, descripcion);
                matriz_datos.add(observacion);
                Log.v("Aviso: ",mensaje);
            }

            correctFinished = true;

            conn.close();

        }catch (Exception e){
            Log.w("LoginActivity","ERROR: Conexión---" +e.getMessage());
            correctFinished = false;
        }
        return "";
    }
}
