package br.com.ejb.projetoempari.view.impl;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Locale;

import br.com.ejb.projetoempari.R;
import br.com.ejb.projetoempari.model.entity.Movie;
import br.com.ejb.projetoempari.presenter.impl.MoviesPresenter;
import br.com.ejb.projetoempari.util.Constants;
import br.com.ejb.projetoempari.view.IDetailsActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity implements IDetailsActivity, View.OnClickListener {
    @BindView(R.id.ivPosterDetails)
    AppCompatImageView ivPosterDetails;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.tvSinopse)
    AppCompatTextView tvSinopse;
    @BindView(R.id.tvTituloOriginal)
    AppCompatTextView tvTituloOriginal;
    @BindView(R.id.tvLancamento)
    AppCompatTextView tvLancamento;
    @BindView(R.id.tvIdiomaOriginal)
    AppCompatTextView tvIdiomaOriginal;
    @BindView(R.id.tvDuracao)
    AppCompatTextView tvDuracao;
    @BindView(R.id.tvOrcamento)
    AppCompatTextView tvOrcamento;
    @BindView(R.id.tvReceita)
    AppCompatTextView tvReceita;
    @BindView(R.id.tvGeneros)
    AppCompatTextView tvGeneros;
    @BindView(R.id.scroll)
    NestedScrollView scroll;
    @BindView(R.id.fabRate)
    FloatingActionButton fabRate;
    @BindView(R.id.progressBarDetail)
    ProgressBar progressBarDetail;

    MoviesPresenter moviesPresenter;
    long movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        moviesPresenter = new MoviesPresenter(this);
        if (getIntent().getExtras() != null) {
            movieId = getIntent().getExtras().getLong(Constants.EXTRAS.MOVIE_ID);
            moviesPresenter.loadDetailsMovie(movieId);
        }

    }

    @Override
    public void showValuesOnUi(Movie movie) {
        fabRate.setOnClickListener(this);

        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);

        collapsingToolbar.setTitle(movie.getTitle());
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.CollapsedAppBar);

        Picasso.with(this)
                .load(Constants.IMG_URL + movie.getBackdrop_path())
                .into(ivPosterDetails);

        tvSinopse.setText(movie.getOverview());
        tvTituloOriginal.setText(Html.fromHtml(String.format("<b>" + getString(R.string.titulooriginal) + ":</b> %s", movie.getOriginal_title())));
        tvLancamento.setText(Html.fromHtml("<b>" + String.format(getString(R.string.lancamento) + ":</b> %s", movie.getDateVirtual())));
        tvIdiomaOriginal.setText(Html.fromHtml("<b>" + String.format(getString(R.string.idiomaoriginal) + ":</b> %s", movie.getOriginal_language().replace("en", "Inglês").replace("ja","Japones"))));
        tvDuracao.setText(Html.fromHtml("<b>" + String.format(getString(R.string.duracao) + ":</b> %s minutos", movie.getRuntime() + "")));
        tvOrcamento.setText(Html.fromHtml("<b>" + String.format(getString(R.string.orcamento) + ":</b> %s", formatter.format(movie.getBudget()))));
        tvReceita.setText(Html.fromHtml("<b>" + String.format(getString(R.string.receita) + ":</b> %s", formatter.format(movie.getRevenue()) + "")));
        tvGeneros.setText(Html.fromHtml("<b>" + String.format(getString(R.string.generos) + ":</b> %s", movie.getGenresVirtual())));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showToast(int message) {
        Toast.makeText(this, getString(message), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showDialogRate() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_rate, null);
        dialogBuilder.setView(dialogView);

        final RatingBar ratingBar = dialogView.findViewById(R.id.ratingBar);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                moviesPresenter.rateMovie(movieId, v * 2);
            }
        });
    }

    @Override
    public void openProgress() {
        progressBarDetail.setVisibility(View.VISIBLE);
        appBarLayout.setVisibility(View.INVISIBLE);
        scroll.setVisibility(View.INVISIBLE);
        fabRate.setVisibility(View.INVISIBLE);
    }

    @Override
    public void closeProgress() {
        progressBarDetail.setVisibility(View.GONE);
        appBarLayout.setVisibility(View.VISIBLE);
        scroll.setVisibility(View.VISIBLE);
        fabRate.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBack() {
        onBackPressed();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fabRate) {
            showDialogRate();
        }
    }
}
