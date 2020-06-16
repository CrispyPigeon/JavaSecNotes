package by.crispypigeon.secnotes._model.main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import by.crispypigeon.secnotes.R;
import by.crispypigeon.secnotes.assistances.encryption.HashAssistance;
import by.crispypigeon.secnotes.assistances.storage.SharedPreferencesAssistance;

public class MainModel {

    private SharedPreferencesAssistance _sharedPreferencesAssistance;
    private HashAssistance _hashAssistance;

    public MainModel() {
        _sharedPreferencesAssistance = new SharedPreferencesAssistance();
        _hashAssistance = new HashAssistance();
    }

    public boolean isAccountExists() {
        String savedPass = _sharedPreferencesAssistance.GetString(R.string.hash_key); //TODO get string by key

        if (savedPass.isEmpty())
            return false;
        return true;
    }

    public void signUp(String password) {
        String hashedPass = _hashAssistance.getHashedPass(password);
        _sharedPreferencesAssistance.SaveString(hashedPass); //TODO add key
    }


    public boolean authenticate(String password) {
        String hashedPass = _hashAssistance.getHashedPass(password);
        String savedPass = _sharedPreferencesAssistance.GetString(R.string.hash_key); //TODO get string by key
        return comparePasswords(hashedPass, savedPass);
    }
}
