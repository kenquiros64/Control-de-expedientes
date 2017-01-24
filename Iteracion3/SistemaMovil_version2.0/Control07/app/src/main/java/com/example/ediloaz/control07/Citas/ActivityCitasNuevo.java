package com.example.ediloaz.control07.Citas;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.Pacientes.Paciente;
import com.example.ediloaz.control07.Pacientes.dbPacientesListado;
import com.example.ediloaz.control07.R;

import java.util.ArrayList;

public class ActivityCitasNuevo extends CommonCode {
    Button button_search, button_eliminar, button_mostrar, button_editar;
    TableLayout tablapos;
    EditText edit_buscar;
    String[][] matriz_datos, matriz_datos_list;
    public ProgressBar progressBar;
    private String nombre_completo_paciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas_nuevo);

        super.Listener();
        edit_buscar =  (EditText) findViewById(R.id.edit_CitasNuevo_Buscar);

        button_search = (Button)findViewById(R.id.button_CitasNuevo_Buscar);
        button_search.setOnClickListener(this);

        tablapos = (TableLayout)findViewById(R.id.table_CitasNuevo_list);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

    }



    @Override
    public void onClick(View v){
        super.onClick(v);
        switch (v.getId())
        {
        case R.id.button_CitasNuevo_Buscar:
            tablapos.removeAllViews();

            String texto_a_buscar = edit_buscar.getText().toString();

            dbPacientesListado db = new dbPacientesListado(this,progressBar,texto_a_buscar);
            db.setActivity("cita");

            InputMethodManager imm = (InputMethodManager)getSystemService(this.getApplicationContext().INPUT_METHOD_SERVICE);

            imm.hideSoftInputFromWindow(edit_buscar.getWindowToken(),
                    InputMethodManager.RESULT_UNCHANGED_SHOWN);
            db.execute();

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
            Log.v("Mensaje para yo", "0");
            TableRow fila = new TableRow(this);
            if(i%2 == 0) fila.setBackgroundColor(Color.parseColor("#FAFAFA"));
            else fila.setBackgroundColor(Color.parseColor("#D6D7D7"));
            Log.v("Mensaje para yo", "1");
            TextView tv_fecha = new TextView(this);
            TextView tv_hora = new TextView(this);
            Button   button_mostrar = new Button(this);
            Log.v("Mensaje para yo", "2");
            tv_fecha.setText(matriz_datos.get(i).getCedula());
            tv_hora.setText(matriz_datos.get(i).getNombre());
            Log.v("Mensaje para yo", "3");
            button_mostrar.setText("Mostrar citas");
            button_mostrar.setAllCaps(false);
            Log.v("Mensaje para yo", "4");
            tv_fecha.setTextSize(16);
            tv_hora.setTextSize(16);
            Log.v("Mensaje para yo", "5");

            final Paciente paciente = matriz_datos.get(i);

            button_mostrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ActivityCitasNuevo.this, ActivityCitasListadoPaciente.class);
                    intent.putExtra("id", paciente.getId());

                    Log.i("asdhbadCITASNUEVO",""+paciente.getId());

                    nombre_completo_paciente = paciente.getNombre() + " " + paciente.getApellido1() + " " +  paciente.getApellido2();
                    intent.putExtra("paciente", nombre_completo_paciente);
                    startActivity(intent);
                }
            });
            fila.addView(tv_fecha);
            fila.addView(tv_hora);
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "after define reps");
            fila.addView(button_mostrar);
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "after add elements to view");
            fila.setPadding(0,10,0,10);
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "after set padding");
            tablapos.addView(fila);
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "after end conditional");
        }
    }
}
