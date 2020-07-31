package by.crispypigeon.secnotes._views.note;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.crispypigeon.secnotes.R;
import by.crispypigeon.secnotes.data.Note;

public class NoteFragment extends Fragment implements INoteView {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            NoteFragmentArgs arg = NoteFragmentArgs.fromBundle(getArguments());
            Note note = arg.getNote();
        }
    }
}