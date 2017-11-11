package com.example.user.fitai;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.R.attr.id;

/**
 * Created by mohit on 28/8/17.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "User.db";
    public static final String TABLE_NAME = "user_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "USERNAME";
    public static final String COL_3 = "EMAIL";
    public static final String COL_4 = "PASSWORD";
    public static final String COL_5 = "IMAGE";
    public static final String COL_6 = "HEIGHT";
    public static final String COL_7 = "WEIGHT";
    public static final String COL_8 = "DOB";
    public static final String COL_9 = "GENDER";


    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, 10);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, EMAIL TEXT, PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        if (newVersion > oldVersion) {
            db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN IMAGE BLOB");
            db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN HEIGHT REAL");
            db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN WEIGHT REAL");
            db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN DOB TEXT");
            db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN GENDER TEXT");
            db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN PROGRAM INTEGER");
        }
    }

    public boolean insertData(String user, String email, String pass, byte[] image){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, user);
        contentValues.put(COL_3, email);
        contentValues.put(COL_4, pass);
        contentValues.put(COL_5, image);
        long res = db.insert(TABLE_NAME,  null, contentValues);
        if(res == -1) {
            Log.d("res", "false");
            return false;
        }
        else
            return true;
    }

    public boolean verifySignup(String email, String user){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, " EMAIL = ? or USERNAME = ?", new String[]{email, user}, null, null, null);
        if(cursor.getCount()>0)
            return false;
        else
            return true;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public boolean verifyLogin(String user, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor1 = db.query(TABLE_NAME, null, " USERNAME = ? and PASSWORD = ?", new String[]{user, pass}, null, null, null);
        Cursor cursor2 = db.query(TABLE_NAME, null, " EMAIL = ? and PASSWORD = ?", new String[]{user, pass}, null, null, null);
        if(cursor1.getCount()==1 || cursor2.getCount()==1)
            return true;
        else
            return false;
    }

    public Cursor getEmail(String user){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor1 = db.query(TABLE_NAME, null, "USERNAME = ?", new String []{user}, null, null, null);
        Cursor cursor2 = db.query(TABLE_NAME, null, "EMAIL = ?", new String []{user}, null, null, null);
        if(cursor1 != null) {
            Log.d("yes", "yes");
            return cursor1;
        } else if(cursor2 != null){
            Log.d("yes", "no");
            return cursor2;
        } else{
            Log.d("yes", "veryno");
            return null;
        }
    }

    public void insertImage(byte[] imageBytes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_5, imageBytes);
        Log.d("dbUri", imageBytes.toString());
        db.insert(TABLE_NAME, null, cv);
    }

    // Get the image from SQLite DB
    // We will just get the last image we just saved for convenience...

    /*public byte[] retreiveImageFromDB() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.query(true, IMAGES_TABLE, new String[]{IMAGE,},
                null, null, null, null,
                IMAGE_ID + " DESC", "1");
        if (cur.moveToFirst()) {
            byte[] blob = cur.getBlob(cur.getColumnIndex(IMAGE));
            cur.close();
            return blob;
        }
        cur.close();
        return null;
    }*/

    public boolean deleteData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME);
        return true;
    }

    public void updateProfile(double height, double weight, String gender, String dob, String user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_6, height);
        cv.put(COL_7, weight);
        cv.put(COL_8, dob);
        cv.put(COL_9, gender);
        db.update(TABLE_NAME, cv, "USERNAME = ?", new String []{user});
    }
}
