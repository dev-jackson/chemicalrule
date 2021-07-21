package com.example.chemicalrule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.chemicalrule.ui.ListAdapter;
import com.example.chemicalrule.ui.ListElement;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<ListElement> elements;
    ChipNavigationBar mmenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_main);

        init();

    }


    public void init(){
        mmenu = findViewById(R.id.menu);
        mmenu.setItemSelected(R.id.home,true);
        elements = new ArrayList<>();
        elements
                .add(new ListElement(
                "#777544","Malecom 2000", "Guayaquil","Exelente"));
        elements
                .add(new ListElement(
                "#777544","Malecom 2000", "Guayaquil","Exelente"));
        elements
                .add(new ListElement(
                        "#777544","Malecom 2000", "Guayaquil","Exelente"));
        elements
                .add(new ListElement(
                        "#777544","Malecom 2000", "Guayaquil","Exelente"));

        ListAdapter listAdapter = new ListAdapter(elements, this);
        RecyclerView recyclerView = findViewById(R.id.listLocation);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }
}