package main;

import moviedatabase.moviedata.MovieContainer;

import java.io.IOException;

public class Main {
    public static void main(String []args) throws IOException {
        MovieContainer cont = new MovieContainer();
        if(cont.collectMovies(null)){

        }
    }
}
