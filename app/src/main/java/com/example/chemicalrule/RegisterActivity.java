package com.example.chemicalrule;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {
    TextInputEditText dateOfBirth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dateOfBirth = (TextInputEditText) findViewById(R.id.data_of_birth);
        dateOfBirth.setOnClickListener(this::showDataPickerDialog);
        ImageButton btnBackLogin = (ImageButton) findViewById(R.id.btn_back_login);
        btnBackLogin.setOnClickListener(this::backScreen);
        Log.d("Info","selectedDate");
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