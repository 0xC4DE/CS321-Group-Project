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
        JOptionPane.showMessageDialog(jFrame, "Title: "+movieToShow.getTitle()+"\nDirector: "+movieToShow.getDirector()+"\nRated: "+
        movieToShow.getRated()+"\nCountry:"+movieToShow.getCountry()+"\nGenre: "+movieToShow.getGenre()+"\nLanguage: "+movieToShow.getLanguage()+"\nRuntime: "+
               movieToShow.getRuntime()+"\nYear: "+movieToShow.getYear()+"\n\tSimilar:  "+findSimilar(movieToShow));

    }
    public String findSimilar(Movie base){
        List<Movie> results;
        Search test = new Search();
        String similar=" ";
        results = test.searchByGenre(base.getGenre());
        for (Movie result : results) {
            similar += result.getTitle() + ", ";
        }
        return similar;

    }


}


