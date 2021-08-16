package com.example.chemicalrule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.chemicalrule.db.OpenHelperSql;
import com.example.chemicalrule.model.UserModel;

import java.util.ArrayList;

public class activity_crud extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);
        OpenHelperSql openHelperSql = new OpenHelperSql(this);
        ListView listUser = findViewById(R.id.list_user);
        ArrayList<UserModel> users = openHelperSql.getAllUser();

        ArrayAdapter adapter = new ArrayAdapter(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                users
        );
        listUser.setAdapter(adapter);
    }
}