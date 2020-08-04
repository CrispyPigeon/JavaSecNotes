package by.crispypigeon.secnotes._presenters.auth;

import by.crispypigeon.secnotes._models.auth.AuthModel;
import by.crispypigeon.secnotes._views.auth.IAuthView;

public class AuthPresenter {

    private IAuthView authView;
    private AuthModel authModel;

    public AuthPresenter(IAuthView view) {
        authView = view;
        authModel = new AuthModel();

        checkAccountExists();
    }

    private void checkAccountExists() {
        if (authModel.isAccountExists())
            authView.loadSignInView();
        else
            authView.loadSignUpView();
    }

    public void authenticateUser(String password) {
        if (!authModel.validateString(password)) {
            authView.showPasswordError();
            return;
        }

        if (authModel.isAccountExists())
            signIn(password);
        else
            signUp(password);
    }

    private void signUp(String password) {
        authModel.signUp(password);
        authView.onUpdatedSingedInView();
    }

    private void signIn(String password) {
        if (authModel.signIn(password)) {
            authView.onSignedInView();
        } else {
            authView.showPasswordError();
        }
    }

    public void resetData() {
        authModel.resetPassword();
        authModel.resetNotes();
        checkAccountExists();
        authView.clearErrorAndPassword();
    }
}
