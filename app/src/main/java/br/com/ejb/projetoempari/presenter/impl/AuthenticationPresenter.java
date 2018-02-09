package br.com.ejb.projetoempari.presenter.impl;

import android.support.annotation.NonNull;

import br.com.ejb.projetoempari.model.api.MoviesAPI;
import br.com.ejb.projetoempari.model.entity.GuestToken;
import br.com.ejb.projetoempari.presenter.IAuthenticationPresenter;
import br.com.ejb.projetoempari.util.EjbUtil;
import br.com.ejb.projetoempari.view.IMainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Euler on 07/02/2018.
 */

public class AuthenticationPresenter implements IAuthenticationPresenter {

    private MoviesAPI moviesAPI;
    private IMainActivity mIMainActivity;

    public AuthenticationPresenter(IMainActivity mIMainActivity) {
        this.moviesAPI = MoviesAPI.getInstance();
        this.mIMainActivity = mIMainActivity;
    }

    @Override
    public void getGuestToken() {
        Call<GuestToken> request = moviesAPI.getAPI().getGuestToken();

        request.enqueue(new Callback<GuestToken>() {
            @Override
            public void onResponse(@NonNull Call<GuestToken> call, @NonNull Response<GuestToken> response) {
                if (response.isSuccessful()) {
                    GuestToken guestToken = response.body();
                    if (guestToken != null) {
                        EjbUtil.guestToken = guestToken.getGuest_session_id();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<GuestToken> call, @NonNull Throwable t) {

            }
        });
    }
}
