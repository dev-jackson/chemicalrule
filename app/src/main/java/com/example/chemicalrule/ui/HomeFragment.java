package com.example.chemicalrule.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.chemicalrule.R;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    List<ListElement> elements;
    Spinner search;
    String[] types = {"Location","Typical Food"};
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
    }

    public void init(View view){
        search = (Spinner)view.findViewById(R.id.spinnerSearch);
        ArrayAdapter<CharSequence> ad = new ArrayAdapter<CharSequence>(view.getContext(),R.layout.support_simple_spinner_dropdown_item,types);
        ad.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        search.setAdapter(ad);
        elements = new ArrayList<>();
        elements
                .add(new ListElement(R.drawable.malecon2000,
                        "#777544","Malecom 2000", "Malecón, Av. Vicente Rocafuerte Bejarano, Guayaquil 090313","Exelente","Location"));
        elements
                .add(new ListElement(R.drawable.lps,
                        "#777544","Las peñas", "Malecón, Av. Vicente Rocafuerte Bejarano, Guayaquil 090313","Exelente", "Locacion"));
        elements
                .add(new ListElement(R.drawable.samanes,
                        "#777544","Malecom 2000", "Guayaquil","Exelente","Fypical Food"));
        elements
                .add(new ListElement(R.drawable.lps,
                        "#777544","Las peñas", "Guayaquil","Exelente","Fypical Food"));
        elements
                .add(new ListElement(R.drawable.lps,
                        "#777544","Malecom 2000", "Guayaquil","Exelente","Location"));
        elements
                .add(new ListElement(R.drawable.samanes,
                        "#777544","Samanes", "Guayaquil","Exelente", "Location"));
        elements
                .add(new ListElement(R.drawable.samanes,
                        "#777544","Malecom 2000", "Guayaquil","Exelente","Fypical Food"));
        elements
                .add(new ListElement(R.drawable.lps,
                        "#777544","Las peñas", "Guayaquil","Exelente","Fypical Food"));
        elements
                .add(new ListElement(R.drawable.samanes,
                        "#777544","Samanes", "Guayaquil","Exelente", "Location"));
        elements
                .add(new ListElement(R.drawable.samanes,
                        "#777544","Malecom 2000", "Malecón, Av. Vicente Rocafuerte Bejarano, Guayaquil 090313","Exelente", "Locaction"));
        elements
                .add(new ListElement(R.drawable.lps,
                        "#777544","Las peñas", "Guayaquil","Exelente", "Location"));
//        elements
//                .add(new ListElement(
//                        "#777544","Malecom 2000", "Guayaquil","Exelente"));

        ListAdapter listAdapter = new ListAdapter(elements, view.getContext());

        RecyclerView recyclerView = view.findViewById(R.id.listLocation);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(listAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),LinearLayoutManager.VERTICAL));
        search.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position >= 0 && position < listAdapter.getItemCount()){

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}