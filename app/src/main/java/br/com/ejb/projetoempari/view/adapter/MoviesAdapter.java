package br.com.ejb.projetoempari.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.ejb.projetoempari.R;
import br.com.ejb.projetoempari.model.entity.Movie;
import br.com.ejb.projetoempari.presenter.impl.MoviesPresenter;
import br.com.ejb.projetoempari.util.Constants;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Euler on 06/02/2018.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private List<Movie> movies;
    private Context mContext;
    private MoviesPresenter moviesPresenter;

    public MoviesAdapter(Context mContext, MoviesPresenter moviesPresenter, List<Movie> movies) {
        this.movies = movies;
        this.moviesPresenter = moviesPresenter;
        this.mContext = mContext;
    }

    public void updateList(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Movie movie = movies.get(position);

        Picasso.with(mContext)
                .load(Constants.IMG_URL + movie.getBackdrop_path())
                .into(holder.ivPoster);

        holder.tvTitle.setText(movie.getTitle());
        holder.tvAverage.setText(String.valueOf(movie.getVote_average()));
        holder.tvAverage.setTextColor(mContext.getResources().getColor(movie.getVote_average() >= 7 ? R.color.average_green : movie.getVote_average() <= 4 ? R.color.average_red : R.color.average_yellow));
        holder.tvDatalancamento.setText(movie.getDateVirtual());

        holder.ivPoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moviesPresenter.openDetails(movie.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivPoster)
        ImageView ivPoster;
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvAverage)
        TextView tvAverage;
        @BindView(R.id.tvDatalancamento)
        TextView tvDatalancamento;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
