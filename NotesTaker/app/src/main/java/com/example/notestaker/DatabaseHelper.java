package com.example.notestaker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Struct;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Notes_5";
    public static final String TABLE_NAME="Note";
    public static final String COL_1="ID";
    public static final String COL_2="WORD";
    public static final String COL_3="DESCRIPTION";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,WORD TEXT,DESCRIPTION TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }
    public boolean insertData(String word,String meaning){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,word);
        contentValues.put(COL_3,meaning);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor get_all_data(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
  /*  public boolean update_dbms(String id,String name,String surname,double marks){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        db.update(TABLE_NAME,contentValues,"ID=?",new String[]{id});
        return true;
    }
    public int delete_dbms(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        return db.delete(TABLE_NAME,"ID=?",new String[]{id});
    }*/



    public Cursor get_all_data_hist_asc(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.query(TABLE_NAME,new String[]{COL_2,COL_3}, null, null, null, null, COL_2+" ASC");
        return res;
    }
}
