package by.crispypigeon.secnotes._views.auth;

public interface IAuthView {

    void loadSignInView();

    void showPasswordError();

    void onSignedInView();
}