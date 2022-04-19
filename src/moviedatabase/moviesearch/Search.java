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
// ^^^ no <3 ^^^
// implement fuzzy searching

/**
 * This is the class used to search the MovieList by whichever parameter is needed
 */
public class Search {
    private final List<Movie> movieList = MovieContainer.getInstance().getMovieList();

    /**
     * takes in a string name and returns a list of all matches
     * @param name is a string
     * @return returns a list of results
     */
    public  List<Movie> searchByName(String name) {
        List<Movie> results = new ArrayList<>();
        for (Movie movie : movieList) {
            if (movie.getTitle().contains(name)) {
                results.add(movie);
            }
        }
        return results;
    }

    /**
     * see above
     * @param director string
     * @return list
     */
    public  List<Movie> searchByDirector(String director) {
        List<Movie> results = new ArrayList<>();
        for (Movie movie : movieList) {
            if (movie.getDirector().contains(director)) {
                results.add(movie);
            }
        }
        return results;
    }

    /**
     * again, see above
     * @param genre string
     * @return list
     */
    public  List<Movie> searchByGenre(String genre) {
        List<Movie> results = new ArrayList<>();
        for (Movie movie : movieList) {
            if (movie.getGenre().contains(genre)) {
                results.add(movie);
            }
        }
        return results;
    }

    /**
     * All these searches work the same, why are you even reading this?
     * @param actor string
     * @return list
     */
    public  List<Movie> searchByActor(String actor) {
        List<Movie> results = new ArrayList<>();
        for (Movie movie : movieList) {
            if (movie.getActors().contains(actor)) {
                results.add(movie);
            }
        }
        return results;
    }

    public List<Movie> searchByID(String ID) {
        List<Movie> results = new ArrayList<>();
        for (Movie movie : movieList) {
            if (movie.getImdbID().contains(ID)) {
                results.add(movie);
            }
        }
        return results;
    }
}
