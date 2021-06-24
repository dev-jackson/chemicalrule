package com.example.chemicalrule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        Button btn_login = (Button) R.id.btn_login;
    }

    public void enter_activity_main(View view) {
        Intent enter = new Intent(view.getContext(),MainActivity.class);
        //enter.putExtra("name","Hello"); send data
        //Bundle bundle = getIntent().getExtras();
        // bundle.getString(name_key);
        startActivity(enter);
    }
}