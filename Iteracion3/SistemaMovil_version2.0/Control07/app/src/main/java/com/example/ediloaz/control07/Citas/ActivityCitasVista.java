package com.example.ediloaz.control07.Citas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.Observaciones.Citas.ActivityCitaObservacionesInicio;
import com.example.ediloaz.control07.Observaciones.Pacientes.ActivityPacienteObservacionesInicio;
import com.example.ediloaz.control07.Pacientes.ActivityPacientesInicio;
import com.example.ediloaz.control07.R;

public class ActivityCitasVista extends CommonCode {
    private String paciente_nombre, medico_nombre, fecha, hora;
    private TextView text_date, text_time, text_paciente, text_medico;
    private Button observaciones;

    private int cita_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas_vista);
        Listener();
        Log.i("A_B_C_D_E","4");
        text_date = (TextView)findViewById(R.id.text_CitasVista_date_answer);
        text_paciente = (TextView)findViewById(R.id.text_CitasVista_Paciente_answer);
        text_medico = (TextView)findViewById(R.id.text_CitasVista_Medico_answer);
        text_time = (TextView)findViewById(R.id.text_CitasVista_Hora_answer);
        Log.i("A_B_C_D_E","5");
        paciente_nombre = getIntent().getStringExtra("paciente_nombre");
        medico_nombre = getIntent().getStringExtra("medico_nombre");
        fecha = getIntent().getStringExtra("fecha");
        hora = getIntent().getStringExtra("hora");

        cita_id = getIntent().getIntExtra("id",1);
        Log.i("A_B_C_D_E","6");
        text_date.setText(fecha);
        text_time.setText(hora);
        text_paciente.setText(paciente_nombre);

        observaciones = (Button) findViewById(R.id.button_CitaObservaciones);
        observaciones.setOnClickListener(this);

        if(getSession().isAdmin()){
            text_medico.setText("Administrador");
        }else {
            text_medico.setText(medico_nombre);
        }


        Log.i("A_B_C_D_E","7");
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.button_CitaObservaciones:
                Intent intent_CitasObservaciones = new Intent(getApplicationContext(),ActivityCitaObservacionesInicio.class);
                intent_CitasObservaciones.putExtra("paciente_nombre", paciente_nombre);
                intent_CitasObservaciones.putExtra("cita_id", cita_id);
                startActivity(intent_CitasObservaciones);
                break;
        }
    }
}
