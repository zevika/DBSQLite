package com.zevika.dbsqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public interface SQLiteOpenHelper {
    void onCreate(SQLiteDatabase db);

    void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);

    boolean insertData(String nim, String nama, String kelas, String nohp);

    Cursor getAllData();

    boolean updateData(String nim, String nama, String kelas, String nohp);

    int deleteData(String nim);
}
