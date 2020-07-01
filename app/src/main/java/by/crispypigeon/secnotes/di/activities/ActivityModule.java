package by.crispypigeon.secnotes.di.activities;

import android.app.Activity;

import by.crispypigeon.secnotes.assistances.storage.SharedPreferencesAssistance;
import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    public SharedPreferencesAssistance provideSharedPreferencesAssistance(){
        return new SharedPreferencesAssistance(activity);
    }
}
