package by.crispypigeon.secnotes.assistances.database;

import java.util.List;

import javax.inject.Inject;

import by.crispypigeon.secnotes.data.EncryptedNote;
import by.crispypigeon.secnotes.di.DaggerUtils;
import io.realm.Realm;
import io.realm.RealmObject;

public class RealmAssistance {

    private static final String idField = "id";

    public Realm realm;

    @Inject
    public RealmAssistance(Realm realm){
        this.realm = realm;

        initializeDI();
    }

    private void initializeDI() {
        DaggerUtils.activityComponent.inject(this);
    }

    public List<EncryptedNote> getEncryptedNotes() {
       return realm.where(EncryptedNote.class).findAll();
    }

    public void addEncryptedNote(EncryptedNote encryptedNote) {
        realm.beginTransaction();
        encryptedNote.id = getNewId(EncryptedNote.class);
        realm.copyToRealm(encryptedNote);
        realm.commitTransaction();
    }

    private <T extends RealmObject>int getNewId(Class<T> clazz){
        int id;
        id = realm.where(clazz).max(idField).intValue();
        if (id == 0){
            return 1;
        }
        else {
            return ++id;
        }
    }
}
