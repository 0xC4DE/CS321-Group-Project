package main.client;
import moviedatabase.moviedata.Movie;
import moviedatabase.moviedata.MovieContainer;
//import moviedatabase.userdata.User;
import moviedatabase.userdata.UserAccount;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import java.awt.Insets;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

public class WishlistView extends Frame {

    public WishlistView () {
        ArrayList<JButton> buttons = new ArrayList<JButton>();
       // List<ArrayList<Movie>> test = UserAccount.getInstance().getWishlist();
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
        // the buttons will be placed horizontally
        setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));
        setSize(400,400);
        setVisible(true);
    }
    // main method
    public static void main(String args[]){
        MovieContainer cont = MovieContainer.getInstance();
        cont.collectMovies(null);
        WishlistView b=new WishlistView();

    }
}