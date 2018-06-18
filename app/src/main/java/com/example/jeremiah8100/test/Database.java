package com.example.jeremiah8100.test;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Display;

import com.example.jeremiah8100.test.Items.Event;

import java.util.ArrayList;
import java.util.List;

public class Database {
    public static Database db = new Database();
    SQLiteDatabase database;
    String file;
    Context context;


    private Database() {

    }

    public void Init(String file, Context context){
        this.file = file;
        this.context = context;
        Open();
        System.out.println("db opened");
        database.execSQL("CREATE TABLE IF NOT EXISTS bookmarks(Eventid TEXT)");
        Close();
    }

    public void AddEvent(Event event) {
        Open();
        Cursor select = database.rawQuery("SELECT * FROM events WHERE Eventid=?", new String[]{ event.Id});
        System.out.println("count: " + select.getCount());
        Close();
    }

    private void Open() {
        if(database == null || !database.isOpen())
            database = SQLiteDatabase.openOrCreateDatabase(context.getFilesDir().getAbsolutePath() + file, null);
    }

    private void Close() {
        if(database != null && database.isOpen())
            database.close();
    }



    public boolean IsBookMarked(Event event){
        int count = 0;
        Open();
        Cursor select = database.rawQuery("SELECT * FROM bookmarks WHERE Eventid=?", new String[]{ event.Id});
        count = select.getCount();
        select.close();
        Close();
        System.out.println("Count: " + count);
        return count > 0;
    }

    public void BookmarkEvent(Event event){
        Open();
        database.execSQL("INSERT INTO bookmarks (Eventid)VALUES('"+event.Id+"')");
        Close();
    }

    public void UnbookmarkEvent(Event event){
        Open();
        database.execSQL("DELETE FROM bookmarks WHERE Eventid='"+event.Id+"'");
        Close();
    }

    public void GetTickets() {

    }
}
