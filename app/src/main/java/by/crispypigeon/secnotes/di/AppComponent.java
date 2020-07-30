package by.crispypigeon.secnotes.di;

import by.crispypigeon.secnotes.assistances.database.RealmAssistance;
import by.crispypigeon.secnotes.assistances.encryption.HashAssistance;
import by.crispypigeon.secnotes.di.assistances.HashAssistanceModule;
import by.crispypigeon.secnotes.di.assistances.RealmAssistanceModule;
import dagger.Component;

@AppComponentScope
@Component(modules = {HashAssistanceModule.class, RealmAssistanceModule.class})
public interface AppComponent {
    HashAssistance getHashAssistance();

    RealmAssistance getRealmAssistance();
}
