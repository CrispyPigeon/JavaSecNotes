package by.crispypigeon.secnotes._views.notes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import by.crispypigeon.secnotes.R;
import by.crispypigeon.secnotes._presenters.notes.NotesPresenter;
import by.crispypigeon.secnotes._views.notes.RecyclerView.NotesAdapter;
import by.crispypigeon.secnotes.data.Note;

public class NotesFragment extends Fragment implements INotesView {

    private TextView toolbarTitleTextView;
    private Button addButton;

    private NotesAdapter notesAdapter;

    private NotesPresenter notesPresenter;

    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        initializeControls();

        changeToolbarTitle();

        notesPresenter = new NotesPresenter(this);
    }

    @Override
    public void showNotes(ArrayList<Note> notes) {
        notesAdapter.setNotesList(notes);
    }

    private void changeToolbarTitle() {
        toolbarTitleTextView.setText(R.string.notesPageTitle);
    }

    private void initializeControls() {
        View view = getView();

        toolbarTitleTextView = view.findViewById(R.id.toolbarTitleTextView);
        addButton = view.findViewById(R.id.addButton);
        addButton.setOnClickListener(x -> {
            NotesFragmentDirections.ActionNotesFragmentToNoteFragment action =
                    NotesFragmentDirections.actionNotesFragmentToNoteFragment(null);
            navController.navigate(action);
        });

        initializeRecyclerView(view);
    }

    private void initializeRecyclerView(@NotNull View view) {
        RecyclerView recyclerView = view.findViewById(R.id.notesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        notesAdapter = new NotesAdapter();
        recyclerView.setAdapter(notesAdapter);
    }
}