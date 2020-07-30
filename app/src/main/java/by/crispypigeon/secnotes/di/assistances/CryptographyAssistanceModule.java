package by.crispypigeon.secnotes.di.assistances;

import android.content.Context;

import javax.inject.Named;

import by.crispypigeon.secnotes.R;
import by.crispypigeon.secnotes.assistances.encryption.CryptographyAssistance;
import by.crispypigeon.secnotes.assistances.encryption.HashAssistance;
import by.crispypigeon.secnotes.assistances.storage.SharedPreferencesAssistance;
import by.crispypigeon.secnotes.di.AppComponentScope;
import by.crispypigeon.secnotes.di.activities.ActivityModule;
import by.crispypigeon.secnotes.di.activities.ActivityScope;
import by.crispypigeon.secnotes.di.context.ContextModule;
import dagger.Module;
import dagger.Provides;

@Module(includes = {ActivityModule.class, ContextModule.class})
public class CryptographyAssistanceModule {
    @Provides
    @ActivityScope
    public CryptographyAssistance provideHashAssistance(SharedPreferencesAssistance sharedPreferencesAssistance,
                                                        Context context) {
        return new CryptographyAssistance(sharedPreferencesAssistance.GetString(context.getString(R.string.hash_key)));
    }
}
