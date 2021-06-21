package com.example.chemicalrule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Splash);
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_ChemicalRule);
        setContentView(R.layout.activity_login);
    }
}