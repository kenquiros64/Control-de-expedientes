package com.example.ediloaz.control07.Medicos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ediloaz.control07.ActivityEmpty;
import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.Enfermedades.ActivityEnfermedadesInicio;
import com.example.ediloaz.control07.Pacientes.ActivityPacientesInicio;
import com.example.ediloaz.control07.R;

import java.util.concurrent.ExecutionException;

public class ActivityMedicosNuevo extends CommonCode {

    private EditText edit_nombre, edit_apellido1, edit_apellido2, edit_cedula, edit_codigo, edit_email, edit_password_1;
    private CheckBox check_activo;
    private Button button_agregar;
    private String nombre, apellido1, apellido2, cedula, codigo, email, password, nacionalidad;
    private boolean activo;


    private ProgressBar progressBar;
    private Spinner spinner_nacionalidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicos_nuevo);
        super.Listener();

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        button_agregar = (Button)findViewById(R.id.button_RegistarMedico_registrar);
        button_agregar.setOnClickListener(this);

        spinner_nacionalidad = (Spinner) findViewById(R.id.spinner_nacionalidad);
        ArrayAdapter spinner_adapter = ArrayAdapter.createFromResource( this, R.array.nacionalidad , android.R.layout.simple_spinner_item);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_nacionalidad.setAdapter(spinner_adapter);

        edit_nombre =  (EditText) findViewById(R.id.edit_RegistrarMedico_name);
        edit_apellido1 =  (EditText) findViewById(R.id.edit_RegistrarMedico_lastname1);
        edit_apellido2 =  (EditText) findViewById(R.id.edit_RegistarMedico_lastname2);
        edit_cedula =  (EditText) findViewById(R.id.edit_RegistarMedico_ced_number);
        edit_codigo =  (EditText) findViewById(R.id.edit_RegistarMedico_cod_number);
        edit_email =  (EditText) findViewById(R.id.edit_RegistarMedico_email);
        edit_password_1 =  (EditText) findViewById(R.id.edit_RegistarMedico_password);

        check_activo =  (CheckBox) findViewById(R.id.checkBoxActivo);
        Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "0");
    }


    private boolean validarPassword(){

        // Reset errors.
        edit_password_1.setError(null);

        boolean cancel = false;
        View focusView = null;

        if(password.length() >= 0 && password.length() < 6){
            cancel = true;
            focusView = edit_password_1;
            edit_password_1.setError("Contraseña debe contener al menos 6 carácteres.");
            focusView.requestFocus();
        }
        return cancel;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.button_RegistarMedico_registrar:

                    nombre = edit_nombre.getText().toString();
                    apellido1 = edit_apellido1.getText().toString();
                    apellido2 = edit_apellido2.getText().toString();
                    cedula = edit_cedula.getText().toString();
                    codigo = edit_codigo.getText().toString();
                    email = edit_email.getText().toString();
                    password  = edit_password_1.getText().toString();
                    nacionalidad = spinner_nacionalidad.getSelectedItem().toString();
                    activo = check_activo.isChecked();

                    if(!validarPassword()){
                        Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "1");
                        dbMedicosNuevo db = new dbMedicosNuevo(this,progressBar,nombre,apellido1,
                                apellido2,cedula,codigo,email,password,nacionalidad,activo);
                        Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "2");

                        db.execute();
                    }

                    Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "3");


                break;
        }
    }
}
