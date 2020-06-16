package by.crispypigeon.secnotes._presenters.main;

import by.crispypigeon.secnotes._model.main.MainModel;
import by.crispypigeon.secnotes._views.main.IMainView;

public class MainPresenter {

    private IMainView _mainView;
    private MainModel _mainModel;

    public MainPresenter(IMainView context) {
        _mainView = context;
        _mainModel = new MainModel();

        checkAccountExists();
    }

    private void checkAccountExists() {
        if (_mainModel.isAccountExists())
            _mainView.loadSignInView();
    }

    public void authenticateUser(String password) {
        if (_mainModel.isAccountExists())
            _mainModel.authenticate(password);
        else
            _mainModel.signUp(password);
    }
}
