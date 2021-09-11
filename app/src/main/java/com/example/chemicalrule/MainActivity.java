package com.example.chemicalrule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.chemicalrule.ui.HomeFragment;
import com.example.chemicalrule.ui.SavedFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ChipNavigationBar bottomBar;
    FragmentManager fragmentManager;
    MaterialToolbar appBar;
    int idUser = 0;
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
        idUser = getIntent().getIntExtra("id_user",0);
        appBar = (MaterialToolbar) findViewById(R.id.toolBar);
        appBar.bringToFront();
        appBar.setOnMenuItemClickListener((item)->{
            switch (item.getItemId()){
                case R.id.about:
                    Intent intentAbout = new Intent(this, AboutActivity.class);
                    startActivity(intentAbout);
                    break;
                case R.id.delete_share_p:
                    SharedPreferences preferences =
                            getSharedPreferences("credentials", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("isSession", false);
                    editor.putString("username", "");
                    editor.putString("password", "");
                    editor.apply();
                    Toast.makeText(
                            this,
                            "Datos share eliminados",
                            Toast.LENGTH_LONG
                    ).show();
//                    finish();
                    break;

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
                        fragment = new HomeFragment(idUser);
                        break;
                    case R.id.saved:
                        fragment = new SavedFragment();
                        break;
                    case R.id.user:
                        fragment = new ProfileFragment(idUser);
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