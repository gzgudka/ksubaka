package uk.zg.movies.repository.tmdb;

import com.google.gson.annotations.SerializedName;

public class TmdbMovie {

	@SerializedName("title")
	private String title;

	@SerializedName("id")
	private String id;

	@SerializedName("release_date")
	private String releaseDate;
	
	public TmdbMovie() {
		// gson uses it
	}
	
	public TmdbMovie(String title, String id, String releaseDate) {
		this.title = title;
		this.id = id;
		this.releaseDate = releaseDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

}