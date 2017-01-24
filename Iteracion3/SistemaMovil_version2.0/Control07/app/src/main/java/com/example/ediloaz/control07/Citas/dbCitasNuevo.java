package com.example.ediloaz.control07.Citas;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ediloaz.control07.Pacientes.ActivityPacientesNuevo;
import com.example.ediloaz.control07.SessionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

/**
 * Created by ediloaz on 13/01/2017.
 */

public class dbCitasNuevo extends AsyncTask<String, Integer, String> {
    Connection conn;
    private Button pacientes_button, medicos_button, enfermedades_button, citas_button, logout_button;
    private String fecha, hora;
    private String messageFinished;
    public boolean correctFinished;
    private int count;
    private ActivityCitasNuevo2 activity;
    private ProgressBar progressBar;
    ResultSet rs;

    private SessionManager session;
    HashMap<String, String> medico;

    public dbCitasNuevo(ActivityCitasNuevo2 pActivity, ProgressBar pProgressBar, String pFecha, String pHora){
        fecha = pFecha;
        hora = pHora;

        activity = pActivity;
        progressBar = pProgressBar;

        session = new SessionManager(activity);
        medico = getSession().getUserDetails();

    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        progressBar.setVisibility(View.GONE);

        if (correctFinished == true){
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "4");
            Toast.makeText(activity.getApplicationContext(),"Cita agregada exitosamente.", Toast.LENGTH_LONG).show();
            activity.openCitas(activity.getApplicationContext());
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "5");
        }else{
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "6");
            Toast.makeText(activity.getApplicationContext(),messageFinished, Toast.LENGTH_LONG).show();
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

//            Verificar si ya el codigo existe
            PreparedStatement stmt;
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "Consulta realizada");

            int id_paciente = activity.getIdPaciente();
//            int id_paciente = Integer.parseInt(rs.getString("id"));

            int id_medico = Integer.parseInt(medico.get(SessionManager.KEY_ID));
            stmt = conn.prepareStatement("INSERT INTO cita (fecha, hora, medico_id, paciente_id, created_at, updated_at) VALUES ('" + fecha + "', '" + hora + "', " + id_medico + ", " + id_paciente + ", NOW(), NOW());");
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "Consulta creada");
            stmt.executeUpdate();

            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "Consulta realizada");
            correctFinished = true;
            conn.close();
        }catch (Exception e){
            Log.w("LoginActivity","ERROR: Conexión---" +e.getMessage());
        }
        return "";
    }


    public SessionManager getSession() {
        return session;
    }


}
