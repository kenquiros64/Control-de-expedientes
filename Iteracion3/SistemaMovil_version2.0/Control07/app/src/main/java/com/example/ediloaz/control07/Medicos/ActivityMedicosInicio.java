
package com.example.ediloaz.control07.Medicos;

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
import com.example.ediloaz.control07.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ActivityMedicosInicio extends CommonCode {
    private Button button_search, button_new;
    private TableLayout tablapos;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medico_inicio);
        super.Listener();

        setProgressBar((ProgressBar)findViewById(R.id.progressBar));
        getProgressBar().setVisibility(View.GONE);

        button_search = (Button)findViewById(R.id.button_Medicos_Buscar);
        button_search.setOnClickListener(this);
        button_new = (Button)findViewById(R.id.button_Medicos_Nuevo);
        button_new.setOnClickListener(this);

        tablapos = (TableLayout)findViewById(R.id.table_Medicos_list);
        Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "onCreate exitosa");

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        dbListadoMedicos db = new dbListadoMedicos(this,progressBar,"");
        Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "Clase creada ex.");

        db.execute("");

        Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "thread hecho join");

        Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "after get matriz");

        Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "after fill the table");
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){

            case R.id.button_Medicos_Buscar:
                finish();
                Intent intent_MedicosBuscar = new Intent(getApplicationContext(),ActivityMedicosBuscar.class);
                startActivity(intent_MedicosBuscar);
                break;
            case R.id.button_Medicos_Nuevo:
                finish();
                Intent intent_MedicosNuevo = new Intent(getApplicationContext(),ActivityMedicosNuevo.class);
                startActivity(intent_MedicosNuevo);
                break;

        }
    }


    public void llenarTabla(final ArrayList<Medico> matriz_datos){

        if(tablapos.getChildCount()>1) {

            int filas = tablapos.getChildCount();
            tablapos.removeViews(1, filas - 1);
        }

        for(int i=0; i<matriz_datos.size() ; i++){
            TableRow fila = new TableRow(this);
            if(i%2 == 0) fila.setBackgroundColor(Color.parseColor("#FAFAFA"));
            else fila.setBackgroundColor(Color.parseColor("#D6D7D7"));

            TextView tv_codigo = new TextView(this);
            TextView tv_cedula = new TextView(this);
            TextView tv_nombre = new TextView(this);
            Button   button_mostrar = new Button(this);
            Button   button_editar = new Button(this);
            Button   button_eliminar = new Button(this);

            tv_codigo.setText(matriz_datos.get(i).getCodigo());
            tv_cedula.setText(matriz_datos.get(i).getCedula());
            tv_nombre.setText(matriz_datos.get(i).getNombre());

            final Medico medico = matriz_datos.get(i);

            button_mostrar.setText("Mostrar");
            button_editar.setText("Editar");

            button_mostrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ActivityMedicosInicio.this, ActivityMedicosVista.class);

                    intent.putExtra("id", medico.getId());
                    intent.putExtra("activo", medico.getActivo());
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
                    Intent intent = new Intent(ActivityMedicosInicio.this, ActivityMedicosEditar.class);

                    intent.putExtra("id", medico.getId());
                    intent.putExtra("activo", medico.getActivo());
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

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                button_mostrar.setAllCaps(false);
                button_editar.setAllCaps(false);
            }
            tv_codigo.setTextSize(16);
            tv_cedula.setTextSize(16);
            tv_nombre.setTextSize(16);

            fila.addView(tv_codigo);
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
