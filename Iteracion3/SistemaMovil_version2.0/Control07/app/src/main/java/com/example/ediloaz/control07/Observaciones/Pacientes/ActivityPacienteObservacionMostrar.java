package com.example.ediloaz.control07.Observaciones.Pacientes;

import android.os.Bundle;
import android.widget.TextView;

import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.R;

public class ActivityPacienteObservacionMostrar extends CommonCode {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observacion_mostrar);

        String paciente_nombre = getIntent().getStringExtra("paciente_nombre");
        String observacion_descripcion = getIntent().getStringExtra("observacion_descripcion");

        TextView textDescripcion = (TextView) findViewById(R.id.text_Observacion_description);

        TextView textPaciente = (TextView) findViewById(R.id.text_Observaciones_Paciente);

        textDescripcion.setText(observacion_descripcion);

        textPaciente.setText("Paciente: " + paciente_nombre);

    }
}
