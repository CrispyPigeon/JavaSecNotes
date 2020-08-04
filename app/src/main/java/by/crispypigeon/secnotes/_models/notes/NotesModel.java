package by.crispypigeon.secnotes._models.notes;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;

import by.crispypigeon.secnotes.assistances.database.RealmAssistance;
import by.crispypigeon.secnotes.assistances.encryption.CryptographyAssistance;
import by.crispypigeon.secnotes.data.EncryptedNote;
import by.crispypigeon.secnotes.data.Note;
import by.crispypigeon.secnotes.di.DaggerUtils;
import io.realm.Realm;

public class NotesModel {

    @Inject
    public CryptographyAssistance cryptographyAssistance;

    @Inject
    public RealmAssistance realm;

    public NotesModel() {
        DaggerUtils.activityComponent.inject(this);
    }

    public ArrayList<Note> getDecryptedNotes() {
        List<EncryptedNote> encryptedNotes = realm.getEncryptedNotes();
        return decryptNotes(encryptedNotes);
    }

    private ArrayList<Note> decryptNotes(List<EncryptedNote> encryptedNotes) {
        ArrayList<Note> notes = new ArrayList<Note>();
        for (EncryptedNote encryptedNote : encryptedNotes) {
            String title = cryptographyAssistance.decrypt(encryptedNote.title, encryptedNote.initializationVector);
            String description = cryptographyAssistance.decrypt(encryptedNote.description, encryptedNote.initializationVector);
            Date date = encryptedNote.date;
            int id = encryptedNote.id;

            notes.add(new Note(id, title, description, date));
        }

        return notes;
    }
}
