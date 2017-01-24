package com.example.ediloaz.control07.Citas;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.Pacientes.Paciente;
import com.example.ediloaz.control07.R;
import com.example.ediloaz.control07.SessionManager;

import java.util.ArrayList;
import java.util.Calendar;

public class ActivityCitasListadoPaciente extends CommonCode {
    private Button button_agregar;
    public ProgressBar progressBar;
    private SessionManager session;
    private TextView text_paciente;
    private Calendar calendar = Calendar.getInstance();
    private boolean admin;
    private String medico, paciente_nombre;
    private int id_paciente;
    TableLayout tablapos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas_listado_paciente);
        Listener();

        button_agregar = (Button) findViewById(R.id.button_CitasListado_Nuevo);
        button_agregar.setOnClickListener(this);
        tablapos = (TableLayout) findViewById(R.id.table_CitasListado_lis);

        text_paciente = (TextView)findViewById(R.id.text_CitasListado_list_little);

        paciente_nombre = getIntent().getStringExtra("paciente");
        id_paciente = getIntent().getIntExtra("id", 0);


        Log.i("asdhbadNUM1",""+paciente_nombre);

        text_paciente.setText("Paciente: " + paciente_nombre);


        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);



    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.i("asdhbadNUM2",""+id_paciente);
        dbCitasInicio db = new dbCitasInicio(this, progressBar,id_paciente);
        Log.i("asdhbadNUM5",""+id_paciente);
        db.execute("");
        Log.i("asdhbadNUM6",""+id_paciente);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.button_CitasListado_Nuevo:

                button_agregar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), ActivityCitasNuevo2.class);
                        intent.putExtra("paciente_id", id_paciente);
//                        intent.putExtra("medico_id", id_medico);
                        intent.putExtra("paciente_nombre", paciente_nombre);
                        startActivity(intent);
                    }
                });


                break;
        }
    }

    public void llenarTabla(ArrayList<Cita> matriz_datos){

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
            TextView tv3 = new TextView(this);
            TextView tv4 = new TextView(this);
            Button   button_mostrar_cita = new Button(this);
            Button   button_editar_cita = new Button(this);

            final Cita cita = matriz_datos.get(i);

            tv1.setText(cita.getFecha());
            tv2.setText(cita.getHora());
            tv3.setText(cita.getMedico().getNombre());
            tv4.setText(cita.getPaciente().getNombre());
            button_mostrar_cita.setText("Mostrar");
            button_editar_cita.setText("Editar");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                button_mostrar_cita.setAllCaps(false);
                button_editar_cita.setAllCaps(false);
            }
            tv1.setTextSize(16);
            tv2.setTextSize(16);
            tv3.setTextSize(16);
            tv4.setTextSize(16);



//            final Cita cita = matriz_datos.get(i);
            Log.i("A_B_C_D_E","0");
            button_mostrar_cita.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ActivityCitasListadoPaciente.this, ActivityCitasVista.class);
                    Log.i("A_B_C_D_E","1");
                    intent.putExtra("id", cita.getId());
                    intent.putExtra("paciente_nombre", cita.getPaciente().getNombre() + " " + cita.getPaciente().getApellido1() + " " + cita.getPaciente().getApellido2());
                    intent.putExtra("medico_nombre", cita.getMedico().getNombre() + " " + cita.getMedico().getApellido1() + " " + cita.getMedico().getApellido2());
                    intent.putExtra("fecha", cita.getFecha());
                    intent.putExtra("hora", cita.getHora());
                    Log.i("A_B_C_D_E","2");
                    startActivity(intent);
                    Log.i("A_B_C_D_E","3");
                }
            });

            button_editar_cita.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ActivityCitasListadoPaciente.this, ActivityCitasEditar.class);
                    Log.i("A_B_C_D_E","1");
                    intent.putExtra("id", cita.getId());
                    intent.putExtra("paciente_nombre", cita.getPaciente().getNombre() + " " + cita.getPaciente().getApellido1() + " " + cita.getPaciente().getApellido2());
                    intent.putExtra("medico_nombre", cita.getMedico().getNombre() + " " + cita.getMedico().getApellido1() + " " + cita.getMedico().getApellido2());
                    intent.putExtra("fecha", cita.getFecha());
                    intent.putExtra("hora", cita.getHora());
                    Log.i("A_B_C_D_E","2");
                    startActivity(intent);
                    Log.i("A_B_C_D_E","3");
                }
            });


            fila.addView(tv1);
            fila.addView(tv2);
//            fila.addView(tv3);
//            fila.addView(tv4);
            fila.addView(button_mostrar_cita);
            fila.addView(button_editar_cita);
            fila.setPadding(0,10,0,10);

            tablapos.addView(fila);
        }

    }

}
