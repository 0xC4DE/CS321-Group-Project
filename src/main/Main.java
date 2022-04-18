package main;

import main.client.Dashboard;
import moviedatabase.moviedata.MovieContainer;
import test.TestConstantMovieContainer;
import test.TestConstantUserAccount;
import test.TestGSON;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String []args) throws Exception {
        MovieContainer cont = MovieContainer.getInstance();
        cont.collectMovies(null);

        Dashboard d = new Dashboard();
    }
}
