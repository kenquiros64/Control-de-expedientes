package com.example.ediloaz.control07.Medicos;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ediloaz.control07.CommonCode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by ediloaz on 11/01/2017.
 */

public class dbMedicosNuevo extends AsyncTask<String, Integer, String> {
    Connection conn;
    private String nombre, apellido1, apellido2, cedula, codigo, email, password, nacionalidad;
    private boolean activo;
    private String messageFinished;
    private int count;
    public boolean correctFinished;
    private CommonCode activity;
    private  ProgressBar progressBar;


    public dbMedicosNuevo(CommonCode pActivity, ProgressBar pProgressBar, String pNombre, String pApellido1,
                          String pApellido2, String pCedula, String pCodigo, String pEmail, String pPassword, String pNacionalidad, boolean pActivo){
        nombre = pNombre;
        apellido1 = pApellido1;
        apellido2 = pApellido2;
        cedula = pCedula;
        codigo = pCodigo;
        email = pEmail;
        password = pPassword;
        nacionalidad = pNacionalidad;
        activo = pActivo;

        activity = pActivity;
        progressBar = pProgressBar;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        progressBar.setVisibility(View.GONE);

        if (correctFinished == true){
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "4");
            Toast.makeText(activity.getApplicationContext(),"Médico agregado exitosamente.", Toast.LENGTH_LONG).show();
            activity.openMedicos(activity.getApplicationContext());
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
            PreparedStatement stmt = conn.prepareStatement("SELECT count(cedula) FROM medicos  " +
                    "WHERE cedula = '" + cedula + "' OR codigo = '" + codigo + "';");
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "SELECT COUNT(*) FROM MEDICOS  WHERE codigo" + codigo);
            ResultSet rs = stmt.executeQuery();
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "SELECT COUNT(*) FROM MEDICOS  WHERE codigo" + count);
            rs.next();
            count = Integer.parseInt(rs.getString(1));

            stmt = conn.prepareStatement("SELECT count(email) FROM medicos  " +
                    "WHERE email = '" + email + "';");
            rs = stmt.executeQuery();
            rs.next();
            int countEmail = Integer.parseInt(rs.getString(1));


            if (count >= 1) {
                correctFinished = false;
                messageFinished = "Código o cédula ya utilizados, ingrese otro.";
            }else if(countEmail  >= 1){
                correctFinished = false;
                messageFinished = "Email ya utilizado, ingrese otro.";
            }else if(nombre.equals("") || apellido1.equals("") || apellido2.equals("") || cedula.equals("") || codigo.equals("")
                    || email.equals("") || password.equals("") ){
                correctFinished = false;
                messageFinished = "Todos los campos son obligatorios";
            }else{
                int valueActivo = 0;
                if(activo == true){
                    valueActivo = 1;
                }

                stmt = conn.prepareStatement("INSERT INTO medicos(email, sign_in_count, created_at, " +
                        "updated_at, codigo, nombre, apellido1, apellido2, cedula, nacionalidad, passcode, " +
                        "activo) VALUES ('" + email + "', 99, NOW(), NOW(), '" + codigo + "', '" + nombre+ "','" +
                        apellido1 + "','" + apellido2 + "','" + cedula + "','"+ nacionalidad +"','" + password + "', " + valueActivo +");");

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
