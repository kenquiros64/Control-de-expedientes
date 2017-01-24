package com.example.ediloaz.control07.Pacientes;

import android.os.AsyncTask;
import android.util.Log;

import com.example.ediloaz.control07.Enfermedades.Enfermedad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by ediloaz on 18/01/2017.
 */

public class dbEnlacesPaciente extends AsyncTask<String, Integer, String> {
    Connection conn;
    private String mensaje;
    int fila;
    int id_paciente;
    String opcion, resultado, operacion, dato_nuevo, dato_anterior;

    public dbEnlacesPaciente(int pId, String pOpcion, String pOperacion, String pDatoNuevo, String pDatoAnterior){
        id_paciente = pId;
        opcion = pOpcion;
        operacion = pOperacion;
        dato_nuevo = pDatoNuevo;
        dato_anterior = pDatoAnterior;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://mysql.freehostia.com:3306/kenqui_expctlr", "kenqui_expctlr", "adminexpctlr");
            Log.w("LoginActivity", "Conexión");

            PreparedStatement stmt = null;
            ResultSet rs = null;
            if (opcion.equals("telefono")){
                Log.v("Aviso: ","entre a telefono");
                if (operacion.equals("select")) {
                    if (id_paciente == -1) {
                        stmt = conn.prepareStatement("SELECT T.telefono FROM pacientes P\n" +
                                "INNER JOIN telefonos T ON T.paciente_id = P.id;");
                    } else {
                        stmt = conn.prepareStatement("SELECT T.telefono AS descripcion FROM pacientes P\n" +
                                "INNER JOIN telefonos T ON T.paciente_id = " + id_paciente +" LIMIT 1;");
                    }
                    rs = stmt.executeQuery();
                }else if (operacion.equals("update")){
                    stmt = conn.prepareStatement("UPDATE telefonos SET telefono = '" + dato_nuevo + "' WHERE paciente_id = "+ id_paciente +" AND telefono = '" + dato_anterior + "';");
                    stmt.executeUpdate();
                }else if (operacion.equals("insert")){
                    Log.v("prooooooodTelefono",""+dato_nuevo);
                    stmt = conn.prepareStatement("select id from pacientes order by id desc limit 1;");
                    rs = stmt.executeQuery();
                    if(rs.next()){
                        id_paciente = Integer.parseInt(rs.getString("id"));
                        stmt = conn.prepareStatement("INSERT INTO telefonos(paciente_id, telefono, created_at, updated_at) VALUES(" + id_paciente +",'" + dato_nuevo + "', NOW(), NOW());");
                        stmt.executeUpdate();
                        Log.v("prooooooodTelefono2",""+dato_nuevo);
                    }
                }
            } else if (opcion.equals("correo")){
                Log.v("Aviso: ","entre a correo");
                if (operacion.equals("select")) {
                    if (id_paciente == -1) {
                        stmt = conn.prepareStatement("SELECT E.email FROM pacientes P\n" +
                                "INNER JOIN emails E ON E.paciente_id = P.id;");
                    } else {
                        stmt = conn.prepareStatement("SELECT E.email AS descripcion FROM pacientes P\n" +
                                "INNER JOIN emails E ON E.paciente_id = " + id_paciente + " LIMIT 1;");
                    }
                    rs = stmt.executeQuery();
                }else if (operacion.equals("update")){
                    stmt = conn.prepareStatement("UPDATE emails SET email = '" + dato_nuevo + "' WHERE paciente_id = " + id_paciente +" AND email = '" + dato_anterior + "';");
                    stmt.executeUpdate();
                }else if (operacion.equals("insert")){
                    Log.v("prooooooodCorreo",""+dato_nuevo);
                    stmt = conn.prepareStatement("select id from pacientes order by id desc limit 1;");
                    rs = stmt.executeQuery();
                    if(rs.next()){
                        id_paciente = Integer.parseInt(rs.getString("id"));
                        stmt = conn.prepareStatement("INSERT INTO emails(paciente_id, email, created_at, updated_at) VALUES(" + id_paciente +",'" + dato_nuevo + "', NOW(), NOW());");
                        stmt.executeUpdate();
                        Log.v("prooooooodCorreo2",""+dato_nuevo);
                    }
                }
            }
            while(rs.next()) {
                resultado  = rs.getString("descripcion");
                mensaje = "El resultado es " + resultado;
                Log.v("Aviso: ",mensaje);
            }
            conn.close();

        }catch (Exception e){
            Log.w("LoginActivity","ERROR: Conexión---" +e.getMessage());
        }
        return "";
    }

    public String GetResultado(){
        return resultado;
    }


}