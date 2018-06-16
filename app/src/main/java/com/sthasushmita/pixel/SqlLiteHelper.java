package com.example.sujal.pocketmed;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sujal on 6/2/2017.
 */
public class SqlLiteHelper extends SQLiteOpenHelper {
    public SqlLiteHelper(Context context) {
        super(context, DBConstant.DB_NAME, null, DBConstant.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(DBConstant.CREATE_QUERY);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DBConstant.DELETE_QUERY);
        onCreate(db);
    }
}