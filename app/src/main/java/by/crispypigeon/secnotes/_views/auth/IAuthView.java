package by.crispypigeon.secnotes._views.auth;

public interface IAuthView {

    void loadSignInView();

    void loadSignUpView();

    void showPasswordError();

    void onUpdatedSingedInView();

    void onSignedInView();

    void clearErrorAndPassword();
}