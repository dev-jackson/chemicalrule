package com.example.chemicalrule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ImageButton btnBack = (ImageButton) findViewById(R.id.btn_back_login);
        btnBack.setOnClickListener(this::backScreen);
    }

    public void backScreen(View v){
        finish();
    }
}