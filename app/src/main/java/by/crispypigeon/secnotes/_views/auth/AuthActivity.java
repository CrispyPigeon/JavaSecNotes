package by.crispypigeon.secnotes._views.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import javax.inject.Inject;

import by.crispypigeon.secnotes.MyApplication;
import by.crispypigeon.secnotes.R;
import by.crispypigeon.secnotes._presenters.auth.AuthPresenter;
import by.crispypigeon.secnotes._views._controls.ErrorImageView;
import by.crispypigeon.secnotes.assistances.encryption.HashAssistance;
import by.crispypigeon.secnotes.assistances.storage.SharedPreferencesAssistance;
import by.crispypigeon.secnotes.di.DaggerUtils;
import by.crispypigeon.secnotes.di.activities.ActivityComponent;
import by.crispypigeon.secnotes.di.activities.ActivityModule;
import by.crispypigeon.secnotes.di.activities.DaggerActivityComponent;
import by.crispypigeon.secnotes.di.context.ContextModule;

public class AuthActivity extends AppCompatActivity implements IAuthView {

    private AuthPresenter _authPresenter;

    private Button authButton;
    private EditText passwordEditText;
    private TextView descriptionTextView;
    private ErrorImageView errorImageView;

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

        DaggerUtils.activityComponent = DaggerActivityComponent.builder()
                .contextModule(new ContextModule(this))
                .activityModule(new ActivityModule(this))
                .appComponent(DaggerUtils.appComponent)
                .build();
    }

    private void initializeControls() {
        authButton = findViewById(R.id.authButton);
        passwordEditText = findViewById(R.id.passwordEditText);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        errorImageView = findViewById(R.id.errorImageView);
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

    @Override
    public void showPasswordError() {
        errorImageView.setVisibility(View.VISIBLE);
        errorImageView.showErrorAnimation(this);
    }
}
