package by.crispypigeon.secnotes.assistances.storage;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Provider;

public class SharedPreferencesAssistance {

    private Activity _context;

    private SharedPreferences sharedPreferences;

    @Inject
    public SharedPreferencesAssistance(Activity context) {
        _context = context;
        sharedPreferences = _context.getPreferences(Context.MODE_PRIVATE);
    }

    public void SaveString(String key, String string) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, string);
        editor.apply();
    }

    public void DeleteString(String key){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key).commit();
    }

    public String GetString(String key) {
        String defaultValue = "";
        return sharedPreferences.getString(key, defaultValue);
    }
}
