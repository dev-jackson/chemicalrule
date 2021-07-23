package com.example.chemicalrule.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chemicalrule.AboutActivity;
import com.example.chemicalrule.R;
import com.example.chemicalrule.ui.ListAdapter;
import com.example.chemicalrule.ui.ListElement;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    MaterialToolbar appBar;
    List<ListElement> elements;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    public void init(View view){
    appBar = (MaterialToolbar) view.findViewById(R.id.toolBar);
        appBar.setOnMenuItemClickListener((item)->{
        if(item.getItemId() == R.id.about){
            Intent intentAbout = new Intent(view.getContext(), AboutActivity.class);
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

    ListAdapter listAdapter = new ListAdapter(elements, view.getContext());

    RecyclerView recyclerView = view.findViewById(R.id.listLocation);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(listAdapter);
}
    public void show_menu(View v){
        PopupMenu popup = new PopupMenu(v.getContext(), v);
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
