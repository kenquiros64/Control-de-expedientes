package com.example.ediloaz.control07;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ActivityEmpty extends CommonCode {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        super.Listener();
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}
