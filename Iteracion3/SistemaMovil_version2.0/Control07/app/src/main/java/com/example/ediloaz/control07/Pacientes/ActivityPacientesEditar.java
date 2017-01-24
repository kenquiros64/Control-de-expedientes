package com.example.ediloaz.control07.Pacientes;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ediloaz.control07.ActivityIngresar;
import com.example.ediloaz.control07.Citas.ActivityCitasInicio;
import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.Enfermedades.ActivityEnfermedadesInicio;
import com.example.ediloaz.control07.Medicos.ActivityMedicosInicio;
import com.example.ediloaz.control07.Medicos.dbMedicosEditar;
import com.example.ediloaz.control07.R;
import com.example.ediloaz.control07.SessionManager;

import java.util.Calendar;

import static com.example.ediloaz.control07.R.id.progressBar;

public class ActivityPacientesEditar extends ActionBarActivity implements View.OnClickListener {
    private String nombre, apellido1, apellido2, cedula, nacimiento, fallecimiento, sexo, nacionalidad, telefono, email, telefono_anterior, email_anterior;
    private EditText editNombre, editApellido1, editApellido2, editCedula, editTelefono, editEmail;
    private Button button_agregar,button_birthday,button_death;
    private ProgressBar progressBar;
    private Spinner editNacionalidad, editSexo;
    private TextView text_birth, text_death, editNacimiento, editFallecimiento ;
    private Calendar calendar = Calendar.getInstance();
    private int option,id;
    private SessionManager session;
    private Button pacientes_button, medicos_button, enfermedades_button, citas_button, logout_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacientes_editar);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Session Manager
        session = new SessionManager(this.getApplicationContext());
        getSession().checkLogin();

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

        Log.i("w_w_w_w_W_W_w__W_W_", "2");
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        Log.i("w_w_w_w_W_W_w__W_W_", "20");
        button_agregar = (Button)findViewById(R.id.button_PacientesEditar_registrar);
        button_agregar.setOnClickListener(this);
        button_birthday = (Button)findViewById(R.id.button_PacientesEditar_birth);
        button_birthday.setOnClickListener(this);
        button_death = (Button)findViewById(R.id.button_PacientesEditar_death);
        button_death.setOnClickListener(this);

        cedula = getIntent().getStringExtra("cedula");
        nombre = getIntent().getStringExtra("nombre");
        apellido1 = getIntent().getStringExtra("apellido1");
        apellido2 = getIntent().getStringExtra("apellido2");
        nacionalidad = getIntent().getStringExtra("nacionalidad");
        nacimiento = getIntent().getStringExtra("nacimiento");
        fallecimiento = getIntent().getStringExtra("fallecimiento");
        sexo = getIntent().getStringExtra("sexo");
        id = getIntent().getIntExtra("id", 0);
        Log.i("w_w_w_w_W_W_w__W_W_", "3");

        editCedula = (EditText) findViewById(R.id.edit_PacientesEditar_ced_number);
        editNombre = (EditText) findViewById(R.id.edit_PacientesEditar_name);
        editApellido1 = (EditText) findViewById(R.id.edit_PacientesEditar_lastname1);
        editApellido2 = (EditText) findViewById(R.id.edit_PacientesEditar_lastname2);
        editEmail = (EditText) findViewById(R.id.edit_PacientesEditar_email);
        editTelefono = (EditText) findViewById(R.id.edit_PacientesEditar_telephone);
        editNacionalidad = (Spinner) findViewById(R.id.spinner_PacientesEditar_nacionality);
        editNacimiento = (TextView) findViewById(R.id.text_PacientesEditar_birthday_date_answer);
        editFallecimiento = (TextView) findViewById(R.id.text_PacientesEditar_death_answer);
        editSexo = (Spinner) findViewById(R.id.spinner_PacientesEditar_gender);


        ArrayAdapter spinner_adapter = ArrayAdapter.createFromResource( this, R.array.nacionalidad , android.R.layout.simple_spinner_item);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editNacionalidad.setAdapter(spinner_adapter);

        ArrayAdapter spinner_adapter2 = ArrayAdapter.createFromResource( this, R.array.sex , android.R.layout.simple_spinner_item);
        spinner_adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editSexo.setAdapter(spinner_adapter2);


        Log.i("w_w_w_w_W_W_w__W_W_", "4");

        editCedula.setText(cedula);
        editNombre.setText(nombre);
        editApellido1.setText(apellido1);
        editApellido2.setText(apellido2);
        editNacimiento.setText(nacimiento);
        editNacionalidad.setSelection(spinner_adapter.getPosition(nacionalidad));
        editSexo.setSelection(spinner_adapter.getPosition(sexo));
        editFallecimiento.setText(fallecimiento);
        if (editFallecimiento.getText().equals("")){
            editFallecimiento.setText("*No ha fallecido");
        }else{
            editFallecimiento.setText(fallecimiento);
        }
        text_birth = (TextView)findViewById(R.id.text_PacientesEditar_birthday_date_answer);
        text_death = (TextView)findViewById(R.id.text_PacientesEditar_death_answer);

        llenarEnlacesDelPaciente(id);
        Log.i("w_w_w_w_W_W_w__W_W_", "5");
    }



    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
            if (option==1) text_birth.setText(year+ "/" + (monthOfYear+1) + "/" + dayOfMonth);
            else if (option==2) text_death.setText(year+ "/" + (monthOfYear+1) + "/" + dayOfMonth);
        }
    };

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
            case R.id.button_PacientesEditar_birth:
                option = 1;
                new DatePickerDialog(ActivityPacientesEditar.this,listener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.button_PacientesEditar_death:
                option = 2;
                new DatePickerDialog(ActivityPacientesEditar.this,listener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.button_PacientesEditar_registrar:
                Log.i("w_w_w_w_W_W_w__W_W_", "10");
                nombre = editNombre.getText().toString();
                Log.i("w_w_w_w_W_W_w__W_W_", "11");
                apellido1 = editApellido1.getText().toString();
                apellido2 = editApellido2.getText().toString();
                Log.i("w_w_w_w_W_W_w__W_W_", "12");
                cedula = editCedula.getText().toString();
                email = editEmail.getText().toString();
                Log.i("w_w_w_w_W_W_w__W_W_", "13");
                telefono  = editTelefono.getText().toString();
                nacimiento = text_birth.getText().toString();
                Log.i("w_w_w_w_W_W_w__W_W_", "14");
                fallecimiento = text_birth.getText().toString();
                Log.i("w_w_w_w_W_W_w__W_W_", "15");
                nacionalidad = String.valueOf(editNacionalidad.getSelectedItem());
                Log.i("w_w_w_w_W_W_w__W_W_", "16");
                sexo = String.valueOf(editSexo.getSelectedItem());
                Log.i("w_w_w_w_W_W_w__W_W_", "17");

                try {
                    dbPacientesEditar db = new dbPacientesEditar(this,progressBar,id,nombre,apellido1, apellido2,cedula,nacionalidad,sexo,nacimiento,fallecimiento);
                    db.execute();
                    Log.w("w_w_w_w_W_W_w__W_W_", "2");

                        dbEnlacesPaciente db_telefono = new dbEnlacesPaciente(id,"telefono","update",telefono, telefono_anterior);
                        db_telefono.execute("").get();

                        dbEnlacesPaciente db_correo = new dbEnlacesPaciente(id,"correo","update",email, email_anterior);
                        db_correo.execute("").get();


                } catch (Exception e) {
                    Intent intent_Ingresar = new Intent(this.getApplicationContext(), ActivityEnfermedadesInicio.class);
                    startActivity(intent_Ingresar);
                    finish();
                }

                break;
        }
    }

    public void llenarEnlacesDelPaciente(int pId){
        try {
            dbEnlacesPaciente db_telefono = new dbEnlacesPaciente(pId,"telefono","select","","");
            db_telefono.execute("").get();
            telefono_anterior = db_telefono.GetResultado();
            editTelefono.setText(telefono_anterior);


            dbEnlacesPaciente db_correo = new dbEnlacesPaciente(pId,"correo","select","","");
            db_correo.execute("").get();
            email_anterior = db_correo.GetResultado();
            editEmail.setText(email_anterior);

        } catch (Exception e) {
            Intent intent_Ingresar = new Intent(this.getApplicationContext(), ActivityEnfermedadesInicio.class);
            startActivity(intent_Ingresar);
            finish();
        }
    }
    public void openPacientes(Context context){
        Intent intent_Ingresar = new Intent(context, ActivityPacientesInicio.class);
        startActivity(intent_Ingresar);
    }

    public SessionManager getSession() {
        return session;
    }

}
