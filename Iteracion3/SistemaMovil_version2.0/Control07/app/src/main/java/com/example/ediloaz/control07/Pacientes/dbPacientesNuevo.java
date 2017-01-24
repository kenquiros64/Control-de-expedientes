package com.example.ediloaz.control07.Pacientes;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
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
import java.util.HashMap;

/**
 * Created by ediloaz on 17/01/2017.
 */

public class dbPacientesNuevo extends AsyncTask<String, Integer, String> {

    Connection conn;
    private String name, lastname1, lastname2, banknote, email, address, telephone, nationality, sex, birth, death;
    private String messageFinished;
    public boolean correctFinished;
    private int count;
    private ActivityPacientesNuevo activity;
    private ProgressBar progressBar;

    private SessionManager session;
    HashMap<String, String> medico;

    public dbPacientesNuevo(ActivityPacientesNuevo pActivity, ProgressBar pProgressBar, String pName, String pLastname1, String pLastname2, String pBanknote, String pEmail, String pAddress, String pTelephone, String pNationality, String pSex, String pBirth, String pDeath){
        name = pName;
        lastname1 = pLastname1;
        lastname2 = pLastname2;
        banknote = pBanknote;
        email = pEmail;
        address = pAddress;
        telephone  = pTelephone;
        nationality = pNationality;
        sex = pSex;
        birth = pBirth;
        death = pDeath;

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
            Toast.makeText(activity.getApplicationContext(),"Paciente agregado exitosamente.", Toast.LENGTH_LONG).show();
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

//            Verificar si ya el codigo existe
            PreparedStatement stmt = conn.prepareStatement("SELECT count(cedula) FROM pacientes  " +
                    "WHERE cedula = '" + banknote + "';");
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "SELECT COUNT(*) FROM MEDICOS  WHERE codigo" + banknote);
            ResultSet rs = stmt.executeQuery();
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "SELECT COUNT(*) FROM MEDICOS  WHERE codigo" + count);
            rs.next();
            count = Integer.parseInt(rs.getString(1));


            if (count >= 1) {
                correctFinished = false;
                messageFinished = "Cédula ya utilizada, ingrese otra.";
            }else if(name.equals("") || lastname1.equals("") || lastname2.equals("") || banknote.equals("") || nationality.equals("") || sex.equals("") || birth.equals("")  ){
                correctFinished = false;
                messageFinished = "Todos los campos son obligatorios";
            }else{
                stmt = conn.prepareStatement("INSERT INTO pacientes( created_at, updated_at, fechaNacimiento, nombre, apellido1, apellido2, cedula, nacionalidad, sexo, fechaFallecimiento) VALUES (NOW(), NOW(), '" + birth + "', '" + name+ "','" + lastname1 + "','" + lastname2 + "','" + banknote + "','" + nationality + "','" + sex + "','" + death + "');");
                Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "Consulta creada");
                stmt.executeUpdate();
                Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "Consulta realizada");
                stmt = conn.prepareStatement("select id from pacientes order by id desc limit 1;");
                rs = stmt.executeQuery();
                if(rs.next()) {
                    int id_paciente = Integer.parseInt(rs.getString("id"));
                    int id_medico = Integer.parseInt(medico.get(SessionManager.KEY_ID));
                    stmt = conn.prepareStatement("INSERT INTO medicos_pacientes(medico_id, paciente_id) VALUES ('" + id_medico + "', '" + id_paciente + "');");
                    Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "Consulta creada");
                    stmt.executeUpdate();
                }
                Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "Consulta realizada");
                correctFinished = true;
            }
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
