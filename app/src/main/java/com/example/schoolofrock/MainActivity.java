package com.example.schoolofrock;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    // declare DBHandler
    DBHandler dbHandler;

    // declare EditText
    EditText voterEditText;

    // declare Spinners
    Spinner songSpinner;

    // declare Strings to store song selected in Spinners
    String song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // initialize DBHandler
        dbHandler = new DBHandler(this, null);

        // initialize EditText
        voterEditText = (EditText) findViewById(R.id.voterEditText);

        // initialize Spinners
        songSpinner = (Spinner) findViewById(R.id.songSpinner);


        // initialize ArrayAdapters with values in year and major string arrays
        // and stylize them with style defined by simple_spinner_item
        ArrayAdapter<CharSequence> songAdapter = ArrayAdapter.createFromResource(this,
                R.array.song, android.R.layout.simple_spinner_item);

        // further stylize ArrayAdapters with style defined by simple_spinner_dropdown_item
        songAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);


        // set ArrayAdapters on Spinners
        songSpinner.setAdapter(songAdapter);


        // register On Item Selected Listener to Spinners
        songSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // get the id of the menu item selected
        switch (item.getItemId()) {
            case R.id.action_count :
                // Display toast that has count of CIS students
                Toast.makeText(this, getMessage("Like"), Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public String getMessage (String vote) {
        int count = dbHandler.count(vote);
        return (count == 1 ? count + " Likes: " : count + " Dislikes: ");
    }

    public void like(MenuItem menuItem) {
        // get data input in EditText and store it in String
        String name = voterEditText.getText().toString();
        String song = songSpinner.getPrompt().toString();

        // trim Strings and see if they're equal to empty Strings
        if (name.trim().equals("")){
            // display "Please enter a name, store, and date!" Toast if any of the Strings are empty
            Toast.makeText(this, "Please enter a name!", Toast.LENGTH_LONG).show();
        } else {
            // add item into database
            dbHandler.like(name, song);

            // display "Student added!" Toast of none of the Strings are empty
            Toast.makeText(this, "Vote Cast!", Toast.LENGTH_LONG).show();
        }
    }

    public void dislike(MenuItem menuItem) {
        // get data input in EditText and store it in String
        String name = voterEditText.getText().toString();
        String song = songSpinner.getPrompt().toString();

        // trim Strings and see if they're equal to empty Strings
        if (name.trim().equals("")){
            // display "Please enter a name, store, and date!" Toast if any of the Strings are empty
            Toast.makeText(this, "Please enter a name!", Toast.LENGTH_LONG).show();
        } else {
            // add item into database
            dbHandler.like(name, song);

            // display "Student added!" Toast of none of the Strings are empty
            Toast.makeText(this, "Vote Cast!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // get the id of the Spinner that called method
        switch (parent.getId()) {
            case R.id.songSpinner:
                // get the item selected in the Spinner and store it in String
                song = parent.getItemAtPosition(position).toString();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}