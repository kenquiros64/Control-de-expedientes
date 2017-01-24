package com.example.ediloaz.control07.Enfermedades;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class dbEnfermedadesNuevo extends AsyncTask<String, Integer, String> {
    private Connection conn;

    private String codigo, descripcion;
    private int count;

    public boolean correctFinished;

    private ActivityEnfermedadesNuevo activity;
    private ProgressBar progressBar;

    public dbEnfermedadesNuevo(ActivityEnfermedadesNuevo pActivity, ProgressBar pProgressBar,String pCodigo, String pDescripcion){
        codigo = pCodigo;
        descripcion = pDescripcion;
        activity = pActivity;
        progressBar = pProgressBar;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        progressBar.setVisibility(View.GONE);

        if (correctFinished == true){
            this.activity.openEnfermedades(this.activity.getApplicationContext());
            Toast.makeText(activity.getApplicationContext(),"Enfermedad agregada exitosamente.", Toast.LENGTH_LONG).show();
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
            PreparedStatement stmt = conn.prepareStatement("SELECT count(codigo) FROM enfermedads  WHERE codigo = '" + codigo + "' " +
                    "OR descripcion = '"+ descripcion +"';");
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "SELECT COUNT(*) FROM enfermedads  WHERE codigo"+codigo);
            ResultSet rs = stmt.executeQuery();
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "SELECT COUNT(*) FROM enfermedads  WHERE codigo"+count);
            rs.next();
            count = Integer.parseInt(rs.getString(1));

            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "SELECT COUNT(*) FROM enfermedads  WHERE codigo"+count);

            if (count>=1) {
                correctFinished = false;
            }else{
                stmt = conn.prepareStatement("INSERT INTO enfermedads(codigo, descripcion, created_at," +
                        " updated_at) VALUES ('" + codigo + "','" + descripcion + "', NOW(),NOW());");
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