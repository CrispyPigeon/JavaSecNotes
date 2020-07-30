package by.crispypigeon.secnotes._views.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import by.crispypigeon.secnotes.R;
import by.crispypigeon.secnotes.di.DaggerUtils;
import by.crispypigeon.secnotes.di.activities.ActivityModule;
import by.crispypigeon.secnotes.di.activities.DaggerActivityComponent;
import by.crispypigeon.secnotes.di.context.ContextModule;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeDI();
    }

    private void initializeDI() {
        DaggerUtils.activityComponent = DaggerActivityComponent.builder()
                .contextModule(new ContextModule(this))
                .activityModule(new ActivityModule(this))
                .appComponent(DaggerUtils.appComponent)
                .build();
    }
}
