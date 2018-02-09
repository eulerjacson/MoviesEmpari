package br.com.ejb.projetoempari.model.api;

import br.com.ejb.projetoempari.model.entity.GuestToken;
import br.com.ejb.projetoempari.model.entity.Movie;
import br.com.ejb.projetoempari.model.entity.JsonMovies;
import br.com.ejb.projetoempari.model.entity.RateMovie;
import br.com.ejb.projetoempari.util.Constants;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Euler on 06/02/2018.
 */

public interface IMoviesAPI {

    //authentication
    @GET("authentication/guest_session/new?api_key=" + Constants.MY_KEY)
    Call<GuestToken> getGuestToken();

    //listas
    @GET("movie/popular?api_key=" + Constants.MY_KEY + "&language=" + Constants.LANGUAGE + "&page=1")
    Call<JsonMovies> listPopularMovies();

    @GET("movie/top_rated?api_key=" + Constants.MY_KEY + "&language=" + Constants.LANGUAGE + "&page=1")
    Call<JsonMovies> listTopRatedMovies();

    @GET("movie/now_playing?api_key=" + Constants.MY_KEY + "&language=" + Constants.LANGUAGE + "&page=1")
    Call<JsonMovies> listUpcomingMovies();

    @GET("guest_session/{guest_session_id}/rated/movies?api_key=" + Constants.MY_KEY + "&language=" + Constants.LANGUAGE)
    Call<JsonMovies> listGuestRatedMovies(@Path("guest_session_id") String guest_session_id);

    //detail
    @GET("movie/{movie_id}?api_key=" + Constants.MY_KEY + "&language=" + Constants.LANGUAGE)
    Call<Movie> getDetailMovie(@Path("movie_id") long movie_id);

    //rate
    @FormUrlEncoded
    @POST("movie/{movie_id}/rating?api_key=" + Constants.MY_KEY)
    Call<RateMovie> rateMovie(@Path("movie_id") long movie_id, @Query("guest_session_id") String guest_session_id, @Field("value") double value);
}
