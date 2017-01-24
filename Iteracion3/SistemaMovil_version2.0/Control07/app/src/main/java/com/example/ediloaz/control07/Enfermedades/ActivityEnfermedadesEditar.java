package com.example.ediloaz.control07.Enfermedades;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.R;

public class ActivityEnfermedadesEditar extends CommonCode {
    private EditText edit_codigo, edit_descripcion;
    private Button button_editar;

    private String codigo, descripcion;
    private int id;

    private ProgressBar progressBar;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enfermedades_editar);
        super.Listener();
        button_editar = (Button)findViewById(R.id.button_EnfermedadesNuevo_editar);
        button_editar.setOnClickListener(this);
        edit_codigo =  (EditText) findViewById(R.id.edit_EnfermedadesRegistrar_code);
        edit_descripcion =  (EditText) findViewById(R.id.edit_EnfermedadesRegistrar_description);
        Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "onCreate terminado");

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        codigo = getIntent().getStringExtra("codigo");
        descripcion = getIntent().getStringExtra("descripcion");
        id =  getIntent().getIntExtra("id",0);

        EditText txtCodigo = (EditText) findViewById(R.id.edit_EnfermedadesRegistrar_code);
        EditText txtDescripcion = (EditText) findViewById(R.id.edit_EnfermedadesRegistrar_description);

        txtCodigo.setText(codigo);
        txtDescripcion.setText(descripcion);
    }


    @Override
    public void onClick(View v){
        super.onClick(v);
        switch (v.getId()) {

            case R.id.button_EnfermedadesNuevo_editar:

                codigo = edit_codigo.getText().toString();
                descripcion = edit_descripcion.getText().toString();

                dbEnfermedadesEditar db = new dbEnfermedadesEditar(this,progressBar,codigo,descripcion,id);

                db.execute();
                break;
        }
    }
}
