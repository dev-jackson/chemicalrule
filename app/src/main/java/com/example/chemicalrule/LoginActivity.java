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

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button btn_login = (Button) findViewById(R.id.btn_login);

        //Inputs
        TextInputLayout text_username = (TextInputLayout) findViewById(R.id.input_username);
        TextInputLayout text_password = (TextInputLayout) findViewById(R.id.input_password);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("a",text_password.getEditText().getText().toString());
                Intent enter = new Intent(v.getContext(),MainActivity.class);
                if(text_password.toString().equals("123")
                        && text_username.toString().equals("user")
                ){
                    enter.putExtra("name",text_username.toString()); //send data
                    startActivity(enter);
                }else{
                    Toast.makeText(v.getContext(),"Credenciales Incorrectas", Toast.LENGTH_LONG).show();
                }
                //enter.putExtra("name",text_username.toString()); //send data
                //Bundle bundle = getIntent().getExtras();
                // bundle.getString(name_key);
                //startActivity(enter);
            }
        });
    }

}