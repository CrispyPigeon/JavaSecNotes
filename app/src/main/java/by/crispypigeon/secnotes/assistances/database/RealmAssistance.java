package by.crispypigeon.secnotes.assistances.database;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import by.crispypigeon.secnotes.data.EncryptedNote;
import by.crispypigeon.secnotes.di.DaggerUtils;
import io.realm.Realm;
import io.realm.RealmObject;

public class RealmAssistance {

    private static final String idField = "id";

    public Realm realm;

    @Inject
    public RealmAssistance(Realm realm) {
        this.realm = realm;

        initializeDI();
    }

    private void initializeDI() {
        DaggerUtils.activityComponent.inject(this);
    }

    public List<EncryptedNote> getEncryptedNotes() {
        return realm.where(EncryptedNote.class).findAll();
    }

    public void addOrUpdateEncryptedNote(EncryptedNote encryptedNote) {
        realm.beginTransaction();

        if (encryptedNote.id == 0)
            encryptedNote.id = getNewId(EncryptedNote.class);

        realm.copyToRealmOrUpdate(encryptedNote);
        realm.commitTransaction();
    }

    private <T extends RealmObject> int getNewId(Class<T> clazz) {
        Number id = realm.where(clazz).max(idField);
        return (id == null) ? 1 : id.intValue() + 1;
    }

    public void deleteNoteById(int dbId) {
        EncryptedNote encryptedNote = realm.where(EncryptedNote.class).equalTo(idField,dbId).findFirst();
        if (encryptedNote != null){
            realm.beginTransaction();
            encryptedNote.deleteFromRealm();
            realm.commitTransaction();
        }
    }

    public void removeAll() {
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }
}
