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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class WishlistView extends Frame {
    private List<ArrayList<Movie>> wishLists;
    private ArrayList<JButton> buttons = new ArrayList<JButton>();


    /**
     * Default Constructor, creates a list of buttons matiching the wishlists pulled in
     */
    public WishlistView () {
        ArrayList<JButton> buttons = new ArrayList<JButton>();
       // List<ArrayList<Movie>> test = UserAccount.getInstance().getWishlist();
        List<Movie> test = MovieContainer.getInstance().getMovieList();

        for (int j = 0;j<5;j++) {

                Movie testMovie = test.get(j);
                buttons.add(j,new JButton(testMovie.getTitle()));
                buttons.get(j).addActionListener(e->
                {

                    SingleMovieView movieShow = new SingleMovieView();
                    movieShow.show(testMovie);
                });

                // adding the buttons so that it can be displayed
                add(buttons.get(j));

        }
        // the buttons will be placed vertically, when we have a nested list, it should display in columns
        setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));
        setSize(400,400);
        setVisible(true);
    }

    /**
     * Takes in a movie object, and its new and old location, and moves the object inside the wishlist matrix accordingly
     * @param movieToMove
     * @param newList
     * @param oldList
     * @param oldListIndex
     */
    private void moveList(Movie movieToMove, int newList, int oldList, int oldListIndex){
        //removing movie from previous list
        wishLists.get(oldList).remove(oldListIndex);
        wishLists.get(newList).add(movieToMove);
        //adds a new button that corrosponds to the moved movie
        buttons.add(new JButton(movieToMove.getTitle()));
        //adds the action that clicking will bring up a single movie view of the movie
        buttons.get(buttons.size()-1).addActionListener(e->{
            SingleMovieView hey = new SingleMovieView();
            hey.show(movieToMove);
        });
        //This should basically remake the whole view with the changed movie
        setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));
        setSize(400,400);
        setVisible(true);
    }

    // main method
    public static void main(String args[]){
        //maybe do a for loop, change the view to do one wishlist at a time
        MovieContainer cont = MovieContainer.getInstance();
        cont.collectMovies(null);
        WishlistView b=new WishlistView();

    }
}