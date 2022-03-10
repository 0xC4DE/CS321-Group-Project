package movieviewer.main;

import movieviewer.dataparser.MovieCollection;

import java.io.IOException;
import java.util.Map;

public class main {
    public static void main(String []args) throws IOException {
        MovieCollection defaultCollection = new MovieCollection();
        Map<?, ?> movie = defaultCollection.get(0);
        System.out.println(movie);
    }
}
