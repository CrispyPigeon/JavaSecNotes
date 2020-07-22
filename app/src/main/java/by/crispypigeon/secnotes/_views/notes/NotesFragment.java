package by.crispypigeon.secnotes._views.notes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import by.crispypigeon.secnotes.R;

public class NotesFragment extends Fragment {
    TextView toolbarTitleTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeControls();

        changeToolbarTitle();
    }

    private void changeToolbarTitle() {
        toolbarTitleTextView.setText(R.string.notesPageTitle);
    }

    private void initializeControls(){
        View view = getView();

        toolbarTitleTextView = view.findViewById(R.id.toolbarTitleTextView);
    }
}