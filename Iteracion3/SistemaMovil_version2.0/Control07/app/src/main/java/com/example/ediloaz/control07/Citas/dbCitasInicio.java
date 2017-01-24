package com.example.ediloaz.control07.Citas;

import android.app.Activity;
import android.app.IntentService;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.ediloaz.control07.Enfermedades.ActivityEnfermedadesBuscar;
import com.example.ediloaz.control07.Enfermedades.ActivityEnfermedadesInicio;
import com.example.ediloaz.control07.Medicos.Medico;
import com.example.ediloaz.control07.Pacientes.Paciente;
import com.example.ediloaz.control07.SessionManager;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ediloaz on 13/01/2017.
 */

public class dbCitasInicio extends AsyncTask<String, Integer, String> {

    private Connection conn;
    private String mensaje;
    private int fila, pacienteID, descripcion,medicoID;
    public ArrayList<Cita> matriz_datos;

    private ProgressBar progressBar;
    private final Activity activity;

    private SessionManager session;
    private HashMap<String, String> medico;


    public dbCitasInicio(Activity pActivity, ProgressBar pProgressBar,int pDescripcion){
        matriz_datos = new ArrayList<Cita>();
        activity = pActivity;
        progressBar = pProgressBar;
        descripcion = pDescripcion;

        session = new SessionManager(pActivity.getApplicationContext());
        medico = session.getUserDetails();
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        progressBar.setVisibility(View.GONE);
        if (descripcion==0){
            ((ActivityCitasInicio) activity).llenarTabla(matriz_datos);
        }else{
            ((ActivityCitasListadoPaciente) activity).llenarTabla(matriz_datos);
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

            int id_medico = Integer.parseInt(medico.get(SessionManager.KEY_ID));

            String medicoNombre, medicoApellido1, medicoApellido2;
            String pacienteNombre, pacienteApellido1, pacienteApellido2;

            PreparedStatement stmt;

            stmt = conn.prepareStatement("SELECT nombre, apellido1, apellido2 FROM medicos WHERE id= " + id_medico + " ORDER BY id ASC LIMIT 1;");

            ResultSet mRS = stmt.executeQuery();

            mRS.next();

            medicoNombre = mRS.getString(1);
            medicoApellido1 = mRS.getString(2);
            medicoApellido2 = mRS.getString(3);

            if (descripcion == 0){
                stmt = conn.prepareStatement("SELECT fecha, hora, id, paciente_id FROM cita WHERE medico_id= " + id_medico + ";");
            }else{
                stmt = conn.prepareStatement("SELECT fecha, hora, id, paciente_id FROM cita WHERE medico_id= " + id_medico + " AND paciente_id= " + descripcion + ";");
//                        " FROM cita  WHERE (P.ID LIKE '%" + descripcion + "%' OR P.nombre LIKE '%" +
//                        descripcion + "%'  OR C.fecha LIKE '%" + descripcion + "%' OR concat_ws(' ',M.nombre,M.apellido1 LIKE '%" +
//                        descripcion + "%' OR concat_ws(' ',P.nombre,P.apellido1) LIKE '%" + descripcion + "%'));");
            }


            ResultSet rs = stmt.executeQuery();
            String fecha, hora;
            int idCita;
            pacienteID = descripcion;
            while(rs.next()) {
                fecha = rs.getString(1);
                hora = rs.getString(2);
                idCita = rs.getInt(3);
                pacienteID = rs.getInt(4);

                PreparedStatement stPaciente = conn.prepareStatement("SELECT nombre, apellido1, apellido2 FROM pacientes WHERE id = " + pacienteID + " ORDER BY id ASC LIMIT 1;");

                ResultSet rsPaciente = stPaciente.executeQuery();

                rsPaciente.next();

                pacienteNombre = rsPaciente.getString(1);
                pacienteApellido1 = rsPaciente.getString(2);
                pacienteApellido2 = rsPaciente.getString(3);


                Medico medico = new Medico(medicoID, medicoNombre, medicoApellido1,medicoApellido2);
                Paciente paciente = new Paciente(pacienteID, pacienteNombre,pacienteApellido1,pacienteApellido2);

                Cita cita = new Cita(idCita,fecha,hora,medico,paciente);

                matriz_datos.add(cita);
            }
            conn.close();

        }catch (Exception e){
            Log.w("LoginActivity","ERROR: Conexión---" +e.getMessage());
        }
        return "";
    }


}
