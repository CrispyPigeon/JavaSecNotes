package by.crispypigeon.secnotes._views.note;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import by.crispypigeon.secnotes.R;
import by.crispypigeon.secnotes._presenters.note.NotePresenter;
import by.crispypigeon.secnotes._views._controls.dialogs.MessageAlertDialog;
import by.crispypigeon.secnotes.data.Note;

public class NoteFragment extends Fragment implements INoteView {

    private EditText noteTitleEditText;
    private EditText noteDescriptionEditText;
    private Button noteAcceptButton;
    private TextView toolbarTitleTextView;
    private ImageButton backToolBarButton;
    private ImageButton deleteToolBarButton;

    private MessageAlertDialog messageAlertDialog;

    private NavController navController;

    private NotePresenter notePresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        initializeControls();

        initializeEvents();

        changeToolbarTitle();

        notePresenter = new NotePresenter(this, getNoteFromArgs());
    }

    private Note getNoteFromArgs() {
        if (getArguments() != null) {
            NoteFragmentArgs arg = NoteFragmentArgs.fromBundle(getArguments());
            return arg.getNote();
        }
        return null;
    }

    private void initializeControls() {
        View view = getView();

        toolbarTitleTextView = view.findViewById(R.id.toolbarTitleTextView);
        backToolBarButton = view.findViewById(R.id.toolbarBackImageButton);
        deleteToolBarButton = view.findViewById(R.id.toolbarDeleteImageButton);
        noteTitleEditText = view.findViewById(R.id.noteTitleEditText);
        noteDescriptionEditText = view.findViewById(R.id.noteDescriptionEditText);
        noteAcceptButton = view.findViewById(R.id.noteAcceptButton);

        messageAlertDialog = new MessageAlertDialog(getString(R.string.deleteNoteTitle),
                getString(R.string.deleteNoteMessage),
                getString(R.string.positiveAnswer));
    }

    private void changeToolbarTitle() {
        toolbarTitleTextView.setText(R.string.notePageTitle);
    }

    private void initializeEvents() {
        noteAcceptButton.setOnClickListener(x -> {
            notePresenter.saveNote(noteTitleEditText.getText().toString(), noteDescriptionEditText.getText().toString());
        });
        backToolBarButton.setOnClickListener(x -> {
            navController.navigateUp();
        });
        deleteToolBarButton.setOnClickListener(x -> {
            messageAlertDialog.show(getActivity().getSupportFragmentManager(),"deleteDialog");
        });

        messageAlertDialog.setNoticeDialogListener(x -> {
            notePresenter.removeCurrentNote();
            navController.navigateUp();
        });
    }

    @Override
    public void showNoDataToast() {
        Toast textToast = Toast.makeText(getContext(), R.string.noteNoDataMessage, Toast.LENGTH_SHORT);
        textToast.show();
    }

    @Override
    public void showSuccessSavingToast() {
        Toast textToast = Toast.makeText(getContext(), R.string.noteSuccessSavingMessage, Toast.LENGTH_SHORT);
        textToast.show();
        navController.navigateUp();
    }

    @Override
    public void showNote(Note note) {
        noteTitleEditText.setText(note.title);
        noteDescriptionEditText.setText(note.description);
    }

    @Override
    public void removeDeleteIcon() {
        deleteToolBarButton.setVisibility(View.INVISIBLE);
    }
}