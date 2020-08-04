package by.crispypigeon.secnotes._views.note;

import by.crispypigeon.secnotes.data.Note;

public interface INoteView {
    void showNoDataToast();

    void showSuccessSavingToast();

    void showNote(Note note);

    void removeDeleteIcon();
}
