package uk.zg.movies.repository.tmdb;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class TmdbSearchResult {
	
	@SerializedName("results")
	private List<TmdbMovie> movies;
	
	public TmdbSearchResult() {
		// gson uses it
	}

	public TmdbSearchResult(List<TmdbMovie> movies) {
		this.movies = movies;
	}

	public List<TmdbMovie> getMovies() {
		return movies;
	}

	public void setMovies(List<TmdbMovie> movies) {
		this.movies = movies;
	}
}