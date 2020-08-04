package by.crispypigeon.secnotes._models.auth;

import android.content.Context;

import javax.inject.Inject;

import by.crispypigeon.secnotes.R;
import by.crispypigeon.secnotes.assistances.database.RealmAssistance;
import by.crispypigeon.secnotes.assistances.encryption.HashAssistance;
import by.crispypigeon.secnotes.assistances.storage.SharedPreferencesAssistance;
import by.crispypigeon.secnotes.di.DaggerUtils;

public class AuthModel {

    private String passKey;

    @Inject
    public SharedPreferencesAssistance sharedPreferencesAssistance;
    @Inject
    public RealmAssistance realmAssistance;
    @Inject
    public HashAssistance hashAssistance;
    @Inject
    public Context context;

    public AuthModel() {
        initializeDI();

        passKey = context.getString(R.string.hash_key);
    }

    private void initializeDI() {
        DaggerUtils.activityComponent.inject(this);
    }

    public boolean isAccountExists() {
        String savedPass = sharedPreferencesAssistance.GetString(passKey);

        return validateString(savedPass);
    }

    public void signUp(String password) {
        String hashedPass = hashAssistance.getHashedPass(password);
        sharedPreferencesAssistance.SaveString(passKey, hashedPass);
    }

    public boolean signIn(String password) {
        String hashedPass = hashAssistance.getHashedPass(password);
        String savedPass = sharedPreferencesAssistance.GetString(passKey);
        return savedPass.equals(hashedPass);
    }

    public void resetPassword() {
        sharedPreferencesAssistance.DeleteString(passKey);
    }

    public void resetNotes() {
        realmAssistance.removeAll();
    }

    public boolean validateString(String string) {
        if (string.isEmpty())
            return false;
        return true;
    }
}
