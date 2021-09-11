package com.example.chemicalrule.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.chemicalrule.model.PublicationModel;
import com.example.chemicalrule.model.UserModel;

import java.util.ArrayList;


public class OpenHelperSql extends SQLiteOpenHelper {
    private static final String SQLUSERTYPE = "CREATE table if not exists type_user(" +
            "id_type_user integer primary key autoincrement, " +
            "value integer not null,details text not null);";
    private static final String SQLUSER = "CREATE table if not exists user(" +
            "id_user integer primary key autoincrement, " +
            "username text not null, email text not null, " +
            "password text not null, " +
            "birth_date text not null, " +
            "type_user integer, " +
            "foreign key(type_user) references type_user(id_type_ser)" +
            ");";
    private  static final String SQLPUBLICATION = "create table if not exists publication(" +
            "id_publication integer primary key autoincrement, " +
            "name text not null, " +
            "description text not null, " +
            "geolaitude text not null, " +
            "geolength text not null, " +
            "image blob not null" +
            ");";
    public static  final String SQLUSERPUBLICATION = "CREATE table if not exists user_publication(" +
            "id_user integer, " +
            "id_publication integer, " +
            "foreign key(id_user) references user(id_user), " +
            "foreign key(id_publication) references publication(id_publication)" +
            ");";
    private static final String DATABASE_NAME = "tourisgo.db";
   private static final int DATABASE_VERSION = 3;
    SQLiteDatabase dabaSqLiteDatabase;

   public OpenHelperSql(Context context){
       super(context,DATABASE_NAME,null,DATABASE_VERSION);
   }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       dabaSqLiteDatabase = sqLiteDatabase;
        sqLiteDatabase.execSQL(SQLUSERTYPE);
        sqLiteDatabase.execSQL(SQLUSER);
        sqLiteDatabase.execSQL(SQLPUBLICATION);
        sqLiteDatabase.execSQL(SQLUSERPUBLICATION);
        this.saveTypeUser(1);
        this.saveTypeUser(2);


    }
    public void saveTypeUser(int value){
        ContentValues cv = new ContentValues();
        cv.put("value",value);
        if(value == 1){
            cv.put("details","normal");
        }else{
            cv.put("details", "admin");
        }
        long id = this.getWritableDatabase().insert("type_user",null,cv);
//        return (id!=-1);

    }
    public UserModel saveUser(UserModel user){
        ContentValues cv = new ContentValues();
        cv.put("username",user.getUsername());
        cv.put("email", user.getEmail());
        cv.put("password", user.getPassword());
        cv.put("birth_date" ,user.getBirth_date());
        cv.put("type_user", 1);
        long id = this.getWritableDatabase().insert("user",null,cv);
        return loginUser(user.getUsername(),user.getPassword());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public PublicationModel createPublication(PublicationModel publicationModel){
        PublicationModel modelp = null;
        ContentValues values = new ContentValues();
        values.put("name",publicationModel.getName());
        values.put("description",publicationModel.getDescription());
        values.put("geolaitude",publicationModel.getLatitude());
        values.put("geolength",publicationModel.getLongitude());
        values.put("image", publicationModel.getImage());
        Log.d("=>>>>", values.toString());
        long id = this.getWritableDatabase().insert("publication",null,values);
        if(id!=1){
            modelp = this.publicationByName(publicationModel.getName());
        }
        return modelp;
    }

    public boolean createRelationUserPublication(int id_user,int id_publication){
       ContentValues values  = new ContentValues();
       values.put("id_user",id_user);
       values.put("id_publication", id_publication);
       long id = this.getWritableDatabase().insert("user_publication",null,values);
       return (id!=-1);
    }
    public PublicationModel publicationByName(String name){
       PublicationModel publicationModel = null;
       Cursor cursor = this.getReadableDatabase().rawQuery(
               "select * from publication where name='"+name+"'",
               null
       );
       if(cursor.moveToFirst()){
           publicationModel = new PublicationModel(
                cursor.getInt(cursor.getColumnIndex("id_publication")),
                   cursor.getString(cursor.getColumnIndex("name")),
                   cursor.getString(cursor.getColumnIndex("description")),
                   cursor.getString(cursor.getColumnIndex("geolaitude")),
                   cursor.getString(cursor.getColumnIndex("geolength")),
                   cursor.getBlob(cursor.getColumnIndex("image"))
           );
       }
       return publicationModel;

    }
    public UserModel loginUser(String username, String password){
       UserModel userModel = null;
        Cursor c = this.getReadableDatabase().rawQuery(
                "select * from user where username ='"+username+"' and password='"+password+"'",
                null);
        if(c.moveToNext()){
           userModel = new UserModel(
                   c.getInt(c.getColumnIndex("id_user")),
                   c.getInt(c.getColumnIndex("type_user"))
           );
       }
        return userModel;
    }

    public ArrayList<PublicationModel> getAllPublication(){
       SQLiteDatabase db = this.getReadableDatabase();
       ArrayList<PublicationModel> publicationModels = new ArrayList<>();
       Cursor res = db.rawQuery(
               "SELECT * FROM publication",
               null);
       if(res.moveToFirst()){
           do{
                publicationModels.add(
                        new PublicationModel(
                            res.getInt(res.getColumnIndex("id_publication")),
                                res.getString(res.getColumnIndex("name")),
                                res.getString(res.getColumnIndex("description")),
                                res.getString(res.getColumnIndex("geolaitude")),
                                res.getString(res.getColumnIndex("geolength")),
                                res.getBlob(res.getColumnIndex("image"))
                        )
                );
           }while (res.moveToNext());
       }
       return publicationModels;
    }
    public ArrayList<UserModel> getAllUser(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<UserModel> users = new ArrayList<UserModel>();
        Cursor res =  db.rawQuery("SELECT * FROM user", null);
        if(res.moveToFirst()){
            do{

            }while (res.moveToNext());

        }
        return users;
    }
}
