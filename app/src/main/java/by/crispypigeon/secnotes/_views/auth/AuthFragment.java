package by.crispypigeon.secnotes._views.auth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import by.crispypigeon.secnotes.R;
import by.crispypigeon.secnotes._presenters.auth.AuthPresenter;
import by.crispypigeon.secnotes._views._controls.ErrorImageView;
import by.crispypigeon.secnotes.di.DaggerUtils;
import by.crispypigeon.secnotes.di.activities.ActivityModule;
import by.crispypigeon.secnotes.di.activities.DaggerActivityComponent;
import by.crispypigeon.secnotes.di.context.ContextModule;

public class AuthFragment extends Fragment implements IAuthView {

    private AuthPresenter _authPresenter;

    private Button authButton;
    private EditText passwordEditText;
    private TextView descriptionTextView;
    private ErrorImageView errorImageView;

    private NavController navController;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);


        initializeDI();

        initializeControls();

        configureEvents();

        _authPresenter = new AuthPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_auth, container, false);
    }

    private void initializeDI() {
        DaggerUtils.activityComponent = DaggerActivityComponent.builder()
                .contextModule(new ContextModule(getContext()))
                .activityModule(new ActivityModule(getActivity()))
                .appComponent(DaggerUtils.appComponent)
                .build();
    }

    private void initializeControls() {
        View view = getView();

        authButton = view.findViewById(R.id.authButton);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        descriptionTextView = view.findViewById(R.id.descriptionTextView);
        errorImageView = view.findViewById(R.id.errorImageView);
    }

    private void configureEvents() {
        authButton.setOnClickListener(x -> _authPresenter.authenticateUser(passwordEditText.getText().toString()));
    }

    @Override
    public void loadSignInView() {
        authButton.setText(R.string.signIn);
        descriptionTextView.setText(R.string.signInDescription);
    }

    @Override
    public void showPasswordError() {
        errorImageView.setVisibility(View.VISIBLE);
        errorImageView.showErrorAnimation(getActivity());
    }

    @Override
    public void onSignedInView() {
        navController.navigate(R.id.action_authFragment_to_notesFragment);
    }
}