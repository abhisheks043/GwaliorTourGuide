package com.example.abhisingh.gwalior;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ResourceCursorTreeAdapter;

/**
 * Created by Abhi Singh on 5/26/2016.
 */
public class MyDBHandler extends SQLiteOpenHelper
{

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="gwalior.db";
    private static final String TABLE_MON="monuments";
    private static final String TABLE_MUS="museums";
    private static final String TABLE_HOT="hotels";
    private static final String TABLE_EAT="eat_joints";
    private static final String TABLE_PUB="pubs";
    private static final String COLUMN_ID="_id";
    private static final String COLUMN_NAME="_name";
    private static final String COLUMN_DETAILS="_details";
    private static final String COLUMN_RATINGS="_ratings";

    Resources res;

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        res=context.getResources();
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String[] tables = new String[]{TABLE_MON,TABLE_MUS,TABLE_EAT,TABLE_HOT,TABLE_PUB};

        for(int i=0;i<5;i++)
        {
            String query = "CREATE TABLE " + tables[i] + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                    COLUMN_NAME + " TEXT ," +
                    COLUMN_DETAILS + " TEXT ," +
                    COLUMN_RATINGS + " TEXT " + ");";
            db.execSQL(query);
        }

        addMON();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MON);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MUS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EAT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PUB);
        onCreate(db);

    }

    public void addMON()
    {

        String[] names= res.getStringArray(R.array.mons);
        SQLiteDatabase db= getWritableDatabase();

        for(int i=0;i<8;i++)
        {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, names[i]);
            values.put(COLUMN_DETAILS, "");
            values.put(COLUMN_RATINGS, "");
            db.insert(TABLE_MON, null, values);

        }


        db.close();
    }


}
