package moviedatabase.moviesearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import moviedatabase.moviedata.Movie;
import moviedatabase.moviedata.MovieContainer;

public class Search {
    private final List<Movie> movieList = MovieContainer.getInstance().getMovieList();
    public  List<Movie> searchByName(String name) {
        List<Movie> results = new ArrayList<Movie>();
        for (Movie movie : movieList) {
            if (Objects.equals(movie.getTitle(), name)) {
                results.add(movie);
            }
        }
        return results;
    }
    public  List<Movie> searchByDirector(String director) {
        List<Movie> results = new ArrayList<Movie>();
        for (Movie movie : movieList) {
            if (Objects.equals(movie.getTitle(), director)) {
                results.add(movie);
            }
        }
        return results;
    }
    public  List<Movie> searchByGenre(String genre) {
        List<Movie> results = new ArrayList<Movie>();
        for (Movie movie : movieList) {
            if (Objects.equals(movie.getTitle(), genre)) {
                results.add(movie);
            }
        }
        return results;
    }
    public  List<Movie> searchByActor(String actor) {
        List<Movie> results = new ArrayList<Movie>();
        for (Movie movie : movieList) {
            if (Objects.equals(movie.getTitle(), actor)) {
                results.add(movie);
            }
        }
        return results;
    }
}
