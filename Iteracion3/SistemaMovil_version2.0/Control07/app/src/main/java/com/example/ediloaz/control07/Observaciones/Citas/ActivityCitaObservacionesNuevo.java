package com.example.ediloaz.control07.Observaciones.Citas;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.Observaciones.Pacientes.ActivityPacienteObservacionesInicio;
import com.example.ediloaz.control07.Observaciones.Pacientes.dbPacienteObservacionesNuevo;
import com.example.ediloaz.control07.R;

public class ActivityCitaObservacionesNuevo extends CommonCode {

    private EditText edit_descripcion;
    private Button button_agregar;

    private String descripcion;
    private int cita_id;
    private ProgressBar progressBar;
    private String paciente_nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observaciones_nuevo);

        super.Listener();

        cita_id = getIntent().getIntExtra("cita_id", 0);
        paciente_nombre = getIntent().getStringExtra("paciente_nombre");

        button_agregar = (Button)findViewById(R.id.button_ObservacionesNuevo_agregar);
        button_agregar.setOnClickListener(this);

        edit_descripcion =  (EditText) findViewById(R.id.edit_ObservacionRegistrar_description);
        Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "onCreate terminado");

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View v){
        super.onClick(v);
        switch (v.getId()) {

            case R.id.button_ObservacionesNuevo_agregar:

                descripcion = edit_descripcion.getText().toString();
                dbCitaObservacionesNuevo db = new dbCitaObservacionesNuevo(this,progressBar,descripcion,cita_id);

                db.execute("");
                break;
        }
    }

    public void openObservacion(Context applicationContext) {
        Intent intent_ObservacionesInicio = new Intent(getApplicationContext(), ActivityCitaObservacionesInicio.class);
        intent_ObservacionesInicio.putExtra("cita_id",cita_id);
        intent_ObservacionesInicio.putExtra("paciente_nombre",paciente_nombre);
        startActivity(intent_ObservacionesInicio);
    }
}
