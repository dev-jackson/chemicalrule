package com.example.chemicalrule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.chemicalrule.ui.ListAdapter;
import com.example.chemicalrule.ui.ListElement;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<ListElement> elements;
    ChipNavigationBar mmenu;
    MaterialToolbar appBar;
    RecyclerView listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_main);

        init();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void init(){
        mmenu = findViewById(R.id.menu);
        mmenu.setItemSelected(R.id.home,true);
        appBar = (MaterialToolbar) findViewById(R.id.toolBar);
        appBar.setOnMenuItemClickListener((item)->{
            if(item.getItemId() == R.id.about){
                Intent intentAbout = new Intent(this, AboutActivity.class);
                startActivity(intentAbout);
            }
            return  true;
        });
        elements = new ArrayList<>();
        elements
                .add(new ListElement(R.drawable.malecon2000,
                "#777544","Malecom 2000", "Guayaquil","Exelente"));
        elements
                .add(new ListElement(R.drawable.lps,
                "#777544","Malecom 2000", "Guayaquil","Exelente"));
        elements
                .add(new ListElement(R.drawable.samanes,
                        "#777544","Malecom 2000", "Guayaquil","Exelente"));
        elements
                .add(new ListElement(R.drawable.samanes,
                        "#777544","Malecom 2000", "Guayaquil","Exelente"));
        elements
                .add(new ListElement(R.drawable.samanes,
                        "#777544","Malecom 2000", "Guayaquil","Exelente"));
        elements
                .add(new ListElement(R.drawable.samanes,
                        "#777544","Malecom 2000", "Guayaquil","Exelente"));
        elements
                .add(new ListElement(R.drawable.samanes,
                        "#777544","Malecom 2000", "Guayaquil","Exelente"));
        elements
                .add(new ListElement(R.drawable.samanes,
                        "#777544","Malecom 2000", "Guayaquil","Exelente"));
        elements
                .add(new ListElement(R.drawable.samanes,
                        "#777544","Malecom 2000", "Guayaquil","Exelente"));
        elements
                .add(new ListElement(R.drawable.samanes,
                        "#777544","Malecom 2000", "Guayaquil","Exelente"));
        elements
                .add(new ListElement(R.drawable.samanes,
                        "#777544","Malecom 2000", "Guayaquil","Exelente"));
//        elements
//                .add(new ListElement(
//                        "#777544","Malecom 2000", "Guayaquil","Exelente"));

        ListAdapter listAdapter = new ListAdapter(elements, this);

        RecyclerView recyclerView = findViewById(R.id.listLocation);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }
    public void show_menu(View v){
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_popup,popup.getMenu());
        popup.setOnMenuItemClickListener((item)->{
            if(item.getItemId() == R.id.about){
                Intent intentAbout = new Intent(v.getContext(), AboutActivity.class);
                startActivity(intentAbout);
            }
            return  true;
        });
        popup.show();

    }
}