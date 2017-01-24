package com.example.ediloaz.control07.Enfermedades;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityEnfermedadesBuscar extends CommonCode {

    private Button button_search;
    private TableLayout tablapos;
    private Spinner spinner_list;
    private ArrayList<Enfermedad>  matriz_datos_list;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enfermedades_buscar);
        super.Listener();
        tablapos = (TableLayout)findViewById(R.id.table_EnfermedadesBuscar_list);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        button_search = (Button)findViewById(R.id.button_EnfermedadesBuscar_Buscar);
        button_search.setOnClickListener(this);

        spinner_list = (Spinner)findViewById(R.id.spinner_EnfermedadesBuscar_list);
        llenarSpinnerEnfermedades();
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.button_EnfermedadesBuscar_Buscar:

                String descripcion_a_buscar = String.valueOf(spinner_list.getSelectedItem());
                Log.i("_A_A_A_A_A_A_A_A_A_",descripcion_a_buscar);
                dbListadoEnfermedades db = new dbListadoEnfermedades(this,progressBar,descripcion_a_buscar);

                db.execute("");

                Log.i("_A_A_A_A_A_A_A_A_A_","3");
                Log.i("_A_A_A_A_A_A_A_A_A_","4");
                Log.i("_A_A_A_A_A_A_A_A_A_","5");

                break;

        }
    }

    public void llenarTabla(ArrayList<Enfermedad> matriz_datos){
        Log.i("_A_A_A_A_A_A_A_A_A_","6");
        if(tablapos.getChildCount()>1) {
            Log.i("fslog", "nrows=" + tablapos.getChildCount());
            int filas = tablapos.getChildCount();
            tablapos.removeViews(1, filas - 1);
        }
        Log.i("_A_A_A_A_A_A_A_A_A_","7");
        for(int i=0; i<matriz_datos.size(); i++){
            TableRow fila = new TableRow(this);
            if(i%2 == 0) fila.setBackgroundColor(Color.parseColor("#FAFAFA"));
            else fila.setBackgroundColor(Color.parseColor("#D6D7D7"));

            TextView tv1 = new TextView(this);
            TextView tv2 = new TextView(this);
            Button   button_mostrar = new Button(this);
            Button   button_editar = new Button(this);
            Button   button_eliminar = new Button(this);
            tv1.setText(matriz_datos.get(i).getCodigo());
            tv2.setText(matriz_datos.get(i).getDescripcion());
            button_mostrar.setText("Mostrar");
            button_editar.setText("Editar");
            button_eliminar.setText("Eliminar");


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                button_mostrar.setAllCaps(false);
                button_editar.setAllCaps(false);
                button_eliminar.setAllCaps(false);
            }
            tv1.setTextSize(16);
            tv2.setTextSize(16);
            fila.addView(tv1);
            fila.addView(tv2);
            fila.addView(button_mostrar);
            fila.addView(button_editar);
            fila.addView(button_eliminar);
            fila.setPadding(0,10,0,10);

            tablapos.addView(fila);
        }
    }

    public void llenarSpinnerEnfermedades(){
        try {
            dbEnfermedadesSpinner db = new dbEnfermedadesSpinner(-1);
            db.execute("").get();

            matriz_datos_list = db.GetMatriz();
            List<String> list = new ArrayList<String>();
            String string_temp;
            for(int i=0; i<matriz_datos_list.size(); i++){
                string_temp = matriz_datos_list.get(i).getDescripcion().toString();
                list.add(string_temp);
            }
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_list.setAdapter(dataAdapter);
        } catch (Exception e) {
            Intent intent_Ingresar = new Intent(this.getApplicationContext(), ActivityEnfermedadesInicio.class);
            startActivity(intent_Ingresar);
            finish();
        }
    }
}
