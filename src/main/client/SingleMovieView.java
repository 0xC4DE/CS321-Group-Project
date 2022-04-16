package main.client;
// Java Program to create a popup and display
// it on a parent frame
import moviedatabase.moviedata.Movie;
import moviedatabase.moviedata.MovieContainer;
import moviedatabase.moviesearch.Search;

import javax.swing.*;
import java.util.List;

class SingleMovieView {
    public void show(Movie movieToShow) {
        JFrame jFrame = new JFrame();
        JOptionPane.showMessageDialog(jFrame, "Title: "+movieToShow.getTitle()+"\n"+movieToShow.getDirector()+"\n"+
        movieToShow.getRated()+"\n"+movieToShow.getCountry()+"\n"+movieToShow.getGenre()+"\n"+movieToShow.getLanguage()+"\n"+
               movieToShow.getRuntime()+"\n"+movieToShow.getYear()+"\n\tSimilar:  "+findSimilar(movieToShow));

    }
    public String findSimilar(Movie base){
        List<Movie> results;
        Search test = new Search();
        results = test.searchByGenre(base.getGenre());
        return results.get(0).getTitle();

    }


}


