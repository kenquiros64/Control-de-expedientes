package com.example.ediloaz.control07.Pacientes;

import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.Enfermedades.ActivityEnfermedadesInicio;
import com.example.ediloaz.control07.Enfermedades.Enfermedad;
import com.example.ediloaz.control07.Enfermedades.dbEnfermedadesSpinner;
import com.example.ediloaz.control07.Observaciones.Pacientes.ActivityPacienteObservacionesInicio;
import com.example.ediloaz.control07.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.ediloaz.control07.R.id.email;

public class ActivityPacientesVista extends CommonCode {
    private Spinner spinner_diseases, spinner_telefonos, spinner_correos;
    private ArrayList<Enfermedad>  matriz_datos_list;
    TextView txtTelefono, txtCorreo;
    private int id;
    private Button observaciones;
    private String paciente_nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacientes_vista);
        super.Listener();

        String cedula = getIntent().getStringExtra("cedula");
        String nombre = getIntent().getStringExtra("nombre");
        String apellido1 = getIntent().getStringExtra("apellido1");
        String apellido2 = getIntent().getStringExtra("apellido2");
        String nacionalidad = getIntent().getStringExtra("nacionalidad");
        String nacimiento = getIntent().getStringExtra("nacimiento");
        String fallecimiento = getIntent().getStringExtra("fallecimiento");
        String sexo = getIntent().getStringExtra("sexo");

        id = getIntent().getIntExtra("id", 0);

        observaciones = (Button) findViewById(R.id.button_PacienteObservaciones);
        observaciones.setOnClickListener(this);

        paciente_nombre = nombre + " " + apellido1 + " " + apellido2;

        TextView txtCedula = (TextView) findViewById(R.id.text_data_RegistrarPaciente_ced_number);
        TextView txtNombre = (TextView) findViewById(R.id.text_data_RegistrarPaciente_name);
        TextView txtNacionalidad = (TextView) findViewById(R.id.text_data_RegistrarPaciente_nacionality);
        TextView txtNacimiento = (TextView) findViewById(R.id.text_data_RegistrarPaciente_birthday_date);
        TextView txtFallecimiento = (TextView) findViewById(R.id.text_data_RegistrarPaciente_death);
        TextView txtSexo = (TextView) findViewById(R.id.text_data_RegistrarPaciente_gender);
        txtTelefono = (TextView) findViewById(R.id.text_data_RegistrarPaciente_numbers_list);
        txtCorreo   = (TextView) findViewById(R.id.text_data_RegistrarPaciente_mails_list);

        txtCedula.setText(cedula);
        txtNombre.setText(nombre + " " + apellido1 + " " + apellido2 );
        txtNacionalidad.setText(nacionalidad);
        txtNacimiento.setText(nacimiento);
        txtSexo.setText(sexo);
        txtFallecimiento.setText(fallecimiento);
        if (txtFallecimiento.getText().equals("")){
            txtFallecimiento.setText("*No ha fallecido");
        }else{
            txtFallecimiento.setText(fallecimiento);
        }

        spinner_diseases = (Spinner)findViewById(R.id.spinner_PacientesVista_diseases);

        llenarSpinnerEnfermedades(id);
        llenarEnlacesDelPaciente(id);


    }

@Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.button_PacienteObservaciones:
                Intent intent_PacientesObservaciones = new Intent(getApplicationContext(),ActivityPacienteObservacionesInicio.class);
                intent_PacientesObservaciones.putExtra("paciente_nombre", paciente_nombre);
                intent_PacientesObservaciones.putExtra("paciente_id", id);
                startActivity(intent_PacientesObservaciones);
                break;
        }
    }

    public void llenarEnlacesDelPaciente(int pId){
        try {
            dbEnlacesPaciente db_telefono = new dbEnlacesPaciente(pId,"telefono","select","","");
            db_telefono.execute("").get();
            String telefono = db_telefono.GetResultado();
            txtTelefono.setText(telefono);

            dbEnlacesPaciente db_correo = new dbEnlacesPaciente(pId,"correo","select","","");
            db_correo.execute("").get();
            String correo = db_correo.GetResultado();
            txtCorreo.setText(correo);

        } catch (Exception e) {
            Intent intent_Ingresar = new Intent(this.getApplicationContext(), ActivityEnfermedadesInicio.class);
            startActivity(intent_Ingresar);
            finish();
        }
    }
    public void llenarSpinnerEnfermedades(int pId){
        try {
            dbEnfermedadesSpinner db = new dbEnfermedadesSpinner(pId);
            db.execute("").get();
            matriz_datos_list = db.GetMatriz();
            List<String> list = new ArrayList<String>();
            String string_temp;
            for(int i=0; i<matriz_datos_list.size(); i++){
                string_temp = matriz_datos_list.get(i).getDescripcion().toString();
                list.add(string_temp);
            }
            if (list.isEmpty()){
                list.add("No tiene enfermedades");
            }
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_diseases.setAdapter(dataAdapter);
        } catch (Exception e) {
            Intent intent_Ingresar = new Intent(this.getApplicationContext(), ActivityEnfermedadesInicio.class);
            startActivity(intent_Ingresar);
            finish();
        }
    }
}
