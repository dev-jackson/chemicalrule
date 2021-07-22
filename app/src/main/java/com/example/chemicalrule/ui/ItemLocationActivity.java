package com.example.chemicalrule.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chemicalrule.R;

public class ItemLocationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_location);

        TextView title = (TextView) findViewById(R.id.titleItem);
        ImageView image = (ImageView) findViewById(R.id.imageItem);
        title.setText(getIntent().getStringExtra("title"));
        image.setImageResource(getIntent().getIntExtra("srcImage",0));

    }
}
