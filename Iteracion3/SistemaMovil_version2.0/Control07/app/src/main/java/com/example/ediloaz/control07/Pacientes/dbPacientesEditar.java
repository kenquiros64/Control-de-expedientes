package com.example.ediloaz.control07.Pacientes;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.SessionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by ediloaz on 18/01/2017.
 */

public class dbPacientesEditar extends AsyncTask<String, Integer, String> {
    Connection conn;
    private String nombre, apellido1, apellido2, cedula, nacimiento, fallecimiento, sexo, messageFinished, nacionalidad;
    private int id,activo;
    private int count;
    public boolean correctFinished;
    private ActivityPacientesEditar activity;
    private ProgressBar progressBar;
    public dbPacientesEditar(ActivityPacientesEditar pActivity, ProgressBar pProgressBar, int pID,String pNombre, String pApellido1,
                           String pApellido2, String pCedula, String pNacionalidad, String pSexo, String pNacimiento, String pFallecimiento){
        nombre = pNombre;
        apellido1 = pApellido1;
        apellido2 = pApellido2;
        cedula = pCedula;
        nacionalidad = pNacionalidad;
        id = pID;
        nacimiento = pNacimiento;
        fallecimiento = pFallecimiento;
        sexo = pSexo;


        activity = pActivity;
        progressBar = pProgressBar;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        progressBar.setVisibility(View.GONE);

        if (correctFinished == true){
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "4");
            Toast.makeText(activity.getApplicationContext(),"Paciente actualizado exitosamente.", Toast.LENGTH_LONG).show();
            activity.openPacientes(activity.getApplicationContext());
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

            PreparedStatement stmt = conn.prepareStatement("SELECT count(cedula) FROM pacientes  " +
                    "WHERE (cedula = '" + cedula + "') AND id != '" + id +"';");
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "SELECT COUNT(*) FROM MEDICOS  WHERE IDIDIDID" + id);
            ResultSet rs = stmt.executeQuery();
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "SELECT COUNT(*) FROM MEDICOS  WHERE codigo" + nacimiento);
            rs.next();
            count = Integer.parseInt(rs.getString(1));


            if (count >= 1) {
                correctFinished = false;
                messageFinished = "Cédula ya utilizada, ingrese otra.";
            }else if(nombre.equals("") || apellido1.equals("") || apellido2.equals("") || cedula.equals("") || nacionalidad.equals("") || sexo.equals("") || nacimiento.equals("")  ){
                correctFinished = false;
                messageFinished = "Todos los campos son obligatorios";
            }else{
                Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "fechas: "+nacimiento+" falle: "+fallecimiento);
                stmt = conn.prepareStatement("UPDATE pacientes SET cedula = '" + cedula + "', updated_at = NOW()" +
                        ", nombre = '" + nombre + "', apellido1 = '" + apellido1 + "', apellido2 = '" + apellido2 +  "', nacionalidad = '"+ nacionalidad +"', sexo = '" + sexo + "', fechaFallecimiento = '" + fallecimiento + "', fechaNacimiento = '" + nacimiento + "' WHERE id = "+ id +";");

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
