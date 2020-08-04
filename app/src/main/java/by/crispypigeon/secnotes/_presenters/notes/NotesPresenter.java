package by.crispypigeon.secnotes._presenters.notes;

import java.util.ArrayList;

import by.crispypigeon.secnotes._models.notes.NotesModel;
import by.crispypigeon.secnotes._views.notes.INotesView;
import by.crispypigeon.secnotes.data.Note;

public class NotesPresenter {

    private NotesModel notesModel;
    private INotesView notesView;

    public NotesPresenter(INotesView notesView) {
        this.notesView = notesView;
        notesModel = new NotesModel();
    }

    public void showNotes() {
        ArrayList<Note> notes = notesModel.getDecryptedNotes();

        if (notes.size() > 0)
            notesView.showNotes(notes);
    }
}
