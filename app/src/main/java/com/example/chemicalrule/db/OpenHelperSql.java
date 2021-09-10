package com.example.chemicalrule.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.chemicalrule.model.UserModel;

import java.util.ArrayList;


public class OpenHelperSql extends SQLiteOpenHelper {
    private static final String SQL = "" + "CREATE table if not exists type_user(\n" + "\tid_type_user integer primary key autoincrement,\n" + "\tvalue integer not null,\n" + "\tdetails text not null\n" + ");\n" + "\n" + "CREATE table if not exists user(\n" + "\tid_user integer primary key autoincrement,\n" + "\tusername text not null,\n" + "\temail text not null,\n" + "\tpassword text not null,\n" + "\tbirth_date text not null,\n" + "\ttype_user integer,\n" + "\tforeign key(type_user) references type_user(id_type_ser)\n" + ");\n" + "\n" + "create table if not exists publication(\n" + "\tid_publication integer primary key autoincrement,\n" + "\tname text not null,\n" + "\tdescription text not null,\n" + "\tgeolaitude text not null,\n" + "\tgeolength text not null,\n" + "\timage blob not null\n" + ");\n" + "\n" + "CREATE table if not exists user_publication(\n" + "\tid_user integer,\n" + "\tid_publication integer,\n" + "\tforeign key(id_user) references user(id_user),\n" + "\tforeign key(id_publication) references publication(id_publicacion)\n" + ");\n" + "\n" + "CREATE table if not exists comment(\n" + "\tid_comment integer primary key autoincrement,\n" + "\tdate_comment text not nul\n" + ");";
   private static final String DATABASE_NAME = "tour.db";
   private static final int DATABASE_VERSION = 1;

   public OpenHelperSql(Context context){
       super(context,DATABASE_NAME,null,DATABASE_VERSION);
   }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       sqLiteDatabase.execSQL(SQL);
       this.saveTypeUser(1);
       this.saveTypeUser(2);


    }
    public void saveTypeUser(int value){
        SQLiteDatabase dabaSqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("value",value);
        if(value == 1){
            cv.put("details","normal");
        }else{
            cv.put("details", "admin");
        }
        long id = dabaSqLiteDatabase.insert("type_user",null,cv);
//        return (id!=-1);

    }
    public UserModel saveUser(UserModel user){
       SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username",user.getUsername());
        cv.put("email", user.getEmail());
        cv.put("password", user.getPassword());
        cv.put("birth_date" ,user.getBirth_date());
        cv.put("type_user", 1);
        long id = database.insert("user",null,cv);
        return loginUser(user.getUsername(),user.getPassword());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean createPublication(ContentValues cv){
        SQLiteDatabase database = this.getWritableDatabase();
        long id = database.insert("publication",null,cv);
        return (id!=-1);
    }

    public UserModel loginUser(String username, String password){
       UserModel userModel = null;
        Cursor c = this.getReadableDatabase().rawQuery(
                "select * from user where username ="+username+" and password="+password+"",
                null);
        if(c.moveToNext()){
           userModel = new UserModel(
                   c.getInt(c.getColumnIndex("id_user")),
                   c.getInt(c.getColumnIndex("type_user"))
           );
       }
        return userModel;
    }

    public ArrayList<UserModel> getAllUser(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<UserModel> users = new ArrayList<UserModel>();
        Cursor res =  db.rawQuery("SELECT * FROM user", null);
        res.moveToFirst();
        while (res.isAfterLast() == false){
            users.add(
                    new UserModel(
                    res.getInt(res.getColumnIndex("id_user")),
                    res.getString(res.getColumnIndex("username")),
                    res.getString(res.getColumnIndex("email")),
                    res.getString(res.getColumnIndex("password")),
                    res.getString(res.getColumnIndex("birth_date")),
                            res.getInt(res.getColumnIndex("type_user"))
                )
            );
            res.moveToNext();
        }
        return users;
    }
}
