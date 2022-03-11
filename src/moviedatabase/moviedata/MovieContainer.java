package moviedatabase.moviedata;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MovieContainer {
    //thinking if this is a container class it will make more sense, an interface cant
    // have member variables, so if it's just a class that returns instences of itself
    // that'll get the job done better

    private Gson movieCollection = new Gson();
    private ArrayList<Movie> movieList;
    private static MovieContainer instance = null;

    private void MovieCollection(){
        instance = this;
    }

    public static MovieContainer getInstance(){
        if(instance == null ){
            instance = new MovieContainer(); {
            }
        }
        return instance;
    }


    /*
    MovieCollection contains a collection of movies in a list (possible later a vector list)
     */
    // my idea for this is to collect the movies in the startup, after
    // the location of the data is specified
    public boolean collectMovies(Path filepath) {
        if (filepath == null) {
            filepath = Paths.get("data/data.json");
        };
        try {
            Reader file = new FileReader(String.valueOf(filepath));
            allMovies = movieList.fromJson(file, Movie.class);
            return true;
        } catch (FileNotFoundException e) {
            return false;
        };
    };
}
