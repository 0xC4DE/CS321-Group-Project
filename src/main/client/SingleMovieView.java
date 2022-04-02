package main.client;
// Java Program to create a popup and display
// it on a parent frame
import moviedatabase.moviedata.Movie;
import moviedatabase.moviedata.MovieContainer;

import javax.swing.*;
import java.util.List;

class SingleMovieView {
    public void show(Movie movieToShow) {
        JFrame jFrame = new JFrame();
        JOptionPane.showMessageDialog(jFrame, "Title: "+movieToShow.getTitle()+"\n"+movieToShow.getDirector()+"\n"+
                movieToShow.getMovieRating()+"\n"+movieToShow.getCountry()+"\n"+movieToShow.getGenre()+"\n"+movieToShow.getLanguage()+"\n"+
                movieToShow.getRuntime()+"\n"+movieToShow.getYear());

    }
    public String findSimilar(Movie base){
        Movie result = new Movie();
        MovieContainer test = MovieContainer.getInstance();
        List<Movie> myMovies = test.getMovieList();
        //call movies by genre
        return result.getTitle();

    }


}


