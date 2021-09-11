package com.example.chemicalrule.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chemicalrule.R;
import com.google.android.material.appbar.MaterialToolbar;

public class ItemLocationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_location);

        MaterialToolbar appBar = (MaterialToolbar) findViewById(R.id.toolBar);
        TextView title = (TextView) findViewById(R.id.titleItem);
        appBar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_white_24);
        appBar.setNavigationOnClickListener((v)->{
            finish();
        });

        ImageView image = (ImageView) findViewById(R.id.imageItem);
        TextView address = (TextView)findViewById(R.id.address);
        TextView review = (TextView) findViewById(R.id.review);
        TextView description = (TextView) findViewById(R.id.description);
        title.setText(getIntent().getStringExtra("title"));
        byte[] bimage = getIntent().getByteArrayExtra("bImage");
        Bitmap bmp = BitmapFactory.decodeByteArray(bimage,0,bimage.length);
        image.setImageBitmap(bmp);
        address.setText(getIntent().getStringExtra("address"));
        review.setText(getIntent().getStringExtra("review"));
        description.setText(getIntent().getStringExtra("description"));



    }
}
