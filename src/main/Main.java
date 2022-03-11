package main;

import moviedatabase.moviedata.MovieContainer;
import test.TestConstantMovieContainer;

import java.io.IOException;

public class Main {
    public static void main(String []args) throws IOException {
        new TestConstantMovieContainer();
        new TestConstantMovieContainer();
    }
}
