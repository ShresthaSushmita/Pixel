package com.example.sujal.pocketmed;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by sujal on 6/4/2017.
 */
public class DatabaseAdapter1{
    public DatabaseHelper helper;
    public SQLiteDatabase db1;

    public DatabaseAdapter1(Context context) {
        helper = new DatabaseHelper(context);
        db1= helper.getWritableDatabase();
    }

    public long insertData(ContentValues contentValues) {
        long id = db1.insert(DBConstants.TB_NAME1, null, contentValues);
        return id;
    }

   public Cursor getAllDataa()
   {
       String[] z=new String[]{DBConstants._ID,DBConstants.MED_FULLNAME,DBConstants.MED_EMAIL,DBConstants.MED_USERNAME,DBConstants.MED_PASSWORD,DBConstants.MED_CPASSWORD};
       Cursor x=db1.query(DBConstants.TB_NAME1,z,null,null,null,null,null);
       return x;
   }


}



