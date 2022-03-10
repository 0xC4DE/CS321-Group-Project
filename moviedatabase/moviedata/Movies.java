package moviedatabase.moviedata;

import java.util.List;
import java.util.Map;

public class Movies implements MovieCollection{
    public Movies() {
        List<Map<?,?>> movies = MovieCollection.collectMovies(null);
    }
}
