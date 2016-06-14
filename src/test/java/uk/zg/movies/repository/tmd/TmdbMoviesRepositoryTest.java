package uk.zg.movies.repository.tmd;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import uk.zg.movies.Movie;
import uk.zg.movies.repository.tmdb.TmdbApiService;
import uk.zg.movies.repository.tmdb.TmdbCrewMember;
import uk.zg.movies.repository.tmdb.TmdbMovie;
import uk.zg.movies.repository.tmdb.TmdbMovieCredits;
import uk.zg.movies.repository.tmdb.TmdbMoviesRepository;
import uk.zg.movies.repository.tmdb.TmdbSearchResult;

public class TmdbMoviesRepositoryTest {

	@Mock
	private TmdbApiService apiService;
	
	@InjectMocks
	private TmdbMoviesRepository repository;
	
	@BeforeMethod
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldReturnTwoFirstMoviesWhenApiReturnsThemMore() {
		// given
		String title = "The movie";
		int limit = 2;
		
		TmdbSearchResult searchResult = new TmdbSearchResult(Arrays.asList(
				new TmdbMovie("Movie 1", "1", "2016-10-10"),
				new TmdbMovie("Movie 2", "2", "2016-10-10"),
				new TmdbMovie("Movie 3", "3", "2016-10-10")));		
		
		// when
		when(apiService.findMoviesByTitle(eq(title))).thenReturn(searchResult);
		List<Movie> movies = repository.findByTitle(title, limit);
		
		// then
		assertNotNull(movies);
		assertFalse(movies.isEmpty());
		assertEquals(movies.size(), 2);
		assertEquals(movies.get(0).getTitle(), "Movie 1");
		assertEquals(movies.get(1).getTitle(), "Movie 2");
	}
	
	@Test
	public void shouldCropOnlyYearFromReleseDate() {
		// given
		String title = "The movie";
		int limit = 2;
		
		TmdbSearchResult searchResult = new TmdbSearchResult(Arrays.asList(
				new TmdbMovie("Movie 1", "1", ""),
				new TmdbMovie("Movie 1", "1", "2016-10-10")));		
		
		// when
		when(apiService.findMoviesByTitle(eq(title))).thenReturn(searchResult);
		List<Movie> movies = repository.findByTitle(title, limit);
		
		// then
		assertEquals(movies.get(0).getYear(), "xxxx");
		assertEquals(movies.get(1).getYear(), "2016");
	}
	
	@Test
	public void shouldFindDirectorsForMovies() {
		// given
		String title = "The movie";
		int limit = 2;
		
		TmdbSearchResult searchResult = new TmdbSearchResult(Arrays.asList(
				new TmdbMovie("Movie 1", "1", "2016-10-10"),
				new TmdbMovie("Movie 2", "2", "2016-10-10")));		
		
		TmdbMovieCredits firstMovieCredits = new TmdbMovieCredits(Arrays.asList(new TmdbCrewMember("", "Director", "John Doe")));
		TmdbMovieCredits secondMovieCredits = new TmdbMovieCredits(Arrays.asList(new TmdbCrewMember("", "Director", "John Doe II")));

		// when
		when(apiService.findMoviesByTitle(eq(title))).thenReturn(searchResult);
		when(apiService.findMovieCreditsById(eq("1"))).thenReturn(firstMovieCredits);
		when(apiService.findMovieCreditsById(eq("2"))).thenReturn(secondMovieCredits);
		List<Movie> movies = repository.findByTitle(title, limit);
		// then
		assertEquals(movies.get(0).getDirector(), "John Doe");
		assertEquals(movies.get(1).getDirector(), "John Doe II");
	}
	
}