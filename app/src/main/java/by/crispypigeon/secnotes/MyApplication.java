package by.crispypigeon.secnotes;

import android.app.Activity;
import android.app.Application;

import by.crispypigeon.secnotes.di.AppComponent;
import by.crispypigeon.secnotes.di.DaggerAppComponent;
import by.crispypigeon.secnotes.di.DaggerUtils;
import by.crispypigeon.secnotes.di.assistances.HashAssistanceModule;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {

    public static MyApplication get(Activity activity) {
        return (MyApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initializeDI();

        initializeRealm();
    }

    private void initializeRealm() {
        Realm.init(this);

        RealmConfiguration config = new  RealmConfiguration.Builder()
                .name(Consts.DB_Path)
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(0)
                .build();

        Realm.setDefaultConfiguration(config);
    }

    private void initializeDI() {
        AppComponent appComponent = DaggerAppComponent
                .builder()
                .hashAssistanceModule(new HashAssistanceModule())
                .build();

        DaggerUtils.appComponent = appComponent;
    }
}
