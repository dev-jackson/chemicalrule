package com.example.chemicalrule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_register = (Button) findViewById(R.id.register);
        Button btn_cancel   = (Button) findViewById(R.id.cancel);
        // Set color btn
        //btn_cancel.setBackgroundColor(Color.RED);

        // Input
        TextInputEditText text_first_name = (TextInputEditText) findViewById(R.id.first_name);
        TextInputEditText text_last_name  = (TextInputEditText) findViewById(R.id.last_name);
        TextInputEditText text_phone      = (TextInputEditText) findViewById(R.id.phone);
        TextInputEditText text_age        = (TextInputEditText) findViewById(R.id.age);
        TextInputEditText text_email      = (TextInputEditText) findViewById(R.id.email);

        btn_register.setOnClickListener((view)->{
            String first_name   = text_first_name.getText().toString();
            String last_name    = text_last_name.getText().toString();
            String phone        = text_phone.getText().toString();
            String age          = text_age.getText().toString();
            String email_address= text_email.getText().toString();

            if(
                !first_name.equals("") || !last_name.equals("") ||
                !phone.equals("") || !age.equals("") || !email_address.equals("")
            ){
                Toast.makeText(
                        view.getContext(),
                        "Registro existo. \n Datos: {\n" +
                                "First Name: " + first_name + "\n" +
                                "Last Name: " + last_name + "\n" +
                                "Number Phone: " + phone + "\n" +
                                "Age: " + age + "\n" +
                                "Email: " + email_address + "\n" +
                        "\n}",
                        Toast.LENGTH_LONG
                ).show();
            }else{
                Toast.makeText(
                        view.getContext(),
                        "Todos los campos deben estar llenos",
                        Toast.LENGTH_LONG
                ).show();
            }
        });

        // btn_cancel
        btn_cancel.setOnClickListener((view) -> {
            Toast.makeText(view.getContext(), "Registro cancelado", Toast.LENGTH_LONG).show();
            Intent login = new Intent(view.getContext(),LoginActivity.class);
            startActivity(login);
        });
    }
}