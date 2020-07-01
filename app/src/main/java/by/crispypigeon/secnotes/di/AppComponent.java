package by.crispypigeon.secnotes.di;

import android.app.Activity;
import android.app.Application;

import javax.inject.Singleton;

import by.crispypigeon.secnotes._views.auth.AuthActivity;
import by.crispypigeon.secnotes.assistances.encryption.HashAssistance;
import by.crispypigeon.secnotes.assistances.storage.SharedPreferencesAssistance;
import dagger.BindsInstance;
import dagger.Component;

@AppComponentScope
@Component
public interface AppComponent {
    HashAssistance getHashAssistance();
}
