package br.com.ejb.projetoempari.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import br.com.ejb.projetoempari.R;
import br.com.ejb.projetoempari.model.entity.Movie;
import br.com.ejb.projetoempari.presenter.impl.AuthenticationPresenter;
import br.com.ejb.projetoempari.presenter.impl.MoviesPresenter;
import br.com.ejb.projetoempari.util.Constants;
import br.com.ejb.projetoempari.view.IMainActivity;
import br.com.ejb.projetoempari.view.adapter.MoviesAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, BottomNavigationView.OnNavigationItemSelectedListener, IMainActivity {

    @BindView(R.id.rvMovies)
    RecyclerView rvMovies;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    MoviesPresenter moviesPresenter;
    MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        navigation.setOnNavigationItemSelectedListener(this);

        AuthenticationPresenter authenticationPresenter = new AuthenticationPresenter(this);
        authenticationPresenter.getGuestToken();

        moviesPresenter = new MoviesPresenter(this);
        moviesPresenter.loadPopularMovies();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_popular:
                moviesPresenter.loadPopularMovies();
                return true;
            case R.id.navigation_toprated:
                moviesPresenter.loadTopRatedMovies();
                return true;
            case R.id.navigation_myratings:
                moviesPresenter.loadGuestRatedMovies();
                return true;
        }
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void updateTable(List<Movie> movies) {
        moviesAdapter = new MoviesAdapter(this, moviesPresenter, movies);
        rvMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvMovies.setAdapter(moviesAdapter);
    }

    @Override
    public void showToast(int message) {
        Toast.makeText(this, getString(message), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openDetails(long idMovie) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(Constants.EXTRAS.MOVIE_ID, idMovie);
        startActivity(intent);
    }

    @Override
    public void openProgress() {
        progressBar.setVisibility(View.VISIBLE);
        rvMovies.setVisibility(View.GONE);
    }

    @Override
    public void closeProgress() {
        progressBar.setVisibility(View.GONE);
        rvMovies.setVisibility(View.VISIBLE);
    }

}
