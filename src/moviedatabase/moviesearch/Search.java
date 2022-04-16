package moviedatabase.moviesearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import moviedatabase.moviedata.Movie;
import moviedatabase.moviedata.MovieContainer;

//TODO:
// tokenize the genre, actors, etc so we search for each
// search using "contains" for the multi-value fields
// change to different search alg
// implement fuzzy searching


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
            if (movie.getGenre().contains(genre)) {
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
