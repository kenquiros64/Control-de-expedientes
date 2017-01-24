package com.example.ediloaz.control07.Observaciones.Pacientes;

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

import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.Observaciones.Observacion;
import com.example.ediloaz.control07.R;

import java.util.ArrayList;

public class ActivityPacienteObservacionesInicio extends CommonCode {
    private Button button_new;
    private TableLayout tablapos;

    private ProgressBar progressBar;

    private int paciente_id;
    private String paciente_nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observaciones_inicio);

        paciente_id = getIntent().getIntExtra("paciente_id", 1);
        paciente_nombre = getIntent().getStringExtra("paciente_nombre");

        TextView textList = (TextView) findViewById(R.id.text_Observaciones_list);
        TextView textPaciente = (TextView) findViewById(R.id.text_Observaciones_Paciente);

        textList.setText(textList.getText() + " de paciente");
        textPaciente.setText("Paciente: " + paciente_nombre);

        super.Listener();

        button_new = (Button)findViewById(R.id.button_Observaciones_Nuevo);
        button_new.setOnClickListener(this);

        tablapos = (TableLayout)findViewById(R.id.table_Observaciones_list);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        dbListadoPacienteObservaciones db = new dbListadoPacienteObservaciones(this,progressBar,paciente_id);
        db.execute("");
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.button_Observaciones_Nuevo:
                Intent intent_ObservacionesNuevo = new Intent(getApplicationContext(), ActivityPacienteObservacionesNuevo.class);
                intent_ObservacionesNuevo.putExtra("paciente_id",paciente_id);
                intent_ObservacionesNuevo.putExtra("paciente_nombre",paciente_nombre);
                startActivity(intent_ObservacionesNuevo);
                break;
        }
    }

    public void llenarTabla(ArrayList<Observacion> matriz_datos){

        if(tablapos.getChildCount()>1) {
            Log.i("fslog", "nrows=" + tablapos.getChildCount());
            int filas = tablapos.getChildCount();
            tablapos.removeViews(1, filas - 1);
        }

        for(int i=0; i<matriz_datos.size() ; i++){
            TableRow fila = new TableRow(this);
            if(i%2 == 0) fila.setBackgroundColor(Color.parseColor("#FAFAFA"));
            else fila.setBackgroundColor(Color.parseColor("#D6D7D7"));

            final Observacion observacion = matriz_datos.get(i);

            TextView tv1 = new TextView(this);

            Button   button_mostrar = new Button(this);
            Button   button_editar = new Button(this);
            //Button   button_eliminar = new Button(this);
            if(observacion.getDescripcion().length() >= 75){
                tv1.setText(observacion.getDescripcion().substring(0,50) + "...");
            }else{
                tv1.setText(observacion.getDescripcion());
            }
            button_mostrar.setText("Mostrar");
            button_editar.setText("Editar");
            //button_eliminar.setText("Eliminar");

            button_mostrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ActivityPacienteObservacionesInicio.this, ActivityPacienteObservacionMostrar.class);
                    intent.putExtra("paciente_nombre", paciente_nombre);
                    intent.putExtra("observacion_descripcion",observacion.getDescripcion());
                    startActivity(intent);
                }
            });

            button_editar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ActivityPacienteObservacionesInicio.this, ActivityPacienteObservacionEditar.class);
                    intent.putExtra("paciente_nombre", paciente_nombre);
                    intent.putExtra("paciente_id", paciente_id);
                    intent.putExtra("observacion_id", observacion.getId());
                    intent.putExtra("observacion_descripcion",observacion.getDescripcion());
                    startActivity(intent);
                }
            });

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                button_mostrar.setAllCaps(false);
                button_editar.setAllCaps(false);
                //button_eliminar.setAllCaps(false);
            }
            tv1.setTextSize(16);
            fila.addView(tv1);
            fila.addView(button_mostrar);
            fila.addView(button_editar);
            //fila.addView(button_eliminar);
            fila.setPadding(0,10,0,10);

            tablapos.addView(fila);
        }

    }

}
