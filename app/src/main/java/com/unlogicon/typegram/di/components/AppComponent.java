package com.unlogicon.typegram.di.components;

import android.content.Context;

import com.unlogicon.typegram.TgramApplication;
import com.unlogicon.typegram.di.modules.AppModule;
import com.unlogicon.typegram.di.modules.NetworkModule;
import com.unlogicon.typegram.di.modules.RoomModule;
import com.unlogicon.typegram.di.modules.SharedPreferencesModule;
import com.unlogicon.typegram.interfaces.api.RestApi;
import com.unlogicon.typegram.presenters.activities.ArticleActivityPresenter;
import com.unlogicon.typegram.presenters.activities.LoginPresenter;
import com.unlogicon.typegram.presenters.activities.MainActivityPresenter;
import com.unlogicon.typegram.presenters.activities.RegisterPresenter;
import com.unlogicon.typegram.services.FcmServices;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Nikita Korovkin 11.10.2018.
 */

@Component(modules = {AppModule.class, NetworkModule.class, RoomModule.class, SharedPreferencesModule.class})
@Singleton
public interface AppComponent {

    void inject(TgramApplication application);

    void inject(ArticleActivityPresenter presenter);

    void inject(MainActivityPresenter presenter);

    void inject(FcmServices services);

    void inject(LoginPresenter presenter);

    void inject(RegisterPresenter presenter);

    Context context();

    RestApi restApi();
}
