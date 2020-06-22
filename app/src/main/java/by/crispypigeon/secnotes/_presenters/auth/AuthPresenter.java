package by.crispypigeon.secnotes._presenters.auth;

import by.crispypigeon.secnotes._model.auth.AuthModel;
import by.crispypigeon.secnotes._views.auth.IAuthView;

public class AuthPresenter {

    private IAuthView _mainView;
    private AuthModel _authModel;

    public AuthPresenter(IAuthView context) {
        _mainView = context;
        _authModel = new AuthModel();

        checkAccountExists();
    }

    private void checkAccountExists() {
        if (_authModel.isAccountExists())
            _mainView.loadSignInView();
    }

    public void authenticateUser(String password) {
        if (_authModel.isAccountExists())
            _authModel.authenticate(password);
        else
            _authModel.signUp(password);
    }
}
