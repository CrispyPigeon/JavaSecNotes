package by.crispypigeon.secnotes._models.auth;

import javax.inject.Inject;

import by.crispypigeon.secnotes.assistances.encryption.HashAssistance;
import by.crispypigeon.secnotes.assistances.storage.SharedPreferencesAssistance;
import by.crispypigeon.secnotes.di.DaggerAppComponent;

public class AuthModel {

    private SharedPreferencesAssistance _sharedPreferencesAssistance;
    private HashAssistance _hashAssistance = DaggerAppComponent.create().getHashAssistance();

    public AuthModel() {
        //_sharedPreferencesAssistance = new SharedPreferencesAssistance();
        //_hashAssistance = new HashAssistance();
    }

    public boolean isAccountExists() {
        // String savedPass = _sharedPreferencesAssistance.GetString(R.string.hash_key); //TODO get string by key

//        if (savedPass.isEmpty())
//            return false;
        return true;
    }

    public void signUp(String password) {
        String hashedPass = _hashAssistance.getHashedPass(password);
        //_sharedPreferencesAssistance.SaveString(hashedPass); //TODO add key
    }

    public boolean authenticate(String password) {
        String hashedPass = _hashAssistance.getHashedPass(password);
//        String savedPass = _sharedPreferencesAssistance.GetString(R.string.hash_key); //TODO get string by key
        return true; //comparePasswords(hashedPass, savedPass);
    }
}
