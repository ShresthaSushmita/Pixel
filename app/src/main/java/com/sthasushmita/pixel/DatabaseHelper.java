package com.example.sujal.pocketmed;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sujal on 6/4/2017.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, DBConstants.DB_NAME1, null, DBConstants.DB_VERSION1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(DBConstants.CREATE_QUERY1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DBConstants.DELETE_QUERY1);
        onCreate(db);
    }


}


