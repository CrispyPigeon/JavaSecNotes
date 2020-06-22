package by.crispypigeon.secnotes._views.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import by.crispypigeon.secnotes.R;
import by.crispypigeon.secnotes._presenters.auth.AuthPresenter;
import by.crispypigeon.secnotes.di.DaggerAppComponent;

public class AuthActivity extends AppCompatActivity implements IAuthView {

    private AuthPresenter _authPresenter;

    private Button authButton;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        _authPresenter = new AuthPresenter(this);

        initializeDI();

        initializeControls();

        configureEvents();
    }

    private void initializeDI() {
        DaggerAppComponent.create().inject(this);
    }

    private void initializeControls() {
        authButton = findViewById(R.id.authButton);
        passwordEditText = findViewById(R.id.passwordEditText);
    }

    private void configureEvents() {
        authButton.setOnClickListener(x -> {
            _authPresenter.authenticateUser(passwordEditText.getText().toString());
        });
    }

    @Override
    public void loadSignInView() {
        //TODO button clicked UI
    }
}
