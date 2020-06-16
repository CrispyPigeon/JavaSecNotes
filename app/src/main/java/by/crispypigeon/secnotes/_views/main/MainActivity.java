package by.crispypigeon.secnotes._views.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import by.crispypigeon.secnotes.R;
import by.crispypigeon.secnotes._presenters.main.MainPresenter;

public class MainActivity extends AppCompatActivity {

    MainPresenter _mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _mainPresenter = new MainPresenter(this);

        configureEvents();
    }

    private void configureEvents() {

    }


}
