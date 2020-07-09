package by.crispypigeon.secnotes._models.auth;

import android.content.Context;

import javax.inject.Inject;

import by.crispypigeon.secnotes.MyApplication;
import by.crispypigeon.secnotes.R;
import by.crispypigeon.secnotes.assistances.encryption.HashAssistance;
import by.crispypigeon.secnotes.assistances.storage.SharedPreferencesAssistance;
import by.crispypigeon.secnotes.di.DaggerAppComponent;

public class AuthModel {

    private String passKey;

    @Inject
    public SharedPreferencesAssistance _sharedPreferencesAssistance;
    @Inject
    public HashAssistance _hashAssistance;
    @Inject
    public Context _context;

    public AuthModel() {
        passKey = _context.getString(R.string.hash_key);
    }

    public boolean isAccountExists() {
        String savedPass = _sharedPreferencesAssistance.GetString(passKey);

        if (savedPass.isEmpty())
            return false;
        return true;
    }

    public void signUp(String password) {
        String hashedPass = _hashAssistance.getHashedPass(password);
        _sharedPreferencesAssistance.SaveString(passKey, hashedPass);
    }

    public boolean authenticate(String password) {
        String hashedPass = _hashAssistance.getHashedPass(password);
        String savedPass = _sharedPreferencesAssistance.GetString(passKey);
        return savedPass.equals(hashedPass);
    }
}
