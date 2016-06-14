package uk.zg.movies;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;
import uk.zg.movies.config.AppConfig;
import uk.zg.movies.repository.MoviesRepository;
import uk.zg.movies.strategy.MoviesStrategyProvider;

public class MovieTool {

	private final MovieApi api;
	private final String title;

	public MovieTool(MovieApi api, String title) {
		this.api = api;
		this.title = title;
	}

	public static void main(String[] args) {

		ArgumentParser parser = ArgumentParsers.newArgumentParser("MovieTool")
				.defaultHelp(true)
				.description("Simple movie tool, \n example usage: java -jar movies-db-*.jar \"Harry Potter\" -api OMDB");

		parser.addArgument("-api")
				.choices(Stream.of(MovieApi.values()).map(Enum::name).collect(Collectors.toList()))
				.setDefault(MovieApi.OMDB.name())
				.help("Specify API to use");

		parser.addArgument("title")
				.nargs("+")
				.help("Movie title to search for");

		try {
			Namespace ns = parser.parseArgs(args);
			MovieTool movieTool = new MovieTool(MovieApi.valueOf(ns.getString("api")), ns.getString("title"));
			movieTool.run();
		} catch (ArgumentParserException e) {
			parser.handleError(e);
			System.exit(1);
		}

	}

	private void run() {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		((AbstractApplicationContext) context).registerShutdownHook();

		MoviesStrategyProvider strategyProvider = context.getBean(MoviesStrategyProvider.class);
		MoviesRepository repository = strategyProvider.provideRepository(api);
		List<Movie> movies = repository.findByTitle(title, 4);

		printMovies(movies);
	}

	private void printMovies(List<Movie> movies) {
		for (Movie movie : movies) {
			System.out.println(movie.getTitle() + ", " + movie.getYear() + ", " + movie.getDirector());
		}
	}
	
}