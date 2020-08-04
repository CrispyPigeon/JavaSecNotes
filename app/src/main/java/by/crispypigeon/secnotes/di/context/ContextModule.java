package by.crispypigeon.secnotes.di.context;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import by.crispypigeon.secnotes.di.AppComponentScope;
import by.crispypigeon.secnotes.di.activities.ActivityScope;
import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    private final Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ActivityScope
    public Context provideContext() {
        return context;
    }
}
