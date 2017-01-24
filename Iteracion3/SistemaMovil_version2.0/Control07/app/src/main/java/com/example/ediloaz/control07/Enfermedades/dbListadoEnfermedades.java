package com.example.ediloaz.control07.Enfermedades;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class dbListadoEnfermedades extends AsyncTask<String, Integer, String> {
    private Connection conn;
    private String codigo, descripcion, mensaje;
    private int fila;
    private ArrayList<Enfermedad> matriz_datos;

    private ProgressBar progressBar;
    private final Activity activity;
    private boolean estado;

    public dbListadoEnfermedades(Activity pActivity, ProgressBar pProgressBar,String pDescripcion){
        descripcion = pDescripcion;
        matriz_datos = new ArrayList<Enfermedad>();
        activity = pActivity;
        progressBar = pProgressBar;
        estado = false;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        progressBar.setVisibility(View.GONE);

        if(estado == true) {
            if (descripcion.equals("")) {
                ((ActivityEnfermedadesInicio) activity).llenarTabla(matriz_datos);
            } else {
                ((ActivityEnfermedadesBuscar) activity).llenarTabla(matriz_datos);
            }
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
            if (descripcion.equals("")){
                stmt = conn.prepareStatement("SELECT id,codigo,descripcion FROM enfermedads;");
                ResultSet rs = stmt.executeQuery();
                String codigo, descripcion;
                while(rs.next()) {
                    codigo = rs.getString(2);
                    descripcion = rs.getString(3);
                    int id = Integer.parseInt(rs.getString(1));

                    mensaje = "El código es " + codigo + " con descripcion " + descripcion;
                    Enfermedad enfermedad = new Enfermedad(codigo,descripcion,id);
                    matriz_datos.add(enfermedad);
                    Log.v("Aviso: ",mensaje);
                }
            }else{
                stmt = conn.prepareStatement("SELECT DISTINCT P.id, P.nombre, P.cedula FROM pacientes P INNER JOIN enfermedads_pacientes EP ON P.id=EP.paciente_id INNER JOIN enfermedads E ON E.id = EP.enfermedad_id AND E.descripcion = '" + descripcion + "';");
                ResultSet rs = stmt.executeQuery();
                String nombre, cedula;
                while(rs.next()) {
                    nombre = rs.getString(2);
                    cedula = rs.getString(3);
                    int id = Integer.parseInt(rs.getString(1));

                    mensaje = "El nombre es " + codigo + " con cédula " + descripcion;
                    Enfermedad enfermedad = new Enfermedad(nombre,cedula,id);
                    matriz_datos.add(enfermedad);
                    Log.v("Aviso: ",mensaje);
                }
            }

            estado = true;

            conn.close();

        }catch (Exception e){
            Log.w("LoginActivity","ERROR: Conexión---" +e.getMessage());
        }
        return "";
    }

    public ArrayList<Enfermedad> GetMatriz(){
        return matriz_datos;
    }


}
