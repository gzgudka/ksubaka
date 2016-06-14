package uk.zg.movies;

public class Movie {

	private final String title;
	
	private final String director;
	
	private final String year;

	public Movie(String title, String director, String year) {
		this.title = title;
		this.director = director;
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public String getDirector() {
		return director;
	}

	public String getYear() {
		return year;
	}

}