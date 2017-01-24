package com.example.ediloaz.control07.Citas;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ediloaz.control07.Enfermedades.ActivityEnfermedadesInicio;
import com.example.ediloaz.control07.Medicos.ActivityMedicosInicio;
import com.example.ediloaz.control07.Medicos.dbMedicosEditar;
import com.example.ediloaz.control07.Pacientes.ActivityPacientesInicio;
import com.example.ediloaz.control07.R;
import com.example.ediloaz.control07.SessionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.HashMap;


public class ActivityCitasEditar extends ActionBarActivity implements View.OnClickListener {
    private Button pacientes_button, medicos_button, enfermedades_button, citas_button, logout_button;
    private TextView text_date, text_paciente, text_medico;
    private EditText edit_time;
    private Button button_date, button_editar;
    private String paciente_nombre, medico_nombre, fecha, hora;

    private int id, activo, cita_id;
    private boolean admin;
    private ProgressBar progressBar;
    private Spinner spinner_nacionalidad;

    private Calendar calendar = Calendar.getInstance();

    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas_editar);

        session = new SessionManager(this.getApplicationContext());

        session.checkLogin();

        admin = session.isAdmin();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Log.i("A_B_C_D_E","4");

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

        Log.i("A_B_C_D_E","5");
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        Log.i("A_B_C_D_E","50");

        progressBar.setVisibility(View.GONE);
        Log.i("A_B_C_D_E","51");

        button_date = (Button)findViewById(R.id.button_CitasEditar_date);
        Log.i("A_B_C_D_E","52");
        button_date.setOnClickListener(this);
        Log.i("A_B_C_D_E","53");
        button_editar = (Button)findViewById(R.id.button_CitasEditar_editar);
        button_editar.setOnClickListener(this);
        Log.i("A_B_C_D_E","6");
        cita_id = getIntent().getIntExtra("cita_id",0);
        text_date = (TextView)findViewById(R.id.text_CitasEditar_date_answer);
        text_paciente = (TextView)findViewById(R.id.text_CitasEditar_Paciente_answer);
        text_medico = (TextView)findViewById(R.id.text_CitasEditar_Medico_answer);
        edit_time = (EditText)findViewById(R.id.edit_CitasEditar_time);

        Log.i("A_B_C_D_E","7");

        paciente_nombre = getIntent().getStringExtra("paciente_nombre");
        medico_nombre = getIntent().getStringExtra("medico_nombre");
        fecha = getIntent().getStringExtra("fecha");
        hora = getIntent().getStringExtra("hora");
        cita_id = getIntent().getIntExtra("id",0);

        text_date.setText(fecha);
        edit_time.setText(hora);
        text_paciente.setText(paciente_nombre);
        if(admin){
            text_medico.setText("Administrador");
        }else{
            text_medico.setText(medico_nombre);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
//                if(getSession().isLoggedIn()){
//                    getSession().logoutUser();
//                    finish();
//                }
                break;
            case R.id.button_CitasEditar_date:
                new DatePickerDialog(ActivityCitasEditar.this,listener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.button_CitasEditar_editar:
                paciente_nombre = text_paciente.getText().toString();
                medico_nombre = text_medico.getText().toString();
                fecha = text_date.getText().toString();
                hora = edit_time.getText().toString();

                Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "1");
                dbCitasEditar db = new dbCitasEditar(this,progressBar,cita_id,fecha,hora);
                Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "2");

                db.execute();

                Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "3");


                break;
        }
    }
    public void openCitas(Context context){
        Intent intent_Ingresar = new Intent(context, ActivityCitasInicio.class);
        startActivity(intent_Ingresar);
    }

    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
            text_date.setText(year+ "/" + (monthOfYear+1) + "/" + dayOfMonth);
        }
    };

}
