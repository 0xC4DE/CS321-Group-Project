package moviedatabase.moviedata;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public interface MovieCollection {
    // This is going to remain an object that floats around, I don't think it gets populated
    Gson movieCollection = new Gson();

    /*
    MovieCollection contains a collection of movies in a list (possible later a vector list)
     */
    static List<Map<?, ?>> collectMovies(Path filepath) {
        if (filepath == null){
            filepath = Paths.get("data/data.json");
        }
        Reader file = null;
        try {
            file = new FileReader(String.valueOf(filepath));
            List<Map<?,?>> col = movieCollection.fromJson(file, List.class);
            System.out.println(movieCollection);
            return col;
        } catch (FileNotFoundException e) {
            return movieCollection.fromJson("{}", List.class);
        }
    }

}
