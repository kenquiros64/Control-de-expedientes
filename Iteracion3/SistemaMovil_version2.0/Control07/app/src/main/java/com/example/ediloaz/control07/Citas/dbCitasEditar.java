package com.example.ediloaz.control07.Citas;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.content.Intent;

import com.example.ediloaz.control07.CommonCode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by ediloaz on 20/01/2017.
 */

public class dbCitasEditar extends AsyncTask<String, Integer, String> {
    private String messageFinished;
    Connection conn;
    private String fecha,hora;
    private int cita_id,activo;
    private int count;
    public boolean correctFinished;
    private ActivityCitasEditar activity;
    private ProgressBar progressBar;

    public dbCitasEditar(ActivityCitasEditar pActivity, ProgressBar pProgressBar, int pID, String fecha, String hora){
        this.fecha = fecha;
        this.hora = hora;
        this.cita_id = pID;
        activity = pActivity;
        progressBar = pProgressBar;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        progressBar.setVisibility(View.GONE);

        if (correctFinished == true){
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "4");
            Toast.makeText(activity.getApplicationContext(),"Cita actualizada exitosamente.", Toast.LENGTH_LONG).show();
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
            ResultSet rs;
            stmt = conn.prepareStatement("UPDATE cita SET fecha= '" + fecha + "', updated_at = NOW(), hora = '" + hora+ "' WHERE id = " + cita_id +";");

            stmt.executeUpdate();
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "Consulta realizada");
            correctFinished = true;

            conn.close();

        }catch (Exception e){
            Log.w("LoginActivity","ERROR: Conexión---" +e.getMessage());
        }
        return "";
    }

}


