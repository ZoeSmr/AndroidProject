package com.example.diary;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyProvider extends ContentProvider {

    private Context mContext;
    NoteDBOpenHelper mDBHelper;
    SQLiteDatabase db = null;

    public static final String AUTOHORITY = "com.example.diary.myprovider";
    public static final int DIARY_CODE = 1;

    private static final UriMatcher myMatcher;
    static {
        myMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        myMatcher.addURI(AUTOHORITY, "note", DIARY_CODE);
    }

    @Override
    public boolean onCreate() {
        mContext = getContext();
        mDBHelper = new NoteDBOpenHelper(getContext());
        db = mDBHelper.getReadableDatabase();

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        String table = getTableName(uri);
        return db.query(table, projection, selection, selectionArgs, null, null, null);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        String table = getTableName(uri);
        db.insert(table, null, contentValues);
        mContext.getContentResolver().notifyChange(uri, null);
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    private String getTableName(Uri uri) {
        String tableName = null;

        if(myMatcher.match(uri) == DIARY_CODE) {
            tableName = "note";
        }
        return tableName;
    }
}
