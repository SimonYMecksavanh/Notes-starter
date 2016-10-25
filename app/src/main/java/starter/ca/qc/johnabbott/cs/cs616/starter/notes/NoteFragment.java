package starter.ca.qc.johnabbott.cs.cs616.starter.notes;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.Date;

import starter.ca.qc.johnabbott.cs.cs616.starter.notes.model.Note;

/**
 * A placeholder fragment containing a simple view.
 */
public class NoteFragment extends Fragment {

    private EditText title;


    public NoteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_note, container, false);

        title = (EditText) root.findViewById(R.id.title_EditText);

        return root;
    }

    public Note getNote() {
        return new Note(title.getText().toString(), "", -1, false, null, new Date());
    }
}


