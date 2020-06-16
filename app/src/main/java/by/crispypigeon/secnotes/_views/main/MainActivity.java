package by.crispypigeon.secnotes._views.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import by.crispypigeon.secnotes.R;
import by.crispypigeon.secnotes._presenters.main.MainPresenter;

public class MainActivity extends AppCompatActivity implements IMainView {

    private MainPresenter _mainPresenter;

    private Button authButton;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _mainPresenter = new MainPresenter(this);

        initializeControls();

        configureEvents();
    }

    private void initializeControls() {
        authButton = findViewById(R.id.authButton);
        passwordEditText = findViewById(R.id.passwordEditText);
    }

    private void configureEvents() {
        authButton.setOnClickListener(x -> {
            _mainPresenter.authenticateUser(passwordEditText.getText().toString());
        });
    }

    @Override
    public void loadSignInView() {
        //TODO button clicked UI
    }
}
