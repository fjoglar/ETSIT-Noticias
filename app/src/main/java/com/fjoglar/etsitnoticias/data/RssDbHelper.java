package com.fjoglar.etsitnoticias.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fjoglar.etsitnoticias.data.RssContract.RssEntry;

/**
 * Gestiona la base de datos local de los datos RSS.
 */
public class RssDbHelper extends SQLiteOpenHelper {

    // Si se cambia el esquema de la base de datos, se debe incrementar la versión de la base
    // de datos.
    private static final int DATABASE_VERSION = 1;

    // Nombre de la base de datos.
    static final String DATABASE_NAME = "rss.db";

    public RssDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Crear una tabla para almacenar las noticias.
        // Una noticia consiste de título, descripción, un link de información,
        // una categoria y una fecha de publicación.
        final String SQL_CREATE_RSS_TABLE = "CREATE TABLE " + RssEntry.TABLE_NAME + " (" +
                RssEntry._ID + " INTEGER PRIMARY KEY," +
                RssEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
                RssEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
                RssEntry.COLUMN_LINK + " TEXT NOT NULL, " +
                RssEntry.COLUMN_CATEGORY + " TEXT NOT NULL, " +
                RssEntry.COLUMN_PUB_DATE + " INTEGER NOT NULL" +
                " );";

        sqLiteDatabase.execSQL(SQL_CREATE_RSS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Esta base de datos es sólo un caché de datos online, así que la política de actualización
        // es simplemente descartar los datos y empezar de nuevo.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RssEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

}
