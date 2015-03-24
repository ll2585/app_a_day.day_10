package com.lukeli.appaday.day10;

import android.content.ContentResolver;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    // The URL used to target the content provider
    static final Uri CONTENT_URL =
            Uri.parse("content://com.luke.appaday.day_9_words.WordContentProvider/words");

    TextView wordsTextView = null;
    CursorLoader cursorLoader;

    // Provides access to other applications Content Providers
    ContentResolver resolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resolver = getContentResolver();

        wordsTextView = (TextView) findViewById(R.id.wordsTextView);

        getWords();
    }

    public void getWords(){

        // Projection contains the columns we want
        String[] projection = new String[]{"id", "word", "definition"};

        // Pass the URL, projection and I'll cover the other options below
        Cursor cursor = resolver.query(CONTENT_URL, projection, null, null, null);

        String contactList = "";

        // Cycle through and display every row of data
        if(cursor.moveToFirst()){

            do{

                String id = cursor.getString(cursor.getColumnIndex("id"));
                String word = cursor.getString(cursor.getColumnIndex("word"));
                String definition = cursor.getString(cursor.getColumnIndex("definition"));

                contactList = contactList + id + " : " + word + ": " + definition + "\n";

            }while (cursor.moveToNext());

        }

        wordsTextView.setText(contactList);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showWords(View view) {

        getWords();
    }
}
