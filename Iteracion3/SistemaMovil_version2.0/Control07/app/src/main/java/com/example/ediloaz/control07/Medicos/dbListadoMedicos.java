package com.example.ediloaz.control07.Medicos;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.ediloaz.control07.ActivityLogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by ediloaz on 11/01/2017.
 */

public class dbListadoMedicos extends AsyncTask<String, Integer, String> {

    private Connection conn;
    private String codigo, nombre, cedula, apellido1,apellido2, nacionalidad, email, descripcion;
    private int activo,id;
    public ArrayList<Medico> matriz_datos;
    private boolean estado;

    private final Activity activity;
    private  final ProgressBar progressBar;

    public dbListadoMedicos(Activity pActivity,ProgressBar pProgressBar,String pDescripcion){
        descripcion = pDescripcion;
        activity = pActivity;
        progressBar = pProgressBar;
        estado = false;
        matriz_datos = new ArrayList<Medico>();
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        progressBar.setVisibility(View.GONE);
        if(estado == true){
            if(descripcion.equals("")){
                ((ActivityMedicosInicio)activity).llenarTabla(matriz_datos);
            }else{
                ((ActivityMedicosBuscar)activity).llenarTabla(matriz_datos);
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

            PreparedStatement stmt;
            if (descripcion.equals("")){
                stmt = conn.prepareStatement("SELECT id,codigo,cedula,nombre,apellido1,apellido2,nacionalidad,email,activo FROM medicos;");
            }else{
                stmt = conn.prepareStatement("SELECT id,codigo,cedula,nombre,apellido1,apellido2,nacionalidad,email,activo" +
                        " FROM medicos WHERE (codigo LIKE '%" + descripcion + "%' OR cedula LIKE '%" +
                        descripcion + "%'  OR nombre LIKE '%" + descripcion + "%' OR concat_ws(' ',nombre,apellido1 LIKE '%" +
                        descripcion + "%' OR concat_ws(' ',apellido1,apellido2) LIKE '%" + descripcion + "%'));");
            }

            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                codigo = rs.getString("codigo");
                cedula = rs.getString("cedula");
                nombre = rs.getString("nombre");
                apellido1 = rs.getString("apellido1");
                apellido2 = rs.getString("apellido2");
                nacionalidad = rs.getString("nacionalidad");
                email = rs.getString("email");
                id = rs.getInt("id");
                activo = rs.getInt("activo");

                Medico medico = new Medico(nombre,apellido1,apellido2,cedula,codigo,nacionalidad, email,id,activo);
                matriz_datos.add(medico);
            }

            estado = true;
            conn.close();

        }catch (Exception e){
            Log.w("LoginActivity","ERROR: Conexión---" +e.getMessage());
        }
        return "";
    }
}