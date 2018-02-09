package br.com.ejb.projetoempari.presenter;

/**
 * Created by Euler on 06/02/2018.
 */

public interface IMoviesPresenter {
    void loadPopularMovies();
    void loadTopRatedMovies();
    void loadUpcomingMovies();
    void loadGuestRatedMovies();
    void loadDetailsMovie(long movieId);
    void rateMovie(long movieId, double rate);
    void openDetails(long idMovie);
    void searchMovies();
}
