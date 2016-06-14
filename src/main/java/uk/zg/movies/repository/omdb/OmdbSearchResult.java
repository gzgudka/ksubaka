package uk.zg.movies.repository.omdb;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class OmdbSearchResult {

	@SerializedName("Search")
	private List<OmdbMovie> movies;
	
	public OmdbSearchResult() {
		// gson uses it
	}

	public OmdbSearchResult(List<OmdbMovie> movies) {
		this.movies = movies;
	}

	public List<OmdbMovie> getMovies() {
		return movies;
	}

	public void setMovies(List<OmdbMovie> movies) {
		this.movies = movies;
	}

}