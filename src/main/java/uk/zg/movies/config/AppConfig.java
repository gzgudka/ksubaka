package uk.zg.movies.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.zg.movies.repository.omdb.OmdbApiClient;
import uk.zg.movies.repository.tmdb.TmdbApiClient;

@Configuration
@ComponentScan("uk.zg.movies.*")
@PropertySource("classpath:/application.properties")
public class AppConfig {

	@Value("${tmdb.token}")
	private String tmdbToken;
	
	@Bean
	public OmdbApiClient omdbApiClient() {
		return new Retrofit.Builder()
				.baseUrl("http://www.omdbapi.com/")
				.addConverterFactory(GsonConverterFactory.create())
				.build().create(OmdbApiClient.class);
	}

	@Bean
	public TmdbApiClient tmdbApiClient() {
		Interceptor interceptor = new Interceptor() {
			
			@Override
			public okhttp3.Response intercept(Chain chain) throws IOException {
				Request original = chain.request();
				HttpUrl originalHttpUrl = original.url();
				
				HttpUrl url = originalHttpUrl.newBuilder()
						.addQueryParameter("api_key", tmdbToken).build();

				Request.Builder requestBuilder = original.newBuilder().url(url);

				Request request = requestBuilder.build();
				return chain.proceed(request);
			}
		};

		OkHttpClient.Builder builder = new OkHttpClient.Builder();
		builder.interceptors().add(interceptor);
		OkHttpClient client = builder.build();

		return new Retrofit.Builder()
				.baseUrl("https://api.themoviedb.org/3/")
				.addConverterFactory(GsonConverterFactory.create())
				.client(client)
				.build().create(TmdbApiClient.class);
	}

}