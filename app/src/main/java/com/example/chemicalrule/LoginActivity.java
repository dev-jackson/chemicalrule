package com.example.chemicalrule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button btn_login = (Button) findViewById(R.id.btn_login);

        //Inputs
        TextInputEditText text_username = (TextInputEditText) findViewById(R.id.input_username);
        TextInputEditText text_password = (TextInputEditText) findViewById(R.id.input_password);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent enter = new Intent(v.getContext(),MainActivity.class);
                try {
                    String username = text_username.getText().toString();
                    String password = text_password.getText().toString();

                    if((username.equals("user") && password.equals("123"))){
                        enter.putExtra("name",text_username.toString()); //send data
                        startActivity(enter);
                    }else{
                        Toast.makeText(
                                v.getContext(),
                                "Incorrect credentials",
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }catch (NullPointerException e){
                    Log.d("Exception ",e.getMessage());
                }
                //enter.putExtra("name",text_username.toString()); //send data
                //Bundle bundle = getIntent().getExtras();
                // bundle.getString(name_key);
                //startActivity(enter);
            }
        });
    }

}