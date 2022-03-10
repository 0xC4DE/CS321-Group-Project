package movieviewer.dataparser;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class MovieCollection {
    // This is going to remain an object that floats around, I dont think it gets populated
    private Gson movieCollection = new Gson();

    // These are separate because the goal is to make movies a List<Movies>
    private final List<Map<?, ?>> movies = loadMovies(null);

    /*
    MovieCollection contains a collection of movies in a list (possible later a vector list)
     */
    public MovieCollection() throws FileNotFoundException {
    }

    public List<Map<?, ?>> loadMovies(Path filepath) throws FileNotFoundException {
        if (filepath == null){
            filepath = Paths.get("data/data.json");
        }
        Reader file = new FileReader(String.valueOf(filepath));
        List<Map<?,?>> movies = movieCollection.fromJson(file, List.class);
        System.out.println(movies.get(0).get("Title"));
        return movies;
    }

    /*
    This will index the internal list for Movie, unwraps the List<> from List<Map<?, ?>>
    @returns Movie
     */
    public Map<?, ?> get(Integer index){
        return movies.get(index);
    }

}
