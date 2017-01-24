package com.example.ediloaz.control07.Medicos;

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
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.ediloaz.control07.ActivityEmpty;
import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.Enfermedades.ActivityEnfermedadesInicio;
import com.example.ediloaz.control07.Enfermedades.dbListadoEnfermedades;
import com.example.ediloaz.control07.Pacientes.ActivityPacientesInicio;
import com.example.ediloaz.control07.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static android.R.id.edit;

public class ActivityMedicosBuscar extends CommonCode {
    private EditText edit_buscar;
    private Button button_search;
    private TableLayout tablapos;

    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medico_buscar);
        super.Listener();

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        edit_buscar =  (EditText) findViewById(R.id.edit_MedicosBuscar_buscador);
        button_search = (Button)findViewById(R.id.button_MedicosBuscar_Buscar);
        button_search.setOnClickListener(this);

        tablapos = (TableLayout)findViewById(R.id.table_MedicosBuscar_list);
    }


    @Override
    public void onClick(View v){
        super.onClick(v);
        switch (v.getId())
        {

        case R.id.button_MedicosBuscar_Buscar:
            tablapos.removeAllViews();
            Log.i("_A_A_A_A_A_A_A_A_A_", "10");
            String texto_a_buscar = edit_buscar.getText().toString();
            Log.i("_A_A_A_A_A_A_A_A_A_", "11"+texto_a_buscar);
            dbListadoMedicos db = new dbListadoMedicos(this,progressBar,texto_a_buscar);
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
    public void llenarTabla(ArrayList<Medico> matriz_datos){
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
            TextView tv_codigo = new TextView(this);
            TextView tv_cedula = new TextView(this);
            TextView tv_nombre = new TextView(this);
            Button button_mostrar = new Button(this);
            Button   button_editar = new Button(this);
            Button   button_eliminar = new Button(this);
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "after define buttons");
            tv_codigo.setText(matriz_datos.get(i).getCodigo());
            tv_cedula.setText(matriz_datos.get(i).getCedula());
            tv_nombre.setText(matriz_datos.get(i).getNombre());
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "after set text");
            button_mostrar.setText("Mostrar");
            button_editar.setText("Editar");
            button_mostrar.setAllCaps(false);
            button_editar.setAllCaps(false);
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "after allcaps");
            tv_codigo.setTextSize(16);
            tv_cedula.setTextSize(16);
            tv_nombre.setTextSize(16);
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "after define size");

            final Medico medico = matriz_datos.get(i);

            button_mostrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ActivityMedicosBuscar.this, ActivityMedicosVista.class);

                    intent.putExtra("id", medico.getId());
                    intent.putExtra("codigo", medico.getCodigo());
                    intent.putExtra("cedula", medico.getCedula());
                    intent.putExtra("nombre", medico.getNombre());
                    intent.putExtra("apellido1", medico.getApellido1());
                    intent.putExtra("apellido2", medico.getApellido2());
                    intent.putExtra("nacionalidad", medico.getNacionalidad());
                    intent.putExtra("email", medico.getEmail());
                    startActivity(intent);
                }
            });

            button_editar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ActivityMedicosBuscar.this, ActivityMedicosEditar.class);

                    intent.putExtra("id", medico.getId());
                    intent.putExtra("codigo", medico.getCodigo());
                    intent.putExtra("cedula", medico.getCedula());
                    intent.putExtra("nombre", medico.getNombre());
                    intent.putExtra("apellido1", medico.getApellido1());
                    intent.putExtra("apellido2", medico.getApellido2());
                    intent.putExtra("nacionalidad", medico.getNacionalidad());
                    intent.putExtra("email", medico.getEmail());
                    startActivity(intent);
                }
            });

            fila.addView(tv_codigo);
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
