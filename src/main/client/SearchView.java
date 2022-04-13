package main.client;

import moviedatabase.moviedata.Movie;
import moviedatabase.moviedata.MovieContainer;
import moviedatabase.moviesearch.Search;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SearchView {
    public void SearchBox() {
        List<Movie> foundMovies;
        JFrame myFrame = new JFrame();
        JTextField test = new JTextField(35);
        test.addActionListener(e -> {
            Search searchFor = new Search();
            String text = test.getText();
            searchFor.searchByName(text);

        });
    }
    private void showMovies(List<Movie> moviesToShow){
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        List<Movie> test = MovieContainer.getInstance().getMovieList();

        for (int j = 0;j<5;j++) {

            Movie testMovie = test.get(j);
            buttons.add(j,new JButton(testMovie.getTitle()));
            buttons.get(j).addActionListener(e->
            {
                SingleMovieView hey = new SingleMovieView();
                hey.show(testMovie);
            });

            // adding the buttons so that it can be displayed
            add(buttons.get(j));

        }
        setLayout (new BoxLayout (this, BoxLayout.X_AXIS));
        setSize(400,400);
        setVisible(true);

    }



}
