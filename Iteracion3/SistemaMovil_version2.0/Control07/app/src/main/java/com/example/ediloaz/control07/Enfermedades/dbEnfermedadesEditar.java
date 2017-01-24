package com.example.ediloaz.control07.Enfermedades;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Dell on 14/1/2017.
 */

public class dbEnfermedadesEditar  extends AsyncTask<String, Integer, String> {
    private Connection conn;

    private String codigo, descripcion;
    private int count,id;

    public boolean correctFinished;

    private ActivityEnfermedadesEditar activity;
    private ProgressBar progressBar;

    public dbEnfermedadesEditar(ActivityEnfermedadesEditar pActivity, ProgressBar pProgressBar,String pCodigo, String pDescripcion, int pId){
        codigo = pCodigo;
        descripcion = pDescripcion;
        activity = pActivity;
        progressBar = pProgressBar;
        id = pId;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        progressBar.setVisibility(View.GONE);

        if (correctFinished == true){
            this.activity.openEnfermedades(this.activity.getApplicationContext());
            Toast.makeText(activity.getApplicationContext(),"Enfermedad actualizada exitosamente.", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(activity.getApplicationContext(),"Enfermedad o código ya existentes.", Toast.LENGTH_LONG).show();
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
            PreparedStatement stmt = conn.prepareStatement("SELECT count(codigo) FROM enfermedads  WHERE (codigo = '" + codigo + "' " +
                    "OR descripcion = '"+ descripcion +"') AND id != " + id + ";");
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "SELECT COUNT(*) FROM enfermedads  WHERE codigo"+codigo);
            ResultSet rs = stmt.executeQuery();
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "SELECT COUNT(*) FROM enfermedads  WHERE codigo"+count);
            rs.next();
            count = Integer.parseInt(rs.getString(1));

            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "SELECT COUNT(*) FROM enfermedads  WHERE codigo"+count);

            if (count>=1) {
                correctFinished = false;
            }else{
                stmt = conn.prepareStatement("UPDATE enfermedads SET codigo = '" + codigo + "', descripcion = '" + descripcion + "', " +
                        "created_at = NOW(), updated_at = NOW() WHERE id = " + id + ";");
                Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "Consulta creada");
                stmt.executeUpdate();
                Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "Consulta realizada");
                correctFinished = true;
            }

            conn.close();

        }catch (Exception e){
            Log.w("LoginActivity","ERROR: Conexión---" +e.getMessage());
        }
        return "";
    }
}
