package com.example.ediloaz.control07.Enfermedades;

import android.os.AsyncTask;
import android.util.Log;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by ediloaz on 11/01/2017.
 */

public class dbEnfermedadesSpinner extends AsyncTask<String, Integer, String> {
    Connection conn;
    private String mensaje;
    int fila;
    public ArrayList<Enfermedad>  matriz_datos;
    int id_paciente;

    public dbEnfermedadesSpinner(int pId){
        id_paciente = pId;
        matriz_datos = new ArrayList<Enfermedad>();
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://mysql.freehostia.com:3306/kenqui_expctlr", "kenqui_expctlr", "adminexpctlr");
            Log.w("LoginActivity", "Conexión");

            PreparedStatement stmt;
            if (id_paciente==-1){
                stmt = conn.prepareStatement("SELECT descripcion FROM enfermedads;");
            }else{
                stmt = conn.prepareStatement("SELECT descripcion FROM enfermedads E \n" +
                        "INNER JOIN enfermedads_pacientes EP ON EP.paciente_id = " + id_paciente + " AND E.id = EP.enfermedad_id;");
            }
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                String descripcion = rs.getString("descripcion");
                mensaje = "La descripcion es " + descripcion;
                Enfermedad enfermedad = new Enfermedad("",descripcion,0);
                matriz_datos.add(enfermedad);
                Log.v("Aviso: ",mensaje);
            }
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