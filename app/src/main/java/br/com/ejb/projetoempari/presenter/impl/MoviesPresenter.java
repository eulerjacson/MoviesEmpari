package br.com.ejb.projetoempari.presenter.impl;

import android.support.annotation.NonNull;

import br.com.ejb.projetoempari.R;
import br.com.ejb.projetoempari.model.api.MoviesAPI;
import br.com.ejb.projetoempari.model.entity.Movie;
import br.com.ejb.projetoempari.model.entity.PopularMovies;
import br.com.ejb.projetoempari.model.entity.RateMovie;
import br.com.ejb.projetoempari.presenter.IMoviesPresenter;
import br.com.ejb.projetoempari.util.EjbUtil;
import br.com.ejb.projetoempari.view.IDetailsActivity;
import br.com.ejb.projetoempari.view.IMainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Euler on 06/02/2018.
 */

public class MoviesPresenter implements IMoviesPresenter {

    private MoviesAPI moviesAPI;
    private IMainActivity mIMainActivity;
    private IDetailsActivity mIDetailsActivity;

    public MoviesPresenter(IMainActivity mIMainActivity) {
        this.moviesAPI = MoviesAPI.getInstance();
        this.mIMainActivity = mIMainActivity;
    }

    public MoviesPresenter(IDetailsActivity mIDetailsActivity) {
        this.moviesAPI = MoviesAPI.getInstance();
        this.mIDetailsActivity = mIDetailsActivity;
    }

    @Override
    public void loadPopularMovies() {
        mIMainActivity.openProgress();
        Call<PopularMovies> request = moviesAPI.getAPI().listPopularMovies();

        request.enqueue(new Callback<PopularMovies>() {
            @Override
            public void onResponse(@NonNull Call<PopularMovies> call, @NonNull Response<PopularMovies> response) {
                mIMainActivity.closeProgress();
                if (response.isSuccessful()) {
                    PopularMovies popularMovies = response.body();
                    if (popularMovies != null) {
                        mIMainActivity.updateTable(popularMovies.getResults());
                    }
                } else {
                    mIMainActivity.showToast(R.string.error_request);
                }
            }

            @Override
            public void onFailure(@NonNull Call<PopularMovies> call, @NonNull Throwable t) {
                mIMainActivity.closeProgress();
                mIMainActivity.showToast(R.string.error_request);
            }
        });

    }

    @Override
    public void loadTopRatedMovies() {
        mIMainActivity.openProgress();
        Call<PopularMovies> request = moviesAPI.getAPI().listTopRatedMovies();

        request.enqueue(new Callback<PopularMovies>() {
            @Override
            public void onResponse(@NonNull Call<PopularMovies> call, @NonNull Response<PopularMovies> response) {
                mIMainActivity.closeProgress();
                if (response.isSuccessful()) {
                    PopularMovies popularMovies = response.body();
                    if (popularMovies != null) {
                        mIMainActivity.updateTable(popularMovies.getResults());
                    }
                } else {
                    mIMainActivity.showToast(R.string.error_request);
                }
            }

            @Override
            public void onFailure(@NonNull Call<PopularMovies> call, @NonNull Throwable t) {
                mIMainActivity.closeProgress();
                mIMainActivity.showToast(R.string.error_request);
            }
        });

    }

    @Override
    public void loadUpcomingMovies() {
        mIMainActivity.openProgress();
        Call<PopularMovies> request = moviesAPI.getAPI().listUpcomingMovies();

        request.enqueue(new Callback<PopularMovies>() {
            @Override
            public void onResponse(@NonNull Call<PopularMovies> call, @NonNull Response<PopularMovies> response) {
                mIMainActivity.closeProgress();
                if (response.isSuccessful()) {
                    PopularMovies popularMovies = response.body();
                    if (popularMovies != null) {
                        mIMainActivity.updateTable(popularMovies.getResults());
                    }
                } else {
                    mIMainActivity.showToast(R.string.error_request);
                }
            }

            @Override
            public void onFailure(@NonNull Call<PopularMovies> call, @NonNull Throwable t) {
                mIMainActivity.closeProgress();
                mIMainActivity.showToast(R.string.error_request);
            }
        });

    }

    @Override
    public void loadGuestRatedMovies() {
        mIMainActivity.openProgress();
        Call<PopularMovies> request = moviesAPI.getAPI().listGuestRatedMovies(EjbUtil.guestToken);

        request.enqueue(new Callback<PopularMovies>() {
            @Override
            public void onResponse(@NonNull Call<PopularMovies> call, @NonNull Response<PopularMovies> response) {
                mIMainActivity.closeProgress();
                if (response.isSuccessful()) {
                    PopularMovies popularMovies = response.body();
                    if (popularMovies != null) {
                        mIMainActivity.updateTable(popularMovies.getResults());
                    }
                } else {
                    mIMainActivity.showToast(R.string.error_request);
                }
            }

            @Override
            public void onFailure(@NonNull Call<PopularMovies> call, @NonNull Throwable t) {
                mIMainActivity.closeProgress();
                mIMainActivity.showToast(R.string.error_request);
            }
        });

    }

    @Override
    public void loadDetailsMovie(long movieId) {
        mIDetailsActivity.openProgress();
        Call<Movie> request = moviesAPI.getAPI().getDetailMovie(movieId);

        request.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(@NonNull Call<Movie> call, @NonNull Response<Movie> response) {
                if (response.isSuccessful()) {
                    Movie movie = response.body();
                    if (movie != null) {
                        mIDetailsActivity.closeProgress();
                        mIDetailsActivity.showValuesOnUi(movie);
                    }
                } else {
                    mIDetailsActivity.showToast(R.string.error_request);
                    mIDetailsActivity.onBack();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Movie> call, @NonNull Throwable t) {
                mIDetailsActivity.showToast(R.string.error_request);
                mIDetailsActivity.onBack();
            }
        });

    }

    @Override
    public void rateMovie(long movieId, double rate) {
        Call<RateMovie> request = moviesAPI.getAPI().rateMovie(movieId, EjbUtil.guestToken, rate);

        request.enqueue(new Callback<RateMovie>() {
            @Override
            public void onResponse(@NonNull Call<RateMovie> call, @NonNull Response<RateMovie> response) {
                if (response.isSuccessful()) {
                    RateMovie rateMovie = response.body();
                    if (rateMovie != null) {
                        mIDetailsActivity.showToast(rateMovie.getStatus_message().contains("updated") ? R.string.avaliacaoatualizadasucesso : R.string.avaliacaofeitasucesso);
                    }
                } else {
                    mIDetailsActivity.showToast(R.string.error_request);
                }
            }

            @Override
            public void onFailure(@NonNull Call<RateMovie> call, @NonNull Throwable t) {
                mIDetailsActivity.showToast(R.string.error_request);
            }
        });
    }

    @Override
    public void openDetails(long idMovie) {
        mIMainActivity.openDetails(idMovie);
    }

    @Override
    public void searchMovies() {

    }
}
