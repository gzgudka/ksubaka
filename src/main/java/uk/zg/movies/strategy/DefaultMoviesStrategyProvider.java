package uk.zg.movies.strategy;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uk.zg.movies.MovieApi;
import uk.zg.movies.repository.MoviesRepository;

@Component
public class DefaultMoviesStrategyProvider implements MoviesStrategyProvider {

	@Autowired
	private Collection<MoviesRepository> moviesRepositories;

	@Override
	public MoviesRepository provideRepository(MovieApi api) {
		return moviesRepositories.stream()
				.filter(repository -> repository.supports(api))
				.findFirst()
				.orElseThrow(ApiServiceNoFoundException::new);
	}

}