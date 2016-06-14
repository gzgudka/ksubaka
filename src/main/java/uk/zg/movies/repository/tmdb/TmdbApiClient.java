package uk.zg.movies.repository.tmdb;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TmdbApiClient {

	@GET("search/movie")
	Call<TmdbSearchResult> findMoviesByTitle(@Query("query") String title);

	@GET("movie/{id}/credits")
	Call<TmdbMovieCredits> findMovieCreditsById(@Path("id") String id);

}