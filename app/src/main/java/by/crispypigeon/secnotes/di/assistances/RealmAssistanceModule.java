package by.crispypigeon.secnotes.di.assistances;

import by.crispypigeon.secnotes.assistances.database.RealmAssistance;
import by.crispypigeon.secnotes.di.AppComponentScope;
import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class RealmAssistanceModule {
    @Provides
    @AppComponentScope
    public RealmAssistance provideRealmAssistance(){
        return new RealmAssistance(Realm.getDefaultInstance());
    }
}
