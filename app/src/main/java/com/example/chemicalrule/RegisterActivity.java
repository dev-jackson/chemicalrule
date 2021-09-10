package com.example.chemicalrule;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.DialogFragment;

import com.example.chemicalrule.db.OpenHelperSql;
import com.example.chemicalrule.model.UserModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    TextInputEditText dateOfBirth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
    }


    public void init(){
        Button register = findViewById(R.id.btn_register);

        CheckBox terms = findViewById(R.id.terms);
        TextInputEditText username = (TextInputEditText) findViewById(R.id.input_username);
        TextInputEditText email = (TextInputEditText) findViewById(R.id.input_email);
        dateOfBirth = (TextInputEditText) findViewById(R.id.data_of_birth);
        TextInputEditText password = (TextInputEditText) findViewById(R.id.input_password);
        TextInputEditText repeatPassword = (TextInputEditText) findViewById(R.id.input_repeat_password);

        dateOfBirth.setOnClickListener(this::showDataPickerDialog);
        ImageButton btnBackLogin = (ImageButton) findViewById(R.id.btn_back_login);
        btnBackLogin.setOnClickListener(this::backScreen);
        // Logical pick up date

        register.setOnClickListener((v)->{

            if(terms.isChecked()){
                if(!Objects.requireNonNull(username.getText()).toString().equals("") &&
                        !Objects.requireNonNull(email.getText()).toString().equals("") &&
                        !Objects.requireNonNull(dateOfBirth.getText()).toString().equals("") &&
                        !Objects.requireNonNull(password.getText()).toString().equals("") &&
                        !Objects.requireNonNull(repeatPassword.getText()).toString().equals("")){
                    if(!password.getText().toString().equals(repeatPassword.getText().toString())){
                        Snackbar.make(v.getContext(),v,"La contranseña no es igual",Snackbar.LENGTH_LONG).show();
                    }else{
                        //Toast.makeText(v.getContext(),"Registro exitis",Toast.LENGTH_LONG).show();
                        UserModel user = new UserModel();
                        user.setUsername(username.getText().toString());
                        user.setEmail(email.getText().toString());
                        user.setPassword(password.getText().toString());
                        user.setBirth_date(dateOfBirth.getText().toString());
                        OpenHelperSql openHelperSql = new OpenHelperSql(this);
                        UserModel usermodel = openHelperSql.saveUser(user);
                        if(usermodel != null){
                            Toast.makeText(v.getContext(),"Guardado existoso",Toast.LENGTH_LONG).show();
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra("id_user",usermodel.getId());
                            resultIntent.putExtra("type","normal");
                            resultIntent.putExtra("username",user.getUsername());
                            resultIntent.putExtra("password", user.getPassword());
                            setResult(Activity.RESULT_OK, resultIntent);
                            finish();
                        }
                    }
                }else{
                    Snackbar.make(v.getContext(),v,"Llene todos los campos",Snackbar.LENGTH_LONG).show();
                }
            }else{
                Snackbar.make(v.getContext(),v,"Acepte terminos y condiciones",Snackbar.LENGTH_LONG).show();
            }
        });


    }
    public void showDataPickerDialog(View v){
        DialogFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                final String selectedDate = dayOfMonth +"/"+(month+1)+"/"+year;
                dateOfBirth.setText(selectedDate);
            }
        });
        newFragment.show(getSupportFragmentManager(), "DatePicker");
    }
    public void backScreen(View v){
        finish();
    }

}