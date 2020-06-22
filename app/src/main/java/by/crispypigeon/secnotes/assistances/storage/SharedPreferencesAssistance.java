package by.crispypigeon.secnotes.assistances.storage;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Provider;

public class SharedPreferencesAssistance {

    @Inject
    private Activity _context;

    public SharedPreferencesAssistance(Provider<Activity> context) {
        _context = context.get();
    }

    public void SaveString(String key, String string) {
        SharedPreferences sharedPref = _context.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, string);
        editor.apply();
    }

    public String GetString(String key){
        SharedPreferences sharedPref = _context.getPreferences(Context.MODE_PRIVATE);
        String defaultValue = "";
        return sharedPref.getString(key, defaultValue);
    }
}
