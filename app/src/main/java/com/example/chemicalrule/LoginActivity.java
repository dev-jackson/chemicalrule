package com.example.chemicalrule;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText textUsername;
    TextInputEditText textPassword;
    TextView textViewCreateAccount;
    Button btnLogin;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initElements();
        if(!(getSharedPreferences("credentials",Context.MODE_PRIVATE) == null)){
            preferences = getSharedPreferences("credentials",Context.MODE_PRIVATE);
            loadPreferencesLogin();
        }
        btnLogin.setOnClickListener(this::Login);

        textViewCreateAccount.setOnClickListener((view)->{
            Intent new_user = new Intent(view.getContext(), RegisterActivity.class);
            startActivity(new_user);
        });
    }

    public void initElements(){
        btnLogin = (Button) findViewById(R.id.btn_login);

        //btnMenu.setOnClickListener(this::show_menu);

        // TextView as Button
        textViewCreateAccount = (TextView) findViewById(R.id.new_user);
        // TextView
        //Inputs
        textUsername = (TextInputEditText) findViewById(R.id.input_username);
        textPassword = (TextInputEditText) findViewById(R.id.input_password);
    }

    public void Login(View v){
        Intent enter = new Intent(v.getContext(),MainActivity.class);

        String username = Objects.requireNonNull(textUsername.getText()).toString();
        String password = Objects.requireNonNull(textPassword.getText()).toString();

        if((username.equals("user") && password.equals("123"))){
//                enter.putExtra("name",text_username.toString()); //send data
            AlertDialog.Builder alertPreference = new AlertDialog.Builder(this);
            alertPreference.setTitle("Guardar sesion?");
            alertPreference.setMessage("Desea recordar sesion?");
            alertPreference.setPositiveButton(
                    "Si",
                    (DialogInterface.OnClickListener) (dialog, which) -> {
                        savePreferencesLogin();
                        Toast.makeText(
                                this,
                                "Sesion guardada",
                                Toast.LENGTH_SHORT
                        ).show();
                        startActivity(enter);
                    });
            alertPreference.setNegativeButton(
                    "No",
                    (DialogInterface.OnClickListener)(dialog,which) ->{
                        Toast.makeText(
                                this,
                                "Bienvenido",
                                Toast.LENGTH_SHORT
                        ).show();
                        startActivity(enter);
                    });
            alertPreference.show();
        }else{
            Snackbar.make(v,"Incorrect credentials",Snackbar.LENGTH_LONG)
                    .setAction("Action",null).show();

        }
    }
    public void savePreferencesLogin(){
        preferences = getSharedPreferences("credentials",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isSession", true);
        editor.putString("username", Objects.requireNonNull(textUsername.getText()).toString());
        editor.putString("password", Objects.requireNonNull(textPassword.getText()).toString());
        editor.apply();
    }

    public void loadPreferencesLogin(){
        if(preferences.getBoolean("isSession",false)){
            textUsername.setText(preferences.getString("username",""));
            textPassword.setText(preferences.getString("password",""));
            Intent enter = new Intent(this,MainActivity.class);
            startActivity(enter);
        }
    }



}