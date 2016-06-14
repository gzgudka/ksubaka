package uk.zg.movies.repository.omdb;

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

public class OmdbMoviesRepositoryTest {

	@Mock
	private OmdbApiService apiService;
	
	@InjectMocks
	private OmdbMoviesRepository repository;
	
	@BeforeMethod
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldReturn2FirstMoviesWhenApiReturnsThemMore() {
		// given
		String title = "The movie";
		int limit = 2;
		
		OmdbSearchResult searchResult = new OmdbSearchResult(Arrays.asList(
				new OmdbMovie("1"),
				new OmdbMovie("2"),
				new OmdbMovie("3")));
		
		// when
		when(apiService.findMoviesByTitle(eq(title), eq("movie"))).thenReturn(searchResult);
		when(apiService.findMovieById("1")).thenReturn(new OmdbMovie("1", "Movie 1", "", ""));
		when(apiService.findMovieById("2")).thenReturn(new OmdbMovie("2", "Movie 2", "", ""));
		List<Movie> movies = repository.findByTitle(title, limit);
		
		// then
		assertNotNull(movies);
		assertFalse(movies.isEmpty());
		assertEquals(movies.size(), 2);
		assertEquals(movies.get(0).getTitle(), "Movie 1");
		assertEquals(movies.get(1).getTitle(), "Movie 2");
	}
	
	@Test
	public void shouldFindDirectorsForMovies() {
		// given
		String title = "The movie";
		int limit = 2;
		
		OmdbSearchResult searchResult = new OmdbSearchResult(Arrays.asList(
				new OmdbMovie("1"),
				new OmdbMovie("2"),
				new OmdbMovie("3")));
		
		// when
		when(apiService.findMoviesByTitle(eq(title), eq("movie"))).thenReturn(searchResult);
		when(apiService.findMovieById("1")).thenReturn(new OmdbMovie("1", "The movie", "", "John Doe"));
		when(apiService.findMovieById("2")).thenReturn(new OmdbMovie("2", "The movie 2", "", "John Doe II"));
		List<Movie> movies = repository.findByTitle(title, limit);
		
		// then	
		assertEquals(movies.get(0).getDirector(), "John Doe");
		assertEquals(movies.get(1).getDirector(), "John Doe II");
	}
	
}