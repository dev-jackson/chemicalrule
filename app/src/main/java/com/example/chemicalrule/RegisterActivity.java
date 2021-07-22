package com.example.chemicalrule;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

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
                if(!username.getText().toString().equals("") && !email.getText().toString().equals("") &&
                        !dateOfBirth.getText().toString().equals("") && !password.getText().toString().equals("") &&
                        !repeatPassword.getText().toString().equals("")){
                    if(!password.getText().toString().equals(repeatPassword.getText().toString())){
                        Snackbar.make(v.getContext(),v,"La contranseña no es igual",Snackbar.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(v.getContext(),"Registro exitis",Toast.LENGTH_LONG).show();
                        Intent login = new Intent(v.getContext(),LoginActivity.class);
                        startActivity(login);
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