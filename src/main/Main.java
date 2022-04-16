package main;

import main.client.Dashboard;
import moviedatabase.moviedata.MovieContainer;
import test.TestConstantMovieContainer;

import java.io.IOException;

public class Main {
    public static void main(String []args) throws IOException {
        MovieContainer cont = MovieContainer.getInstance();
        cont.collectMovies(null);

        // TODO: Make GSON actually eat the movie data instead of dying.
        //System.out.println(cont.getMovieList().get(0).getTitle());
        Dashboard d = new Dashboard();

        new TestConstantMovieContainer();
        new TestConstantMovieContainer();



    }
}
