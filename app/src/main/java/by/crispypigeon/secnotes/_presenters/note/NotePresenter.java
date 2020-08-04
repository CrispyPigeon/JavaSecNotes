package by.crispypigeon.secnotes._presenters.note;

import by.crispypigeon.secnotes._models.note.NoteModel;
import by.crispypigeon.secnotes._views.note.INoteView;
import by.crispypigeon.secnotes.data.Note;

public class NotePresenter {

    private INoteView noteView;
    private NoteModel noteModel;

    private Note note;

    public NotePresenter(INoteView view, Note noteFromArgs) {
        noteView = view;
        noteModel = new NoteModel();

        note = noteFromArgs;

        showCurrentNote();
    }

    private void showCurrentNote() {
        if (note == null) {
            note = new Note();
            noteView.removeDeleteIcon();
        } else {
            noteView.showNote(note);
        }
    }

    public void saveNote(String noteTitle, String noteDescription) {

        if (!noteModel.validateTexts(noteTitle, noteDescription)) {
            noteView.showNoDataToast();
            return;
        }

        note.title = noteTitle;
        note.description = noteDescription;

        noteModel.saveOrUpdateNote(note);

        noteView.showSuccessSavingToast();
    }

    public void removeCurrentNote() {
        noteModel.removeNote(note);
    }
}
