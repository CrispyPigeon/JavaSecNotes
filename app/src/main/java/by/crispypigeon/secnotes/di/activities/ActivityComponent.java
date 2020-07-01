package by.crispypigeon.secnotes.di.activities;

import by.crispypigeon.secnotes.assistances.storage.SharedPreferencesAssistance;
import by.crispypigeon.secnotes.di.AppComponent;
import dagger.Component;

@Component(dependencies = AppComponent.class,
        modules = ActivityModule.class)
@ActivityScope
public interface ActivityComponent {

    SharedPreferencesAssistance getSharedPreferencesAssistance();
}
