package com.unlogicon.typegram.interfaces.activities;

import com.arellomobile.mvp.MvpView;

public interface RegisterView extends MvpView {

    void setSingUpEnabled(boolean enabled);

    void startMainActivity();

    void startLoginActivity();

    void showSnackbar(String text);
}
