package uk.zg.movies.repository.omdb;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OmdbApiClient {
	
	@GET("/")
	Call<OmdbSearchResult> findMoviesByTitle(@Query("s") String title, @Query("type") String type);
	
	@GET("/")
	Call<OmdbMovie> findMovieById(@Query("i") String id);
	
}