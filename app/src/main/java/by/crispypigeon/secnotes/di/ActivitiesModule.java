package by.crispypigeon.secnotes.di;

import android.app.Activity;

import by.crispypigeon.secnotes._views.auth.AuthActivity;
import dagger.Module;
import dagger.Provides;

@Module
public class ActivitiesModule {

    @Provides
    Activity provideActivity(AuthActivity activity){
        return activity;
    }
}
