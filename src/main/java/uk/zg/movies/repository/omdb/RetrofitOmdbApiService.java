package uk.zg.movies.repository.omdb;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.zg.movies.repository.ApiServiceUnavailableException;

@Service
public class RetrofitOmdbApiService implements OmdbApiService {

	@Autowired
	private OmdbApiClient apiClient;

	@Override
	public OmdbSearchResult findMoviesByTitle(String title, String type) {
		try {
			return apiClient.findMoviesByTitle(title, type).execute().body();
		} catch (IOException e) {
			throw new ApiServiceUnavailableException();
		}
	}

	@Override
	public OmdbMovie findMovieById(String id) {
		try {
			return apiClient.findMovieById(id).execute().body();
		} catch (IOException e) {
			throw new ApiServiceUnavailableException();
		}
	}

}