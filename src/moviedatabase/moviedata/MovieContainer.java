package moviedatabase.moviedata;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class MovieContainer {
     // init constructor vals
    private List movieList;
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
            movieList = (movieCollection.fromJson(file, List.class));
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    public List<Movie> getMovieList() {
        return movieList;
    }
}
