package by.crispypigeon.secnotes;

import android.app.Application;

import by.crispypigeon.secnotes.di.AppComponent;
import by.crispypigeon.secnotes.di.DaggerAppComponent;

public class MyApplication extends Application {
    AppComponent appComponent = DaggerAppComponent.create();
}
