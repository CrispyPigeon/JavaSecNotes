package by.crispypigeon.secnotes.di.assistances;

import by.crispypigeon.secnotes.assistances.encryption.HashAssistance;
import by.crispypigeon.secnotes.di.AppComponentScope;
import dagger.Module;
import dagger.Provides;

@Module
public class HashAssistanceModule {
    private String hashType = "SHA256";

    @Provides
    @AppComponentScope
    public HashAssistance provideHashAssistance(){
        return new HashAssistance(hashType);
    }
}
