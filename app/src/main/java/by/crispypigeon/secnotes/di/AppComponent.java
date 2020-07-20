package by.crispypigeon.secnotes.di;

import by.crispypigeon.secnotes.assistances.encryption.HashAssistance;
import by.crispypigeon.secnotes.di.services.HashAssistanceModule;
import dagger.Component;

@AppComponentScope
@Component (modules = HashAssistanceModule.class)
public interface AppComponent {
    HashAssistance getHashAssistance();

}
