package starter.ca.qc.johnabbott.cs.cs616.starter.notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import starter.ca.qc.johnabbott.cs.cs616.starter.notes.model.DatabaseException;
import starter.ca.qc.johnabbott.cs.cs616.starter.notes.model.DatabaseHandler;
import starter.ca.qc.johnabbott.cs.cs616.starter.notes.model.Note;

public class NoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        long id = intent.getLongExtra("ID", -1);

        if(id >= 0){
            DatabaseHandler dbh = new DatabaseHandler(this);
            try {
                Note note = dbh.getNoteTable().read(id);
                NoteFragment noteFragment = (NoteFragment) getSupportFragmentManager().findFragmentById(R.id.note_fragment);
                noteFragment.load(note);
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note, menu);
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
        if (id == R.id.action_save) {
            NoteFragment frag = (NoteFragment) getSupportFragmentManager().findFragmentById(R.id.note_fragment);

            Note note = frag.getNote();
           // Log.d("DEBUG", frag.getNote().toString());

            DatabaseHandler dbh = new DatabaseHandler(this);

            try {
                if(note.getId() >= 0)
                    dbh.getNoteTable().update(frag.getNote());
                else
                    dbh.getNoteTable().create(frag.getNote());
                setResult(RESULT_OK);
                finish();
            } catch (DatabaseException e) {
                e.printStackTrace();
            }

        }

        return super.onOptionsItemSelected(item);
    }
}
