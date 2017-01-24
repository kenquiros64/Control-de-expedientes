package com.example.ediloaz.control07.Citas;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.Enfermedades.ActivityEnfermedadesInicio;
import com.example.ediloaz.control07.Medicos.ActivityMedicosInicio;
import com.example.ediloaz.control07.Pacientes.ActivityPacientesInicio;
import com.example.ediloaz.control07.Pacientes.ActivityPacientesNuevo;
import com.example.ediloaz.control07.Pacientes.dbEnlacesPaciente;
import com.example.ediloaz.control07.Pacientes.dbPacientesNuevo;
import com.example.ediloaz.control07.R;
import com.example.ediloaz.control07.SessionManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class ActivityCitasNuevo2 extends ActionBarActivity implements View.OnClickListener {
    private Button button_agregar, button_date;
    private Button pacientes_button, medicos_button, enfermedades_button, citas_button, logout_button;
    public ProgressBar progressBar;
    private TextView text_date, text_medico, text_paciente;
    private EditText edit_time;
    private Calendar calendar = Calendar.getInstance();
    private boolean admin;
    private String nombre_medico,paciente, fecha, hora;
    private int id_paciente, id_medico;


    private SessionManager session;
    HashMap<String, String> medico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas_nuevo2);

        session = new SessionManager(this.getApplicationContext());

        session.checkLogin();

        admin = session.isAdmin();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        pacientes_button = (Button)findViewById(R.id.dashboard_Pacientes);
        pacientes_button.setOnClickListener(this);
        medicos_button = (Button) findViewById(R.id.dashboard_Medicos);
        medicos_button.setOnClickListener(this);
        enfermedades_button = (Button) findViewById(R.id.dashboard_Enfermedades);
        enfermedades_button.setOnClickListener(this);
        if(!admin) {
            medicos_button.setVisibility(View.GONE);
            enfermedades_button.setVisibility(View.GONE);
        }
        citas_button = (Button)findViewById(R.id.dashboard_Citas);
        citas_button.setOnClickListener(this);
        logout_button = (Button)findViewById(R.id.dashboard_LogOut);
        logout_button.setOnClickListener(this);

        button_agregar = (Button) findViewById(R.id.button_CitasNuevo2_Agregar);
        button_agregar.setOnClickListener(this);
        button_date = (Button) findViewById((R.id.button_CitasNuevo2_date));
        button_date.setOnClickListener(this);

        text_date = (TextView)findViewById(R.id.text_CitasNuevo2_date_answer);
        text_medico = (TextView)findViewById(R.id.text_CitasNuevo2_Medico_answer);
        text_paciente = (TextView)findViewById(R.id.text_CitasNuevo2_Paciente_answer);

        edit_time = (EditText)findViewById(R.id.edit_CitasNuevo_time);

        id_paciente = getIntent().getIntExtra("paciente_id",0);
//        medico = getIntent().getStringExtra("medico");
        paciente = getIntent().getStringExtra("paciente_nombre");
        Log.i("tyuiqw","nuevo2"+paciente);
//        id_medico = Integer.parseInt(medico.get(SessionManager.KEY_ID));


        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);


        session = new SessionManager(this);
        medico = getSession().getUserDetails();
        id_medico = Integer.parseInt(medico.get(SessionManager.KEY_ID));
        nombre_medico = medico.get(SessionManager.KEY_NAME);


        text_paciente.setText(paciente);
        if(admin){
            text_medico.setText("Administrador");
        }else{
            text_medico.setText(nombre_medico);
        }
    }



    @Override
    public void onClick(View v){
        switch (v.getId())
        {
            case R.id.dashboard_Pacientes:
                Intent intent_Pacientes = new Intent(getApplicationContext(),ActivityPacientesInicio.class);
                startActivity(intent_Pacientes);
                finish();
                break;
            case R.id.dashboard_Medicos:
                if(admin){
                    Intent intent_Medicos = new Intent(getApplicationContext(),ActivityMedicosInicio.class);
                    startActivity(intent_Medicos);
                    finish();
                }else{
                    Toast.makeText(this.getApplicationContext(),"Ingreso para administradores.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.dashboard_Enfermedades:
                if(admin){
                    Intent intent_Enfermedades= new Intent(getApplicationContext(),ActivityEnfermedadesInicio.class);
                    startActivity(intent_Enfermedades);
                    finish();
                }else{
                    Toast.makeText(this.getApplicationContext(),"Ingreso para administradores.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.dashboard_Citas:
                Intent intent_Citas = new Intent(getApplicationContext(),ActivityCitasInicio.class);
                startActivity(intent_Citas);
                finish();
                break;
            case R.id.dashboard_LogOut:
                if(getSession().isLoggedIn()){
                    getSession().logoutUser();
                    finish();
                }
                break;
            case R.id.button_CitasNuevo2_date:
                new DatePickerDialog(ActivityCitasNuevo2.this,listener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.button_CitasNuevo2_Agregar:




                fecha = text_date.getText().toString();
                hora = edit_time.getText().toString();
//                sex = String.valueOf(spinner_sex.getSelectedItem());

                Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "1");
                try{
                    dbCitasNuevo db = new dbCitasNuevo(this,progressBar,fecha,hora);
                    db.execute();
                } catch (Exception e) {
                    Intent intent_Ingresar = new Intent(this.getApplicationContext(), ActivityEnfermedadesInicio.class);
                    startActivity(intent_Ingresar);
                    finish();
                }

                break;

        }
    }

    public int getIdPaciente(){
        return id_paciente;
    }

    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
            text_date.setText(year+ "/" + (monthOfYear+1) + "/" + dayOfMonth);
        }
    };


    public void openCitas(Context context){
        Intent intent_Ingresar = new Intent(context, ActivityCitasInicio.class);
        startActivity(intent_Ingresar);
    }

    public SessionManager getSession() {
        return session;
    }
}

