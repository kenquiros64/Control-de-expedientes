package com.example.ediloaz.control07.Pacientes;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.ediloaz.control07.ActivityIngresar;
import com.example.ediloaz.control07.Citas.ActivityCitasInicio;
import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.Enfermedades.ActivityEnfermedadesInicio;
import com.example.ediloaz.control07.Medicos.ActivityMedicosBuscar;
import com.example.ediloaz.control07.Medicos.ActivityMedicosEditar;
import com.example.ediloaz.control07.Medicos.ActivityMedicosInicio;
import com.example.ediloaz.control07.Medicos.ActivityMedicosVista;
import com.example.ediloaz.control07.Medicos.Medico;
import com.example.ediloaz.control07.Medicos.dbListadoMedicos;
import com.example.ediloaz.control07.R;

import java.util.ArrayList;

public class ActivityPacientesBuscar extends CommonCode {
    EditText edit_buscar;
    Button button_search;
    TableLayout tablapos;
    Spinner spinner_list;
    String[][] matriz_datos, matriz_datos_list;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacientes_buscar);
        super.Listener();
        edit_buscar =  (EditText) findViewById(R.id.edit_PacientesBuscar_Buscar);
        button_search = (Button)findViewById(R.id.button_PacientesBuscar_Buscar);
        button_search.setOnClickListener(this);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        tablapos = (TableLayout)findViewById(R.id.table_PacientesBuscar_list);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.dashboard_Pacientes:
                Intent intent_Pacientes = new Intent(getApplicationContext(),ActivityPacientesInicio.class);
                startActivity(intent_Pacientes);
                break;
            case R.id.dashboard_Medicos:
                Intent intent_Medicos = new Intent(getApplicationContext(),ActivityMedicosInicio.class);
                startActivity(intent_Medicos);
                break;
            case R.id.dashboard_Enfermedades:
                Intent intent_Enfermedades= new Intent(getApplicationContext(),ActivityEnfermedadesInicio.class);
                startActivity(intent_Enfermedades);
                break;
            case R.id.dashboard_Citas:
                Intent intent_Citas = new Intent(getApplicationContext(),ActivityCitasInicio.class);
                startActivity(intent_Citas);
                break;
            case R.id.dashboard_LogOut:
                Intent intent_LogOut = new Intent(getApplicationContext(),ActivityIngresar.class);
                startActivity(intent_LogOut);
                break;
            case R.id.button_PacientesBuscar_Buscar:
                tablapos.removeAllViews();
                Log.i("_A_A_A_A_A_A_A_A_A_", "10");
                String texto_a_buscar = edit_buscar.getText().toString();
                Log.i("_A_A_A_A_A_A_A_A_A_", "11"+texto_a_buscar);
                dbPacientesListado db = new dbPacientesListado(this, progressBar,texto_a_buscar);
                Log.i("_A_A_A_A_A_A_A_A_A_", "12");
                InputMethodManager imm = (InputMethodManager)getSystemService(this.getApplicationContext().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(edit_buscar.getWindowToken(),
                        InputMethodManager.RESULT_UNCHANGED_SHOWN);
                db.execute();
                Log.i("_A_A_A_A_A_A_A_A_A_", "3");
                break;
        }
    }




    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void llenarTabla(ArrayList<Paciente> matriz_datos){
        if(tablapos.getChildCount()>1) {
            Log.i("fslog", "nrows=" + tablapos.getChildCount());
            int filas = tablapos.getChildCount();
            tablapos.removeViews(1, filas - 1);
        }
        Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "after condition");
        for(int i=0; i<matriz_datos.size() ; i++){
            TableRow fila = new TableRow(this);
            if(i%2 == 0) fila.setBackgroundColor(Color.parseColor("#FAFAFA"));
            else fila.setBackgroundColor(Color.parseColor("#D6D7D7"));
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "after set colours");
            TextView tv_cedula = new TextView(this);
            TextView tv_nombre = new TextView(this);
            Button button_mostrar = new Button(this);
            Button   button_editar = new Button(this);
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "after define buttons");
            tv_cedula.setText(matriz_datos.get(i).getCedula());
            tv_nombre.setText(matriz_datos.get(i).getNombre());
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "after set text");
            button_mostrar.setText("Mostrar");
            button_editar.setText("Editar");
            button_mostrar.setAllCaps(false);
            button_editar.setAllCaps(false);
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "after allcaps");
            tv_cedula.setTextSize(16);
            tv_nombre.setTextSize(16);
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "after define size");

            final Paciente paciente = matriz_datos.get(i);

            button_mostrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ActivityPacientesBuscar.this, ActivityPacientesVista.class);

                    intent.putExtra("id", paciente.getId());
                    intent.putExtra("cedula", paciente.getCedula());
                    intent.putExtra("nombre", paciente.getNombre());
                    intent.putExtra("apellido1", paciente.getApellido1());
                    intent.putExtra("apellido2", paciente.getApellido2());
                    intent.putExtra("nacionalidad", paciente.getNacionalidad());
                    intent.putExtra("nacimiento", paciente.getNacimiento());
                    intent.putExtra("fallecimiento", paciente.getFallecimiento());
                    intent.putExtra("sexo", paciente.getSexo());
                    startActivity(intent);
                }
            });

            button_editar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ActivityPacientesBuscar.this, ActivityPacientesEditar.class);

                    intent.putExtra("id", paciente.getId());
                    intent.putExtra("cedula", paciente.getCedula());
                    intent.putExtra("nombre", paciente.getNombre());
                    intent.putExtra("apellido1", paciente.getApellido1());
                    intent.putExtra("apellido2", paciente.getApellido2());
                    intent.putExtra("nacionalidad", paciente.getNacionalidad());
                    intent.putExtra("nacimiento", paciente.getNacimiento());
                    intent.putExtra("fallecimiento", paciente.getFallecimiento());
                    intent.putExtra("sexo", paciente.getSexo());
                    startActivity(intent);
                }
            });

            fila.addView(tv_cedula);
            fila.addView(tv_nombre);
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "after define reps");
            fila.addView(button_mostrar);
            fila.addView(button_editar);
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "after add elements to view");
            fila.setPadding(0,10,0,10);
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "after set padding");

            tablapos.addView(fila);

            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "after end conditional");
        }

    }

}
