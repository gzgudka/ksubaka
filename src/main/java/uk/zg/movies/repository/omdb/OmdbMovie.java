package uk.zg.movies.repository.omdb;

import com.google.gson.annotations.SerializedName;

public class OmdbMovie {

	@SerializedName("Title")
	private String title;

	@SerializedName("imdbID")
	private String id;

	@SerializedName("Year")
	private String year;

	@SerializedName("Director")
	private String director;
	
	public OmdbMovie() {
		// gson uses it
	}
	
	public OmdbMovie(String id) {
		this.id = id;
	}

	public OmdbMovie(String id, String title, String year, String director) {
		this(id);
		this.title = title;
		this.year = year;
		this.director = director;
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

}