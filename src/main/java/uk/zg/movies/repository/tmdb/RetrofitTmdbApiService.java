package uk.zg.movies.repository.tmdb;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.zg.movies.repository.ApiServiceUnavailableException;

@Service
public class RetrofitTmdbApiService implements TmdbApiService {

	@Autowired
	private TmdbApiClient apiClient;
	
	@Override
	public TmdbSearchResult findMoviesByTitle(String title) {
		try {
			return apiClient.findMoviesByTitle(title).execute().body();
		} catch (IOException e) {
			throw new ApiServiceUnavailableException();
		}
	}

	@Override
	public TmdbMovieCredits findMovieCreditsById(String id) {
		try {
			return apiClient.findMovieCreditsById(id).execute().body();
		} catch (IOException e) {
			throw new ApiServiceUnavailableException();
		}
	}

}