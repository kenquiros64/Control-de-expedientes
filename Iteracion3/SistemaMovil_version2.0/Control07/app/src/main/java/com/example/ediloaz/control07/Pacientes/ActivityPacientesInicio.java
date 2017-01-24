package com.example.ediloaz.control07.Pacientes;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.ediloaz.control07.ActivityEmpty;
import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.Enfermedades.ActivityEnfermedadesInicio;
import com.example.ediloaz.control07.Medicos.ActivityMedicosEditar;
import com.example.ediloaz.control07.Medicos.ActivityMedicosVista;
import com.example.ediloaz.control07.Medicos.Medico;
import com.example.ediloaz.control07.Medicos.dbListadoMedicos;
import com.example.ediloaz.control07.R;

import java.util.ArrayList;

public class ActivityPacientesInicio extends CommonCode {
    Button pacientes_nuevo_button, pacientes_buscar_button;
    Button button_search, button_new;
    private TableLayout tablapos;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacientes_inicio);
        super.Listener();

        setProgressBar((ProgressBar)findViewById(R.id.progressBar));
        getProgressBar().setVisibility(View.GONE);

        pacientes_nuevo_button = (Button)findViewById(R.id.button_Pacientes_Nuevo);
        pacientes_nuevo_button.setOnClickListener(this);
        pacientes_buscar_button = (Button)findViewById(R.id.button_Pacientes_Buscar);
        pacientes_buscar_button.setOnClickListener(this);

        tablapos = (TableLayout)findViewById(R.id.table_Pacientes_list);
        Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "onCreate exitosa");
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        dbPacientesListado db = new dbPacientesListado(this,progressBar,"");
        Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "Clase creada ex.");

        db.execute("");

    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.button_Pacientes_Nuevo:
                Intent intent_PacientesNuevo = new Intent(getApplicationContext(),ActivityPacientesNuevo.class);
                startActivity(intent_PacientesNuevo);
                break;
            case R.id.button_Pacientes_Buscar:
                Intent intent_PacientesBuscar = new Intent(getApplicationContext(),ActivityPacientesBuscar.class);
                startActivity(intent_PacientesBuscar);
                break;
        }
    }


    public void llenarTabla(final ArrayList<Paciente> matriz_datos){

        if(tablapos.getChildCount()>1) {

            int filas = tablapos.getChildCount();
            tablapos.removeViews(1, filas - 1);
        }

        for(int i=0; i<matriz_datos.size() ; i++){
            TableRow fila = new TableRow(this);
            if(i%2 == 0) fila.setBackgroundColor(Color.parseColor("#FAFAFA"));
            else fila.setBackgroundColor(Color.parseColor("#D6D7D7"));

            TextView tv_cedula = new TextView(this);
            TextView tv_nombre = new TextView(this);
            Button   button_mostrar = new Button(this);
            Button   button_editar = new Button(this);

            tv_cedula.setText(matriz_datos.get(i).getCedula());
            tv_nombre.setText(matriz_datos.get(i).getNombre());

            final Paciente paciente = matriz_datos.get(i);

            button_mostrar.setText("Mostrar");
            button_editar.setText("Editar");

            button_mostrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ActivityPacientesInicio.this, ActivityPacientesVista.class);

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
                    Intent intent = new Intent(ActivityPacientesInicio.this, ActivityPacientesEditar.class);
                    Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "SELECT COUNT(*) FROM MEDICOS  CON IDIDIDID" + paciente.getId());
                    intent.putExtra("id", paciente.getId());
                    intent.putExtra("cedula", paciente.getCedula());
                    intent.putExtra("nombre", paciente.getNombre());
                    intent.putExtra("apellido1", paciente.getApellido1());
                    intent.putExtra("apellido2", paciente.getApellido2());
                    intent.putExtra("nacionalidad", paciente.getNacionalidad());
                    intent.putExtra("nacimiento", paciente.getNacimiento());
                    intent.putExtra("fallecimiento", paciente.getFallecimiento());
                    intent.putExtra("sexo", paciente.getSexo());
                    Log.i("w_w_w_w_W_W_w__W_W_", "0");
                    startActivity(intent);
                    Log.i("w_w_w_w_W_W_w__W_W_", "1");
                }
            });

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                button_mostrar.setAllCaps(false);
                button_editar.setAllCaps(false);
            }
            tv_cedula.setTextSize(16);
            tv_nombre.setTextSize(16);

            fila.addView(tv_cedula);
            fila.addView(tv_nombre);

            fila.addView(button_mostrar);
            fila.addView(button_editar);

            fila.setPadding(0,10,0,10);


            tablapos.addView(fila);

        }
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }
}