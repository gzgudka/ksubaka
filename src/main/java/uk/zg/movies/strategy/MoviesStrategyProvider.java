package uk.zg.movies.strategy;

import uk.zg.movies.MovieApi;
import uk.zg.movies.repository.MoviesRepository;

public interface MoviesStrategyProvider {

	MoviesRepository provideRepository(MovieApi api);

}