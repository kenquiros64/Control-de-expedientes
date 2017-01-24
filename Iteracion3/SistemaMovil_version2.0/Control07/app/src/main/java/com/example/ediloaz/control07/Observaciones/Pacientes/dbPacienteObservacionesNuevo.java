package com.example.ediloaz.control07.Observaciones.Pacientes;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ediloaz.control07.Observaciones.Pacientes.ActivityPacienteObservacionesNuevo;
import com.example.ediloaz.control07.SessionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;


public class dbPacienteObservacionesNuevo extends AsyncTask<String, Integer, String> {
    private Connection conn;

    private String descripcion;
    private int paciente_id;

    public boolean correctFinished;

    private ActivityPacienteObservacionesNuevo activity;
    private ProgressBar progressBar;

    private SessionManager session;
    private HashMap<String, String> medico;

    public dbPacienteObservacionesNuevo(ActivityPacienteObservacionesNuevo pActivity, ProgressBar pProgressBar, String pDescripcion, int paciente_id){
        this.paciente_id = paciente_id;
        this.descripcion = pDescripcion;
        this.activity = pActivity;
        this.progressBar = pProgressBar;

        session = new SessionManager(activity);
        medico = session.getUserDetails();
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        progressBar.setVisibility(View.GONE);

        if (correctFinished == true){
            this.activity.openObservacion(this.activity.getApplicationContext());
            Toast.makeText(activity.getApplicationContext(),"Observación agregada exitosamente.", Toast.LENGTH_LONG).show();
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
            correctFinished = false;

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://mysql.freehostia.com:3306/kenqui_expctlr", "kenqui_expctlr", "adminexpctlr");
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "Conexión a la BD exitosa");

            int id_medico = Integer.parseInt(medico.get(SessionManager.KEY_ID));

            PreparedStatement stmt;

            stmt = conn.prepareStatement("INSERT INTO observacions(descripcion, created_at," +
                    " updated_at, medico_id) VALUES ('" + descripcion + "', NOW(),NOW(), "+ id_medico +");");
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "Consulta creada");
            stmt.executeUpdate();
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "Consulta realizada");

            stmt = conn.prepareStatement("select id from observacions order by id desc limit 1;");

            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                int observacion_id = Integer.parseInt(rs.getString("id"));

                stmt = conn.prepareStatement("INSERT INTO observacions_pacientes(observacion_id, paciente_id) VALUES ('" + observacion_id + "', '" + paciente_id + "');");
                Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "Consulta creada");
                stmt.executeUpdate();

                correctFinished = true;
            }


            conn.close();

        }catch (Exception e){
            Log.w("LoginActivity","ERROR: Conexión---" +e.getMessage());
        }
        return "";
    }

}