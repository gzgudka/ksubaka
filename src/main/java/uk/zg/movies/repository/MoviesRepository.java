package uk.zg.movies.repository;

import java.util.List;

import uk.zg.movies.Movie;
import uk.zg.movies.MovieApi;

public interface MoviesRepository {

	boolean supports(MovieApi api);

	List<Movie> findByTitle(String title, int limit);

}