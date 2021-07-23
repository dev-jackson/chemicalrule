package com.example.chemicalrule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.chemicalrule.ui.HomeFragment;
import com.example.chemicalrule.ui.SavedFragment;
import com.example.chemicalrule.ui.UserFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ChipNavigationBar bottomBar;
    FragmentManager fragmentManager;
    MaterialToolbar appBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_main);

        init(savedInstanceState);//UserFragment
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @SuppressLint("NonConstantResourceId")
    public void init(Bundle savedIn) {
        appBar = (MaterialToolbar) findViewById(R.id.toolBar);
        appBar.bringToFront();
        appBar.setOnMenuItemClickListener((item)->{
            if(item.getItemId() == R.id.about){
                Intent intentAbout = new Intent(this, AboutActivity.class);
                startActivity(intentAbout);
            }
            return  true;
        });
        bottomBar = findViewById(R.id.menu);
//        mmenu.setItemSelected(R.id.home, true);
        if(savedIn==null){
            bottomBar.setItemSelected(R.id.home,true);
            fragmentManager = getSupportFragmentManager();
            HomeFragment homeFragment = new HomeFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container,homeFragment)
                    .commit();
        }
        bottomBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                Fragment fragment = null;
                switch (id){
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.saved:
                        fragment = new SavedFragment();
                        break;
                    case R.id.user:
                        fragment = new ProfileFragment();
                        Log.d("Inf","user");
                        break;
                }
                if(fragment != null){
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                }else{
                    Log.e("TAG","Error in create fragment");
                }
            }
        });


    }
}