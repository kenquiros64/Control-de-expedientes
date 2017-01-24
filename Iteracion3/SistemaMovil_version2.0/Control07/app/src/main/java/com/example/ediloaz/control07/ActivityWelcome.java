package com.example.ediloaz.control07;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.HashMap;

public class ActivityWelcome extends CommonCode {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        super.Listener();

        // get medico data from session
        HashMap<String, String> medico = getSession().getUserDetails();

        String name = medico.get(SessionManager.KEY_NAME);

        boolean admin = getSession().isAdmin();

        TextView welcome = (TextView) findViewById(R.id.dashboard_welcome);

        if(admin){
            welcome.setText("Bienvenido " + name);
        }else{
            welcome.setText("Bienvenido Dr. " + name);
        }
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
    }


}
