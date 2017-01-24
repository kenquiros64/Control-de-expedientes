package com.example.ediloaz.control07.Enfermedades;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.ediloaz.control07.ActivityEmpty;
import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.Medicos.ActivityMedicosInicio;
import com.example.ediloaz.control07.Pacientes.ActivityPacientesInicio;
import com.example.ediloaz.control07.R;

import java.util.ArrayList;

import static android.R.attr.breadCrumbShortTitle;
import static android.R.attr.drawablePadding;
import static android.R.attr.progressBarPadding;

public class ActivityEnfermedadesInicio extends CommonCode {
    private Button button_search,button_new;
    private TableLayout tablapos;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enfermedades_inicio);
        super.Listener();
        button_search = (Button)findViewById(R.id.button_Enfermedades_Buscar);
        button_search.setOnClickListener(this);
        button_new = (Button)findViewById(R.id.button_Enfermedades_Nuevo);
        button_new.setOnClickListener(this);
        tablapos = (TableLayout)findViewById(R.id.table_Enfermedades_list);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        dbListadoEnfermedades db = new dbListadoEnfermedades(this,progressBar,"");
        db.execute("");
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.button_Enfermedades_Buscar:
                Intent intent_EnfermedadesBuscar = new Intent(getApplicationContext(), ActivityEnfermedadesBuscar.class);
                startActivity(intent_EnfermedadesBuscar);
                break;
            case R.id.button_Enfermedades_Nuevo:
                Intent intent_EnfermedadesNuevo = new Intent(getApplicationContext(), ActivityEnfermedadesNuevo.class);
                startActivity(intent_EnfermedadesNuevo);
                break;
        }
    }

    public void llenarTabla(ArrayList<Enfermedad> matriz_datos){

        if(tablapos.getChildCount()>1) {
            Log.i("fslog", "nrows=" + tablapos.getChildCount());
            int filas = tablapos.getChildCount();
            tablapos.removeViews(1, filas - 1);
        }

        for(int i=0; i<matriz_datos.size() ; i++){
            TableRow fila = new TableRow(this);
            if(i%2 == 0) fila.setBackgroundColor(Color.parseColor("#FAFAFA"));
            else fila.setBackgroundColor(Color.parseColor("#D6D7D7"));

            TextView tv1 = new TextView(this);
            TextView tv2 = new TextView(this);
            Button   button_mostrar = new Button(this);
            Button   button_editar = new Button(this);
            //Button   button_eliminar = new Button(this);
            tv1.setText(matriz_datos.get(i).getCodigo());
            tv2.setText(matriz_datos.get(i).getDescripcion());
            button_mostrar.setText("Mostrar");
            button_editar.setText("Editar");
            //button_eliminar.setText("Eliminar");


            final Enfermedad enfermedad = matriz_datos.get(i);

            button_mostrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ActivityEnfermedadesInicio.this, ActivityEnfermedadesVista.class);

                    intent.putExtra("codigo", enfermedad.getCodigo());
                    intent.putExtra("descripcion", enfermedad.getDescripcion());
                    startActivity(intent);
                }
            });

            button_editar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ActivityEnfermedadesInicio.this, ActivityEnfermedadesEditar.class);

                    intent.putExtra("codigo", enfermedad.getCodigo());
                    intent.putExtra("descripcion", enfermedad.getDescripcion());
                    intent.putExtra("id", enfermedad.getId());
                    startActivity(intent);
                }
            });

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                button_mostrar.setAllCaps(false);
                button_editar.setAllCaps(false);
                //button_eliminar.setAllCaps(false);
            }
            tv1.setTextSize(16);
            tv2.setTextSize(16);
            fila.addView(tv1);
            fila.addView(tv2);
            fila.addView(button_mostrar);
            fila.addView(button_editar);
            //fila.addView(button_eliminar);
            fila.setPadding(0,10,0,10);

            tablapos.addView(fila);
        }

    }
}
