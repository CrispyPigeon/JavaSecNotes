package by.crispypigeon.secnotes.di.activities;

import android.content.Context;

import by.crispypigeon.secnotes._models.auth.AuthModel;
import by.crispypigeon.secnotes._models.notes.NotesModel;
import by.crispypigeon.secnotes.assistances.database.RealmAssistance;
import by.crispypigeon.secnotes.assistances.encryption.CryptographyAssistance;
import by.crispypigeon.secnotes.assistances.storage.SharedPreferencesAssistance;
import by.crispypigeon.secnotes.di.AppComponent;
import by.crispypigeon.secnotes.di.assistances.CryptographyAssistanceModule;
import by.crispypigeon.secnotes.di.context.ContextModule;
import dagger.Component;

@Component(dependencies = AppComponent.class,
        modules = {
                ActivityModule.class,
                ContextModule.class,
                CryptographyAssistanceModule.class
        })
@ActivityScope
public interface ActivityComponent {

    Context getContext();

    SharedPreferencesAssistance getSharedPreferencesAssistance();

    CryptographyAssistance getCryptographyAssistance();

    void inject(AuthModel obj);

    void inject(NotesModel obj);

    void inject(RealmAssistance obj);

    void inject(CryptographyAssistance obj);
}
