package main;

import moviedatabase.moviedata.MovieContainer;
import test.TestConstantMovieContainer;
import test.TestConstantUserAccount;
import test.TestGSON;

import java.io.IOException;

public class Main {
    public static void main(String []args) throws Exception {
        MovieContainer cont = MovieContainer.getInstance();
        cont.collectMovies(null);

        //new TestConstantMovieContainer();
        //new TestConstantMovieContainer();

        //new TestGSON();
        new TestConstantUserAccount();


    }
}
