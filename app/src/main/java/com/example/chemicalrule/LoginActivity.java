package com.example.chemicalrule;

import androidx.annotation.Nullable;
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

import com.example.chemicalrule.db.OpenHelperSql;
import com.example.chemicalrule.model.UserModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText textUsername;
    TextInputEditText textPassword;
    TextView textViewCreateAccount;
    Button btnLogin;
    int id_user = 1;
    int type;
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
            startActivityForResult(new_user,10);
        });
        textUsername.setText(getIntent().getStringExtra("username"));
        textPassword.setText(getIntent().getStringExtra("password"));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Log.d("1",data.getStringExtra("id_user"));
        }
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
        OpenHelperSql openHelperSql = new OpenHelperSql(this);
        if(!username.equals("") && !password.equals("")){
            UserModel userModel = openHelperSql.loginUser(username,password);
            if(userModel != null){
                id_user = userModel.getId();
                type = userModel.getType();
            }else{
                Snackbar.make(v,"Incorrect credentials",Snackbar.LENGTH_LONG)
                        .setAction("Action",null).show();
                return;
            }
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
            Snackbar.make(v,"Incorrect credentials or null",Snackbar.LENGTH_LONG)
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
        preferences = getSharedPreferences("userIsNowSession",Context.MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor1 = preferences.edit();
        if(id_user == 0){
            editor.putInt("id_user",getIntent().getIntExtra("id_user",0));
            editor.putString("type",getIntent().getStringExtra("type"));
        }else{
            editor.putInt("id_user",id_user);
            if(type == 1){
                editor.putString("type","normal");
            }else{
                editor.putString("type","admin");
            }
        }
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