package com.example.ediloaz.control07.Enfermedades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.R;

public class ActivityEnfermedadesVista extends CommonCode {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enfermedades_vista);
        super.Listener();

        String codigo = getIntent().getStringExtra("codigo");
        String descripcion = getIntent().getStringExtra("descripcion");

        TextView txtCodigo = (TextView) findViewById(R.id.text_EnfermedadesRegistrar_code_answer);
        TextView txtDescripcion = (TextView) findViewById(R.id.text_EnfermedadesRegistrar_description_answer);

        txtCodigo.setText(codigo);
        txtDescripcion.setText(descripcion);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}
