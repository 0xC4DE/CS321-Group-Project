package main.client;
import moviedatabase.moviedata.Movie;
import moviedatabase.moviedata.MovieContainer;
//import moviedatabase.userdata.User;
import moviedatabase.userdata.UserAccount;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

import java.awt.Insets;
import java.awt.Dimension;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

// TODO:
//
public class WishlistView extends Frame {
    private List<ArrayList<Movie>> wishLists;
    private ArrayList<JButton> buttons = new ArrayList<JButton>();


    /**
     * Default Constructor, creates a list of buttons matching the wishlists pulled in
     */
    public WishlistView () {
        JPanel WishLists = new JPanel();
        ArrayList<JButton> buttons = new ArrayList<JButton>();
       // List<ArrayList<Movie>> test = UserAccount.getInstance().getWishlist();
        List<Movie> test = MovieContainer.getInstance().getMovieList();
        ArrayList<ArrayList<Movie>> practiceList = new ArrayList<ArrayList<Movie>>();
        //sets up our test wishlists, 3 lists of 4 movies
        for(int i=0;i<3;i++){
            practiceList.add(new ArrayList<Movie>());
            for(int j = 0;j<4;j++) {
                practiceList.get(i).add(test.get(i+j));
            }
        }

        for(int i=0;i<practiceList.size();i++){
            JPanel listPanel = new JPanel();
            listPanel.add(new JLabel("Wishlist #" + i));
            for(int j=0;j<practiceList.get(i).size();j++){
                Movie testMovie = test.get(j);
                buttons.add(j,new JButton(testMovie.getTitle()));
                buttons.get(j).addActionListener(e->
                {
                    SingleMovieView movieShow = new SingleMovieView();
                    movieShow.show(testMovie);
                });
                listPanel.add(buttons.get(j));
            }
            setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
            WishLists.add(listPanel);
        }
        // the buttons will be placed vertically, when we have a nested list, it should display in columns
        setLayout (new BoxLayout (WishLists, BoxLayout.Y_AXIS));
        JFrame myFrame = new JFrame();
        myFrame.add(WishLists);
        myFrame.setVisible(true);
        myFrame.setSize(400, 400);

        // TODO: delete this line
        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // close window on 'X' always

        // capture mouse click
        myFrame.addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent me) { }
            public void mouseReleased(MouseEvent me) { }
            public void mouseEntered(MouseEvent me) { }
            public void mouseExited(MouseEvent me) { }
            public void mouseClicked(MouseEvent me) {
//                if(me.getButton() == MouseEvent.BUTTON1) {
//                    label.setText("Left Click!");
//                }
//                if(me.getButton() == MouseEvent.BUTTON2) {
//                    label.setText("Middle Click!");
//                }
                if(me.getButton() == MouseEvent.BUTTON3) {
                    //label.setText("Right Click!");
                    System.out.println("right");

                }
            }
        });

    }

    /**
     * Takes in a movie object, and its new and old location, and moves the object inside the wishlist matrix accordingly
     * @param movieToMove
     * @param newList
     * @param oldList
     * @param oldListIndex
     */
    public void swapMovieList (Movie movieToMove, int newList, int oldList, int oldListIndex){
        //removing movie from previous list
        wishLists.get(oldList).remove(oldListIndex);
        wishLists.get(newList).add(movieToMove);
        //adds a new button that corresponds to the moved movie
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

    public void createList() {

    }

    public void deleteList() {

    }

    public void addMovietoList() {

    }

    public void removeMovieFromList() {

    }


    
    // main method
    public static void main(String args[]){
        //maybe do a for loop, change the view to do one wishlist at a time
        MovieContainer cont = MovieContainer.getInstance();
        cont.collectMovies(null);
        WishlistView b=new WishlistView();

    }
}