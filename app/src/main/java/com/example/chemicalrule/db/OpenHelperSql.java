package com.example.chemicalrule.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.chemicalrule.model.UserModel;

import java.util.ArrayList;


public class OpenHelperSql extends SQLiteOpenHelper {
    private static final String USER_TABLE =
            "CREATE TABlE user(id_user INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "username TEXT, email TEXT, password TEXT, birth_date TEXT)";
   private static final String DATABASE_NAME = "togo.db";
   private static final int DATABASE_VERSION = 1;

   public OpenHelperSql(Context context){
       super(context,DATABASE_NAME,null,DATABASE_VERSION);
   }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       sqLiteDatabase.execSQL(USER_TABLE);

    }

    public boolean saveUser(UserModel user){
       SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username",user.getUsername());
        cv.put("email", user.getEmail());
        cv.put("password", user.getPassword());
        cv.put("birth_date" ,user.getBirth_date());
        long id = database.insert("user",null,cv);
        if(id == -1){
            return false;
        }
        return true;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

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
                    res.getString(res.getColumnIndex("birth_date"))
                )
            );
            res.moveToNext();
        }
        return users;
    }
}
