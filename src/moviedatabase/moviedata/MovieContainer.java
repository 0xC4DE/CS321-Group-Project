package moviedatabase.moviedata;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MovieContainer {
    private List<Movie> movieList;
    private Movie[] tempMovie;
    private Gson movieCollection;

    // singleton instance of the movie list
    private static MovieContainer instance;

    // private singleton constructor
    private MovieContainer() {
        List<Movie> movieList = null;
        movieCollection = new Gson();
    }

    // public getInstance
    public static MovieContainer getInstance(){
        if(instance == null) {
            instance = new MovieContainer();
        }
        return instance;
    }

    /*
    MovieCollection contains a collection of movies in a list (possible later a vector list)
     */
    public boolean collectMovies(Path filepath) {
        if (filepath == null) {
            filepath = Paths.get("data/data.json");
        };
        try {
            Reader file = new FileReader(String.valueOf(filepath));
            //movieList = (movieCollection.fromJson(file, List.class));
            //String test = String.valueOf(movieCollection.fromJson(file, List.class));
            // BAD movieList = movieCollection.fromJson(file, Movie[].class);
            Movie[] tempMovie = movieCollection.fromJson(file, Movie[].class);
            System.out.println("Temp: " + tempMovie);
            movieList = Arrays.asList(tempMovie);
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    public List<Movie> getMovieList() {
        return movieList;
    }
}
