package com.example.ediloaz.control07;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.ediloaz.control07.Citas.ActivityCitasInicio;
import com.example.ediloaz.control07.Enfermedades.ActivityEnfermedadesInicio;
import com.example.ediloaz.control07.Medicos.ActivityMedicosInicio;
import com.example.ediloaz.control07.Pacientes.ActivityPacientesInicio;


public class CommonCode extends AppCompatActivity implements View.OnClickListener {
    private Button pacientes_button, medicos_button, enfermedades_button, citas_button, logout_button;

    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Session Manager
        session = new SessionManager(this.getApplicationContext());

        getSession().checkLogin();

    }

    public void Listener(){
        boolean admin = getSession().isAdmin();
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
    }

    @Override
    public void onClick(View v) {
        boolean admin = getSession().isAdmin();
        switch (v.getId()){
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
        }
    }

    public void openMedicos(Context context){
        Intent intent_Ingresar = new Intent(context, ActivityMedicosInicio.class);
        startActivity(intent_Ingresar);
    }


    public void openEnfermedades(Context context){
        Intent intent_Ingresar = new Intent(context, ActivityEnfermedadesInicio.class);
        startActivity(intent_Ingresar);
    }

    public SessionManager getSession() {
        return session;
    }

}