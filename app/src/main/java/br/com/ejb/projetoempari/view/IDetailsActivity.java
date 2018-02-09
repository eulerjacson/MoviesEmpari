package br.com.ejb.projetoempari.view;

import java.util.List;

import br.com.ejb.projetoempari.model.entity.Movie;

/**
 * Created by Euler on 06/02/2018.
 */

public interface IDetailsActivity {
    void showValuesOnUi(Movie movie);
    void showToast(int message);
    void showToast(String message);
    void openProgress();
    void closeProgress();
    void onBack();
}
