package main;

import moviedatabase.moviedata.MovieContainer;
import test.TestConstantMovieContainer;

import java.io.IOException;

public class Main {
    public static void main(String []args) throws IOException {
        MovieContainer cont = new MovieContainer();
        cont.collectMovies(null);

        // TODO: Make GSON actually eat the movie data instead of dying.
        //System.out.println(cont.getMovieList().get(0).getTitle());


        new TestConstantMovieContainer();
        new TestConstantMovieContainer();


    }
}
