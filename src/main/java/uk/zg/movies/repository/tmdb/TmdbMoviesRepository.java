package uk.zg.movies.repository.tmdb;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import uk.zg.movies.Movie;
import uk.zg.movies.MovieApi;
import uk.zg.movies.repository.MoviesRepository;

@Repository
public class TmdbMoviesRepository implements MoviesRepository {

	@Autowired
	private TmdbApiService apiService;
	
	@Override
	public List<Movie> findByTitle(String title, int limit) {	
		return Optional.ofNullable(apiService.findMoviesByTitle(title))
				.map(TmdbSearchResult::getMovies)
				.orElseGet(Collections::emptyList)
				.stream()
					.limit(limit)
					.map(this::buildMovie)
					.collect(Collectors.toList());
	}

	@Override
	public boolean supports(MovieApi api) {
		return api == MovieApi.TMDB;
	}
	
	private Optional<TmdbCrewMember> findDirectorByMovieId(String id) {
		return Optional.ofNullable(apiService.findMovieCreditsById(id))
				 .map(TmdbMovieCredits::getCrewMembers)
				 .orElseGet(Collections::emptyList)
				 .stream()
				 .filter(member -> "Director".equals(member.getJob()))
				 .findFirst();
	}
	
	private Movie buildMovie(TmdbMovie movie) {
		String year = Optional.ofNullable(movie.getReleaseDate())
				.filter(StringUtils::isNotBlank)
				.map(TmdbMoviesRepository::retriveYearFromDate)
				.orElse("xxxx");
		String director = findDirectorByMovieId(movie.getId())
				.map(TmdbCrewMember::getName)
				.orElse("xxxx");
		return new Movie(movie.getTitle(), director, year);
	}
	
	private static String retriveYearFromDate(String date) {
		LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
		return Integer.toString(localDate.getYear());
	}
	
}