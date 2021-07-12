package com.example.chemicalrule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button btn_login = (Button) findViewById(R.id.btn_login);

        // TextView as Button
        TextView btn_new_user = (TextView) findViewById(R.id.new_user);
        // TextView
                //Inputs
        TextInputEditText text_username = (TextInputEditText) findViewById(R.id.input_username);
        TextInputEditText text_password = (TextInputEditText) findViewById(R.id.input_password);

        btn_login.setOnClickListener((view) -> {
            Intent enter = new Intent(view.getContext(),MainActivity.class);

            String username = Objects.requireNonNull(text_username.getText()).toString();
            String password = Objects.requireNonNull(text_password.getText()).toString();

            if((username.equals("user") && password.equals("123"))){
                enter.putExtra("name",text_username.toString()); //send data
                startActivity(enter);
            }else{
                Snackbar.make(view,"Incorrect credentials",Snackbar.LENGTH_LONG)
                    .setAction("Action",null).show();

            }
                //enter.putExtra("name",text_username.toString()); //send data
                //Bundle bundle = getIntent().getExtras();
                // bundle.getString(name_key);
                //startActivity(enter);
        });

        btn_new_user.setOnClickListener((view)->{
            Intent new_user = new Intent(view.getContext(), RegisterActivity.class);
            startActivity(new_user);
        });
    }

}