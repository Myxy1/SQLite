package com.example.gyorgyitamas.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/*
        HASZNÁLHATÓ LINKEK:
        http://sqlitebrowser.org/
        https://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html
        https://developer.android.com/training/data-storage/sqlite.html
        https://developer.android.com/training/data-storage/room/index.html

 */

public class AdatbazisSegito extends SQLiteOpenHelper {

    // ADATBÁZIS FELÉPÍTÉSE

    public static final String DATABASE_NAME = "Tanulo.db";     //ADATBÁZIS FILE NEVE
    public static final String TABLE_NAME = "Tanulo_tabla";     //TÁBLA NEVE

    public static final String COL_1 = "ID";                    //ELSŐ OSZLOP NEVE
    public static final String COL_2 = "KERESZTNEV";            //MÁSODIK OSZLOP NEVE
    public static final String COL_3 = "VEZETEKNEV";            //HARMADIK OSZLOP NEVE
    public static final String COL_4 = "JEGY";                  //NEGYEDIK OSZLOP NEVE

    public AdatbazisSegito(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    //LÉTREHOZZUK A TÁBLÁT ÉS A BENNE LÉVŐ OSZLOPOKHOZ TÍPUST ADUNK

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, KERESZTNEV TEXT, VEZETEKNEV TEXT, JEGY INTEGER)");
    }

    //DOBJA EL A TÁBLÁT HA MÁR ILYEN LÉTEZIK

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

    }

    //ADAT FELVÉTEL

    public boolean adatRogzites(String keresztnev, String vezeteknev, String jegy){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,keresztnev);
        contentValues.put(COL_3,vezeteknev);
        contentValues.put(COL_4,jegy);

        long result = db.insert(TABLE_NAME,null,contentValues);

        if (result == -1){
            return false;       //sikertelen felvétel esetén false eredményt add vissza
        }else{
            return true;       //sikeres felvétel esetén true eredményt add vissza
        }
    }

    //ADAT LEKÉRDEZÉS

    public Cursor adatLekerdezes()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from " + TABLE_NAME,null);
        return res;
    }

    //ADAT TÖRLÉS

    public Integer adatTorles(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(TABLE_NAME, "ID=?", new String[]{id});
        return i;
    }
}
