package by.crispypigeon.secnotes.di;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import by.crispypigeon.secnotes.MyApplication;
import by.crispypigeon.secnotes._models.auth.AuthModel;
import by.crispypigeon.secnotes._views.auth.AuthActivity;
import by.crispypigeon.secnotes.assistances.encryption.HashAssistance;
import by.crispypigeon.secnotes.assistances.storage.SharedPreferencesAssistance;
import by.crispypigeon.secnotes.di.context.ContextModule;
import by.crispypigeon.secnotes.di.services.HashAssistanceModule;
import dagger.BindsInstance;
import dagger.Component;

@AppComponentScope
@Component (modules = HashAssistanceModule.class)
public interface AppComponent {
    HashAssistance getHashAssistance();

}
