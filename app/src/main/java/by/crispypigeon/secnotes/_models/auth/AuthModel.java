package by.crispypigeon.secnotes._models.auth;

import android.content.Context;

import javax.inject.Inject;

import by.crispypigeon.secnotes.R;
import by.crispypigeon.secnotes.assistances.encryption.HashAssistance;
import by.crispypigeon.secnotes.assistances.storage.SharedPreferencesAssistance;
import by.crispypigeon.secnotes.di.DaggerUtils;

public class AuthModel {

    private String passKey;

    @Inject
    public SharedPreferencesAssistance sharedPreferencesAssistance;
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

        if (savedPass.isEmpty())
            return false;
        return true;
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
}
