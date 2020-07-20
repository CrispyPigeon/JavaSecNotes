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
    }

    public void authenticateUser(String password) {
        if (authModel.isAccountExists())
            signIn(password);
        else
            authModel.signUp(password);
    }

    private void signIn(String password) {
        if (authModel.signIn(password)) {
            authView.onSignedInView();
        } else {
            authView.showPasswordError();
        }
    }
}
