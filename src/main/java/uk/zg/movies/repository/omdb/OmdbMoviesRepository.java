package uk.zg.movies.repository.omdb;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import uk.zg.movies.Movie;
import uk.zg.movies.MovieApi;
import uk.zg.movies.repository.MoviesRepository;

@Repository
public class OmdbMoviesRepository implements MoviesRepository {

	@Autowired
	private OmdbApiService apiService;

	@Override
	public List<Movie> findByTitle(String title, int limit) {
		return Optional.ofNullable(apiService.findMoviesByTitle(title, "movie"))
				.map(OmdbSearchResult::getMovies)
				.orElseGet(Collections::emptyList)
					.stream()
					.limit(limit)
					.map(OmdbMovie::getId)
					.map(this::findMovieById)
					.filter(Optional::isPresent)
						.map(Optional::get)
						.collect(Collectors.toList());
	}

	@Override
	public boolean supports(MovieApi api) {
		return api == MovieApi.OMDB;
	}
	
	private Optional<Movie> findMovieById(String id) {
		return Optional.ofNullable(apiService.findMovieById(id))
				.filter(movie -> movie.getTitle() != null)
				.map(movie -> new Movie(movie.getTitle(), movie.getDirector(), movie.getYear()));
	}
	
}