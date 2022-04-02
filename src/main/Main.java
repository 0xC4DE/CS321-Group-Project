package main;

import moviedatabase.moviedata.Movie;
import moviedatabase.moviedata.MovieContainer;
import test.TestConstantMovieContainer;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String []args) throws IOException {
        MovieContainer cont = MovieContainer.getInstance();
        cont.collectMovies(null);

        // TODO: Make GSON actually eat the movie data instead of dying.
        //System.out.println("Stream: " + cont.getMovieList().stream().findFirst());//.get(0).getTitle());

        List<Movie> test = cont.getMovieList();
        Movie tempList = cont.getMovieList().get(0);
        System.out.println("Movie: " + test);
        System.out.println("Title: " + tempList.getTitle());

        //new TestConstantMovieContainer();
        //new TestConstantMovieContainer();



    }
}
