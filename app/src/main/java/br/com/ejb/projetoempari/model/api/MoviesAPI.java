package br.com.ejb.projetoempari.model.api;

import br.com.ejb.projetoempari.util.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Euler on 06/02/2018.
 */

public class MoviesAPI {
    private static MoviesAPI sInstance;
    private IMoviesAPI iMoviesAPI;

    private MoviesAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.iMoviesAPI = retrofit.create(IMoviesAPI.class);

    }

    public static MoviesAPI getInstance() {
        if (sInstance == null) {
            synchronized (MoviesAPI.class) {
                if (sInstance == null) sInstance = new MoviesAPI();
            }
        }
        return sInstance;
    }

    public IMoviesAPI getAPI() {
        return iMoviesAPI;
    }
}
