package com.example.ediloaz.control07.Enfermedades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.ediloaz.control07.ActivityEmpty;
import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.Medicos.ActivityMedicosInicio;
import com.example.ediloaz.control07.Pacientes.ActivityPacientesInicio;
import com.example.ediloaz.control07.R;

import java.util.ArrayList;

public class ActivityEnfermedadesNuevo extends CommonCode {
    private EditText edit_codigo, edit_descripcion;
    private Button button_agregar;

    private String codigo, descripcion;
    private ProgressBar progressBar;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enfermedades_nuevo);
        super.Listener();
        button_agregar = (Button)findViewById(R.id.button_EnfermedadesNuevo_agregar);
        button_agregar.setOnClickListener(this);
        edit_codigo =  (EditText) findViewById(R.id.edit_EnfermedadesRegistrar_code);
        edit_descripcion =  (EditText) findViewById(R.id.edit_EnfermedadesRegistrar_description);
        Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "onCreate terminado");


        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
    }


    @Override
    public void onClick(View v){
        super.onClick(v);
        switch (v.getId()) {

            case R.id.button_EnfermedadesNuevo_agregar:

                codigo = edit_codigo.getText().toString();
                descripcion = edit_descripcion.getText().toString();
                dbEnfermedadesNuevo db = new dbEnfermedadesNuevo(this,progressBar,codigo,descripcion);

                db.execute();
            break;
        }
    }
}
