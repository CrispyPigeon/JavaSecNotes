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
import by.crispypigeon.secnotes._views._controls.dialogs.InformationAlertDialog;
import by.crispypigeon.secnotes._views._controls.dialogs.MessageAlertDialog;

public class AuthFragment extends Fragment implements IAuthView {

    private AuthPresenter authPresenter;

    private Button authButton;
    private Button resetPasswordButton;
    private EditText passwordEditText;
    private TextView descriptionTextView;
    private ErrorImageView errorImageView;

    private NavController navController;

    private InformationAlertDialog informationAlertDialog;
    private MessageAlertDialog resetDataAlertDialog;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        initializeControls();

        configureEvents();

        authPresenter = new AuthPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_auth, container, false);
    }

    private void initializeControls() {
        View view = getView();

        authButton = view.findViewById(R.id.authButton);
        resetPasswordButton = view.findViewById(R.id.resetPasswordButton);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        descriptionTextView = view.findViewById(R.id.descriptionTextView);
        errorImageView = view.findViewById(R.id.errorImageView);

        informationAlertDialog = new InformationAlertDialog(getString(R.string.authWarningTitle),
                getString(R.string.authWarningMessage));
        resetDataAlertDialog = new MessageAlertDialog(getString(R.string.resetAllDataTitle),
                getString(R.string.resetAllDataMessage),
                getString(R.string.positiveAnswer));
    }

    private void configureEvents() {
        authButton.setOnClickListener(x -> {
            authPresenter.authenticateUser(passwordEditText.getText().toString());
        });

        resetPasswordButton.setOnClickListener(x -> {
            resetDataAlertDialog.show(getActivity().getSupportFragmentManager(), "resetDialog");
        });

        resetDataAlertDialog.setNoticeDialogListener(x -> authPresenter.resetData());
    }

    @Override
    public void loadSignInView() {
        authButton.setText(R.string.signIn);
        descriptionTextView.setText(R.string.signInDescription);
    }

    @Override
    public void loadSignUpView() {
        authButton.setText(R.string.signUp);
        descriptionTextView.setText(R.string.signUpDescription);
        resetPasswordButton.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showPasswordError() {
        errorImageView.setVisibility(View.VISIBLE);
        errorImageView.showErrorAnimation(getActivity());
    }

    @Override
    public void onUpdatedSingedInView() {
        informationAlertDialog.show(getActivity().getSupportFragmentManager(), "infoDialog");
        onSignedInView();
    }

    @Override
    public void onSignedInView() {
        navController.navigate(R.id.action_authFragment_to_notesFragment);
    }

    @Override
    public void clearErrorAndPassword() {
        errorImageView.setVisibility(View.INVISIBLE);
        passwordEditText.setText("");
    }
}