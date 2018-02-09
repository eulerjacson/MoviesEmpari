package br.com.ejb.projetoempari.view;

import java.util.List;

import br.com.ejb.projetoempari.model.entity.Movie;
import br.com.ejb.projetoempari.model.entity.PopularMovies;

/**
 * Created by Euler on 06/02/2018.
 */

public interface IMainActivity {
    void updateTable(List<Movie> movies);
    void showToast(int message);
    void showToast(String message);
    void openDetails(long idMovie);
    void openProgress();
    void closeProgress();
}
