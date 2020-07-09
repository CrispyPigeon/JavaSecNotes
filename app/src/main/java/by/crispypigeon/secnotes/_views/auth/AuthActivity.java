package by.crispypigeon.secnotes._views.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import javax.inject.Inject;

import by.crispypigeon.secnotes.MyApplication;
import by.crispypigeon.secnotes.R;
import by.crispypigeon.secnotes._presenters.auth.AuthPresenter;
import by.crispypigeon.secnotes.di.activities.ActivityComponent;
import by.crispypigeon.secnotes.di.activities.ActivityModule;
import by.crispypigeon.secnotes.di.activities.DaggerActivityComponent;
import by.crispypigeon.secnotes.di.context.ContextModule;

public class AuthActivity extends AppCompatActivity implements IAuthView {

    private AuthPresenter _authPresenter;

    private Button authButton;
    private EditText passwordEditText;
    private TextView descriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        initializeDI();

        initializeControls();

        configureEvents();

        _authPresenter = new AuthPresenter(this);
    }

    private void initializeDI() {
        ActivityComponent mainActivityComponent = DaggerActivityComponent.builder()
                .contextModule(new ContextModule(this))
                .appComponent(MyApplication.get(this).getApplicationComponent())
                .build();
        mainActivityComponent.injectActivity(this);
    }

    private void initializeControls() {
        authButton = findViewById(R.id.authButton);
        passwordEditText = findViewById(R.id.passwordEditText);
        descriptionTextView = findViewById(R.id.descriptionTextView);
    }

    private void configureEvents() {
        authButton.setOnClickListener(x -> {
            _authPresenter.authenticateUser(passwordEditText.getText().toString());
        });
    }

    @Override
    public void loadSignInView() {
        authButton.setText(R.string.signIn);
        descriptionTextView.setText(R.string.signInDescription);
    }
}
