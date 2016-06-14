package uk.zg.movies.repository.omdb;

public interface OmdbApiService {

	OmdbSearchResult findMoviesByTitle(String title, String type);

	OmdbMovie findMovieById(String id);

}