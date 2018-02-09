package br.com.ejb.projetoempari.model.entity;

import java.util.ArrayList;

/**
 * Created by Euler on 06/02/2018.
 */

public class Movie {
    private int vote_count;
    private long id;
    private boolean video;
    private double vote_average;
    private String title;
    private double popularity;
    private String poster_path;
    private String original_language;
    private String original_title;
    private String backdrop_path;
    private boolean adult;
    private String overview;
    private String release_date;
    //details
    private double budget;
    private double revenue;
    private int runtime;
    private ArrayList<Genres> genres;

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public ArrayList<Genres> getGenres() {
        return genres;
    }

    public String getGenresVirtual() {
        StringBuilder sb = new StringBuilder();
        for (Genres genre : genres) {
            sb.append(genre.getName()).append(", ");
        }
        return sb.toString().substring(0, sb.toString().length() - 2);
    }

    public void setGenres(ArrayList<Genres> genres) {
        this.genres = genres;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getDateVirtual() {
        String[] splitDate = release_date.split("-");

        String[] meses = {"0", "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

        return splitDate[2] + " de " + meses[Integer.valueOf(splitDate[1])] + " de " + splitDate[0];
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
}
