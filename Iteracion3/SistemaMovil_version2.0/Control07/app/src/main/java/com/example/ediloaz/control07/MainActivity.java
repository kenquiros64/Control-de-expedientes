package com.example.ediloaz.control07;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView text;
    EditText edit;
    ImageView image;
    Button login_buton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login_buton = (Button)findViewById(R.id.button_login);
        login_buton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_login:
                Intent intent = new Intent(getApplicationContext(),ActivityWelcome.class);
                startActivity(intent);
                break;
        }
    }
}
