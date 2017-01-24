package com.example.ediloaz.control07.Medicos;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.R;

public class ActivityMedicosVista extends CommonCode {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medico_vista);
        super.Listener();

        String nombre = getIntent().getStringExtra("nombre");
        String apellido1 = getIntent().getStringExtra("apellido1");
        String apellido2 = getIntent().getStringExtra("apellido2");
        String codigo = getIntent().getStringExtra("codigo");
        String cedula = getIntent().getStringExtra("cedula");
        String nacionalidad = getIntent().getStringExtra("nacionalidad");
        String email = getIntent().getStringExtra("email");

        int id = getIntent().getIntExtra("id",0);

        int activo = getIntent().getIntExtra("activo",0);

        TextView txtNombre = (TextView) findViewById(R.id.text_RegistrarMedico_name);
        TextView txtApellido1 = (TextView) findViewById(R.id.text_RegistrarMedico_lastname1);
        TextView txtApellido2 = (TextView) findViewById(R.id.text_RegistarMedico_lastname2);
        TextView txtCedula = (TextView) findViewById(R.id.text_data_RegistarMedico_ced_number);
        TextView txtCodigo = (TextView) findViewById(R.id.text_data_RegistarMedico_cod_number);
        TextView txtNacionalidad = (TextView) findViewById(R.id.text_data_RegistarMedico_nacionalidad);
        TextView txtEmail = (TextView) findViewById(R.id.text_data_RegistarMedico_email);
        TextView txtActivo = (TextView) findViewById(R.id.text_Medico_activo);

        txtNombre.setText(nombre);
        txtApellido1.setText(apellido1);
        txtApellido2.setText(apellido2);
        txtCedula.setText(cedula);
        txtCodigo.setText(codigo);
        txtNacionalidad.setText(nacionalidad);
        txtEmail.setText(email);

        if(activo == 0){
            txtActivo.setText("Activo");
        }else{
            txtActivo.setText("Inactivo ");
        }

    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}
