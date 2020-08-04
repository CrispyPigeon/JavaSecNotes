package by.crispypigeon.secnotes._models.note;

import java.util.Calendar;

import javax.inject.Inject;

import by.crispypigeon.secnotes.assistances.database.RealmAssistance;
import by.crispypigeon.secnotes.assistances.encryption.CryptographyAssistance;
import by.crispypigeon.secnotes.data.EncryptedNote;
import by.crispypigeon.secnotes.data.Note;
import by.crispypigeon.secnotes.di.DaggerUtils;

public class NoteModel {

    @Inject
    public RealmAssistance realmAssistance;
    @Inject
    public CryptographyAssistance cryptographyAssistance;

    public NoteModel(){
        initializeDI();
    }

    private void initializeDI() {
        DaggerUtils.activityComponent.inject(this);
    }

    public boolean validateTexts(String noteTitle, String noteDescription) {
        if (noteTitle.isEmpty() || noteDescription.isEmpty())
            return false;
        return true;
    }

    public void saveOrUpdateNote(Note note) {

        EncryptedNote encryptedNote = new EncryptedNote();

        try {
            byte[] iv = cryptographyAssistance.getIV();
            encryptedNote.id = note.dbId;
            encryptedNote.title = cryptographyAssistance.encrypt(note.title.getBytes(), iv);
            encryptedNote.description = cryptographyAssistance.encrypt(note.description.getBytes(), iv);
            encryptedNote.date = Calendar.getInstance().getTime();
            encryptedNote.initializationVector = iv;

            realmAssistance.addOrUpdateEncryptedNote(encryptedNote);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeNote(Note note) {
        realmAssistance.deleteNoteById(note.dbId);
    }
}
