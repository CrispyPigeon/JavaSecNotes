package by.crispypigeon.secnotes;

import android.app.Activity;
import android.app.Application;

import by.crispypigeon.secnotes.di.AppComponent;
import by.crispypigeon.secnotes.di.DaggerAppComponent;

public class MyApplication extends Application {
    private AppComponent appComponent;

    public static MyApplication get(Activity activity) {
        return (MyApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder().build();
    }

    public AppComponent getApplicationComponent() {
        return appComponent;
    }
}
