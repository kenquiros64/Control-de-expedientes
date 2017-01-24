package com.example.ediloaz.control07.Medicos;

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
 * Created by Dell on 14/1/2017.
 */

public class dbMedicosEditar extends AsyncTask<String, Integer, String> {
    Connection conn;
    private String nombre, apellido1, apellido2, cedula, codigo, email, password, messageFinished, nacionalidad;
    private int id,activo;
    private int count;
    public boolean correctFinished;
    private CommonCode activity;
    private ProgressBar progressBar;

    public dbMedicosEditar(CommonCode pActivity, ProgressBar pProgressBar, int pID,String pNombre, String pApellido1,
                          String pApellido2, String pCedula, String pCodigo, String pEmail, String pPassword, String pNacionalidad, int pActivo){
        nombre = pNombre;
        apellido1 = pApellido1;
        apellido2 = pApellido2;
        cedula = pCedula;
        codigo = pCodigo;
        email = pEmail;
        password = pPassword;
        nacionalidad = pNacionalidad;
        id = pID;
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
            Toast.makeText(activity.getApplicationContext(),"Médico actualizado exitosamente.", Toast.LENGTH_LONG).show();
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
                    "WHERE (cedula = '" + cedula + "' OR codigo = '" + codigo + "') AND id != '" + id +"';");
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "SELECT COUNT(*) FROM MEDICOS  WHERE codigo" + codigo);
            ResultSet rs = stmt.executeQuery();
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "SELECT COUNT(*) FROM MEDICOS  WHERE codigo" + count);
            rs.next();
            count = Integer.parseInt(rs.getString(1));

            stmt = conn.prepareStatement("SELECT count(email) FROM medicos  " +
                    "WHERE email = '" + email + "' AND id != '" + id +"';");
            rs = stmt.executeQuery();
            rs.next();
            int countEmail = Integer.parseInt(rs.getString(1));

            if (count >= 1) {
                correctFinished = false;
                messageFinished = "Código o cédula ya utilizados, ingrese otro.";
            }else if (countEmail >= 1) {
                correctFinished = false;
                messageFinished = "Email ya utilizado, ingrese otro.";
            }else if(password.equals("")){
                stmt = conn.prepareStatement("UPDATE medicos SET email = '" + email + "', updated_at = NOW(), codigo = '" + codigo + "'," +
                        " nombre = '" + nombre + "', apellido1 = '" + apellido1 + "', apellido2 = '" + apellido2 + "'," +
                        "cedula = '" + cedula + "', nacionalidad = '"+ nacionalidad +"', activo = " + activo + " WHERE id = "+ id +";");

                Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "Consulta creada");
                stmt.executeUpdate();
                Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "Consulta realizada");
                correctFinished = true;
            }else {
                stmt = conn.prepareStatement("UPDATE medicos SET email = '" + email + "', updated_at = NOW(), codigo = '" + codigo + "'," +
                        " nombre = '" + nombre + "', apellido1 = '" + apellido1 + "', apellido2 = '" + apellido2 + "', passcode = '" + password + "'," +
                        " cedula = '" + cedula + "', nacionalidad = '"+ nacionalidad +"', activo = 0 WHERE id = "+ id +";");

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

