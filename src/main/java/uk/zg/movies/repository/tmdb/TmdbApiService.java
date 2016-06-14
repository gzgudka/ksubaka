package uk.zg.movies.repository.tmdb;

public interface TmdbApiService {

	TmdbSearchResult findMoviesByTitle(String title);

	TmdbMovieCredits findMovieCreditsById(String id);

}