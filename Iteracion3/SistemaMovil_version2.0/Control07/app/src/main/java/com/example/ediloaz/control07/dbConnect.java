package com.example.ediloaz.control07;

import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class dbConnect extends AsyncTask<String, Integer, String> {

    private Connection conn;
    private String email;
    private String pass;
    private boolean isUser;

    private ActivityLogin context;
    private SessionManager session;

    public dbConnect(ActivityLogin pContext, String pEmail, String pPass){
        this.email = pEmail;
        this.pass = pPass;
        this.isUser = false;
        this.context = pContext;

        // Session Manager
        session = new SessionManager(pContext.getApplicationContext());
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://mysql.freehostia.com:3306/kenqui_expctlr", "kenqui_expctlr", "adminexpctlr");
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "Conexión a la BD exitosa");

            PreparedStatement stmt = conn.prepareStatement("SELECT id,nombre,apellido1,apellido2,email,passcode FROM medicos WHERE email = '" + email + "' ORDER BY id ASC LIMIT 1;");
            ResultSet rs = stmt.executeQuery();

            if(rs.next() ) {
                Log.w("LoginActivity", "REGULAR USER: " + pass + "---" + rs.getString(6));
                if(rs.getString(6).equals(pass)) {
                    Log.w("LoginActivity", "REGULAR USER: " + email + "---" + rs.getString(5));
                    isUser = true;

                    int id = rs.getInt("id");

                    PreparedStatement stRol = conn.prepareStatement("SELECT medico_id FROM medicos_roles WHERE medico_id = '"+id+"';");
                    ResultSet rsRol = stRol.executeQuery();

                    if(rsRol.next()){ // es admin
                        String userName = rs.getString(2);
                        session.createLoginSession(String.valueOf(id),userName,email,true);
                    }else{
                        String userName = rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4);
                        session.createLoginSession(String.valueOf(id),userName,email,false);
                    }

                    return "Conectado";
                }else{
                    Log.w("LoginActivity", "PASSWORD INCORRECTO");
                    return  "Correo o contraseña incorrectos";
                }
            }
            conn.close();
        }catch (Exception e){
            Log.w("LoginActivity","ERROR: Conexión---" +e.getMessage());
            context.reloadPage();
            return "ERROR: Conexión---" +e.getMessage();
        }
        return "";
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if(isUser){
            Log.w("LoginActivity", "WIIIIIIII CONECTADO");
            context.openWelcomePage();
        }
    }
}
