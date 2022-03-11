package main;

import moviedatabase.moviedata.MovieContainer;

import java.io.IOException;

public class Main {
    public static void main(String []args) throws IOException {
        MovieContainer cont = new MovieContainer();
        MovieContainer.setInstance(cont);
        if(cont.collectMovies(null)){
            System.out.println(MovieContainer.getInstance().getMovieList());
            System.out.println(cont.getMovieList());
            if (MovieContainer.getInstance().getMovieList() == cont.getMovieList()){
                System.out.println("foo");
            } else {
                System.out.println("bar");
            }
        }
    }
}
