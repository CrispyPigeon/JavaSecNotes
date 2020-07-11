package by.crispypigeon.secnotes;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import by.crispypigeon.secnotes.assistances.encryption.HashAssistance;
import by.crispypigeon.secnotes.di.AppComponent;
import by.crispypigeon.secnotes.di.DaggerAppComponent;
import by.crispypigeon.secnotes.di.services.HashAssistanceModule;

public class MyApplication extends Application {

    private AppComponent appComponent;

    public static MyApplication get(Activity activity) {
        return (MyApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent
                .builder()
                .hashAssistanceModule(new HashAssistanceModule())
                .build();
    }

    public AppComponent getApplicationComponent() {
        return appComponent;
    }
}
