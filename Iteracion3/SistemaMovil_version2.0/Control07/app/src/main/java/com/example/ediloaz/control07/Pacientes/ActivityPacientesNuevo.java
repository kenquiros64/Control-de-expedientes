package com.example.ediloaz.control07.Pacientes;

import android.content.Context;
import android.content.Intent;
        import android.os.Bundle;
        import android.support.v4.app.FragmentManager;
        import android.support.v7.app.ActionBarActivity;
        import android.util.Log;
        import android.view.View;
        import android.app.DatePickerDialog;
        import android.app.Dialog;
        import android.app.DialogFragment;
        import android.os.Bundle;
import android.view.WindowManager;
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
        import com.example.ediloaz.control07.Medicos.dbMedicosNuevo;
        import com.example.ediloaz.control07.R;
import com.example.ediloaz.control07.SessionManager;

import android.os.Bundle;
        import java.text.DateFormat;
        import java.util.Calendar;
        import java.util.Date;
        import java.util.GregorianCalendar;

public class ActivityPacientesNuevo extends ActionBarActivity implements View.OnClickListener{
    private Calendar calendar = Calendar.getInstance();
    private TextView text_birth, text_death ;
    private EditText edit_name, edit_lastname1, edit_lastname2, edit_banknote, edit_email, edit_address, edit_telephone, edit_birth, edit_death;
    private String name, lastname1, lastname2, banknote, email, address, telephone, nationality, sex, birth, death;
    private int option;
    private ProgressBar progressBar;
    private Spinner spinner_nationality, spinner_sex;
    private Button button_birthday, button_death, button_agregar;
    private Button pacientes_button, medicos_button, enfermedades_button, citas_button, logout_button;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacientes_nuevo);
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

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        button_birthday = (Button)findViewById(R.id.button_RegistrarPaciente_birth);
        button_birthday.setOnClickListener(this);
        button_death = (Button)findViewById(R.id.button_RegistrarPaciente_death);
        button_death.setOnClickListener(this);
        button_agregar = (Button)findViewById(R.id.button_RegistrarPaciente_registrar);
        button_agregar.setOnClickListener(this);

        edit_name =  (EditText) findViewById(R.id.edit_RegistrarPaciente_name);
        edit_lastname1 =  (EditText) findViewById(R.id.edit_RegistrarPaciente_lastname1);
        edit_lastname2 =  (EditText) findViewById(R.id.edit_RegistrarPaciente_lastname2);
        edit_banknote =  (EditText) findViewById(R.id.edit_RegistrarPaciente_ced_number);
        edit_email =  (EditText) findViewById(R.id.edit_RegistrarPaciente_email);
        edit_telephone =  (EditText) findViewById(R.id.edit_RegistrarPaciente_telephone);
        spinner_nationality = (Spinner)findViewById(R.id.spinner_RegistrarPaciente_nacionality);
        spinner_sex = (Spinner)findViewById(R.id.spinner_RegistrarPaciente_gender);
        text_birth = (TextView)findViewById(R.id.text_RegistrarPaciente_birthday_date_answer);
        text_death = (TextView)findViewById(R.id.text_RegistrarPaciente_death_answer);
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
            case R.id.button_RegistrarPaciente_birth:
                option = 1;
                new DatePickerDialog(ActivityPacientesNuevo.this,listener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.button_RegistrarPaciente_death:
                option = 2;
                new DatePickerDialog(ActivityPacientesNuevo.this,listener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.button_RegistrarPaciente_registrar:

                name = edit_name.getText().toString();
                lastname1 = edit_lastname1.getText().toString();
                lastname2 = edit_lastname2.getText().toString();
                banknote = edit_banknote.getText().toString();
                email = edit_email.getText().toString();
                telephone  = edit_telephone.getText().toString();
                birth = text_birth.getText().toString();
                death = text_birth.getText().toString();
                nationality = String.valueOf(spinner_nationality.getSelectedItem());
                sex = String.valueOf(spinner_sex.getSelectedItem());

                Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "1");
                try{
                    dbPacientesNuevo db = new dbPacientesNuevo(this,progressBar,name, lastname1, lastname2, banknote, email, address, telephone, nationality, sex, birth, death);
                    db.execute();
                    Log.w("_A_B_C_A_A_A_A_A_A_A_A_", "2"+telephone+":"+email);

                    if(!telephone.equals("")){
                        dbEnlacesPaciente db_telefono = new dbEnlacesPaciente(-2,"telefono","insert",telephone, "");
                        db_telefono.execute("").get();
                    }
                    if(!email.equals("")){
                        dbEnlacesPaciente db_correo = new dbEnlacesPaciente(-2,"correo","insert",email, "");
                        db_correo.execute("").get();
                    }

                } catch (Exception e) {
                    Intent intent_Ingresar = new Intent(this.getApplicationContext(), ActivityEnfermedadesInicio.class);
                    startActivity(intent_Ingresar);
                    finish();
                }



                break;
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
