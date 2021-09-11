package com.example.chemicalrule;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.chemicalrule.db.OpenHelperSql;
import com.example.chemicalrule.model.PublicationModel;
import com.google.android.gms.maps.MapView;
import com.google.android.material.textfield.TextInputEditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class ActivityRegisterItem extends AppCompatActivity {

    ImageView image;
    Button btnImage,btnLocation, btnRegister,btnCancel;
    String pathImage;
    TextInputEditText nameLocation,descriptionLocation, textLatitude,textLongitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_item);

        image = (ImageView) findViewById(R.id.img_placeholder);
        btnImage = (Button) findViewById(R.id.btn_image);
        nameLocation = (TextInputEditText) findViewById(R.id.input_name_location);
        descriptionLocation = (TextInputEditText) findViewById(R.id.input_description);
        textLatitude = (TextInputEditText) findViewById(R.id.input_latitude);
        textLongitude = (TextInputEditText) findViewById(R.id.input_longitude);
        btnLocation = (Button) findViewById(R.id.btn_location);
        btnRegister = (Button) findViewById(R.id.save_register_p);
        btnCancel = (Button) findViewById(R.id.cancel_register_p);
        btnCancel.setOnClickListener((v)->{
            finish();
        });
        btnRegister.setOnClickListener((v)->{
            try {
                saveLocation();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnLocation.setOnClickListener(this::openGoogleFragment);
        btnImage.setOnClickListener(this::loadImg);

    }

    @SuppressLint("IntentReset")
    public void loadImg(View v){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(Intent.createChooser(intent,"Selecione una image"),10);
    }

    public void openGoogleFragment(View v){
        Intent intent= new Intent(this,Map.class);
        startActivityForResult(intent,0);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("1",resultCode+"");
        assert data != null;
        double latitude = data.getDoubleExtra("latitude",0);
        double longitude = data.getDoubleExtra("longitude",0);
        Log.d("3",latitude+"<==>"+longitude);
        if(resultCode == RESULT_OK) {
            if(latitude != 0 && longitude != 0){
                textLongitude.setText(""+longitude);
                textLatitude.setText(""+latitude);
            }else{
                Uri path = data.getData();
                Log.d("2",path+"");
                image.setTag(path.toString());
                image.setImageURI(path);
                Log.d("2",image.getTag().toString());
                pathImage = this.convertUri(path);
            }

        }
    }
    public String convertUri(Uri contentUri){
        String [] proj={MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery( contentUri,
                proj, // Which columns to return
                null,       // WHERE clause; which rows to return (all rows)
                null,       // WHERE clause selection arguments (none)
                null); // Order-by clause (ascending by name)
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();

        return cursor.getString(column_index);
    }

    public void saveLocation() throws IOException {

        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_PERMISSION_STORAGE = 100;
            String[] permissions = {
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            };

            for (String str : permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    this.requestPermissions(permissions, REQUEST_CODE_PERMISSION_STORAGE);
                }
            }
        }
        FileInputStream image = new FileInputStream(pathImage);
        OpenHelperSql openHelperSql = new OpenHelperSql(this);
        byte[] bimage = new byte[image.available()];
        image.read(bimage);
        PublicationModel publicationModel = new PublicationModel();
        publicationModel.setName(Objects.requireNonNull(nameLocation.getText()).toString());
        publicationModel.setDescription(Objects.requireNonNull(descriptionLocation.getText()).toString());
        publicationModel.setLatitude(Objects.requireNonNull(textLatitude.getText()).toString());
        publicationModel.setLongitude(Objects.requireNonNull(textLongitude.getText()).toString());
        publicationModel.setImage(bimage);
        PublicationModel pmodel = openHelperSql.createPublication(publicationModel);
        if(pmodel != null){
            boolean ban = openHelperSql.createRelationUserPublication(
                    getIntent().getIntExtra("id_user",0),
                    pmodel.getId_publication()
                    );
            if(ban){
                Toast.makeText(this,"Publicacion guardad con exito!",Toast.LENGTH_LONG).show();
                finish();
            }else{
                Toast.makeText(this,"Ocurrio un error intente nuevamente",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this,"Ocurrio un error intente nuevamente",Toast.LENGTH_LONG).show();
        }
        image.close();

    }
}