package by.crispypigeon.secnotes.data;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class EncryptedNote extends RealmObject {

    @PrimaryKey
    public int id = 0;

    public byte[] title;

    public byte[] description;

    public Date date;

    public byte[] initializationVector;

    public EncryptedNote() {    }

    public EncryptedNote(byte[] title, byte[] description, byte[] initializationVector, Date date) {
        this.title = title;
        this.description = description;
        this.initializationVector = initializationVector;
        this.date = date;
    }
}
