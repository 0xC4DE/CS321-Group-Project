package main.client;
import moviedatabase.moviedata.Movie;
import moviedatabase.moviedata.MovieContainer;
//import moviedatabase.userdata.User;
import moviedatabase.userdata.UserAccount;

import javax.naming.event.ObjectChangeListener;
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
//  add submenus for the add and swap options
//  generate default reviews list
//  should singleMovieView have add to wishlist option? with dropdown to select which list to add to during searches


public class WishlistView extends Frame {
    private List<ArrayList<Movie>> wishLists;
    private ArrayList<JButton> buttons = new ArrayList<>();
    static JPopupMenu popup = new JPopupMenu(); // popup menu for actions
    private String listFn = "none";
    //private ActionListener menuListener;



    /**
     * Default Constructor, sets up wishlists from the file loaded wishlists
     */
    public WishlistView(){
        wishLists = UserAccount.getInstance().getWishlist();
    }
    public JPanel showWishlists() {
        //setupPopUp();
        // setup panel & frame to display
        JFrame myFrame = new JFrame();
        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // close window on 'X' always
        JPanel wishlistPanel = new JPanel();
        JButton addList = new JButton("Make a new list");
        addList.addActionListener(e -> {
            createList();
            wishlistPanel.revalidate();
        });
        // TESTING - Setup Sample Wishlists (this will be the wishlist the user is interacting with)
        // List<ArrayList<Movie>> test = UserAccount.getInstance().getWishlist();
        List<Movie> test = MovieContainer.getInstance().getMovieList();
        List<ArrayList<Movie>> theList = new ArrayList<>();
        //sets up our test wishlists, 3 lists of 4 movies
        for(int i = 0; i < 3; i++){
            theList.add(new ArrayList<>());
            for(int j = 0; j < 4; j++) {
                theList.get(i).add(test.get(i+j));
            }
        }


        // Setup buttons for movies in wishlist
        for(int i = 0; i < theList.size(); i++){
            JPanel listPanel = new JPanel();
            listPanel.add(new JLabel("Wishlist #" + i));

            for(int j=0;j<theList.get(i).size();j++){
                Movie testMovie = theList.get(i).get(j);
                buttons.add(j,new JButton(testMovie.getTitle()));
                buttons.get(j).addActionListener(e->
                {
                    SingleMovieView movieShow = new SingleMovieView();
                    movieShow.show(testMovie);
                });

                listPanel.add(buttons.get(j));
                handleMouse(buttons.get(j), i, testMovie);
            }
            setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
            wishlistPanel.add(listPanel);
        }

        // the buttons will be placed vertically, when we have a nested list, it should display in columns
        wishlistPanel.setLayout(new BoxLayout (wishlistPanel, BoxLayout.Y_AXIS));
        wishlistPanel.setVisible(true);
        return wishlistPanel;
    }

    /**
     * sets up the layout of the popup menus :)
     */
    public void setupPopUp(){
        // popup menu setup
        JMenuItem pAdd = new JMenuItem("Add");
        JMenuItem pRemove = new JMenuItem("Remove");
        JMenuItem pSwap = new JMenuItem("Swap");
        popup.add(pAdd); popup.add(pRemove); popup.add(pSwap);

        ActionListener menuListener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Popup menu item [" + event.getActionCommand() + "] was selected.");
                listFn = event.getActionCommand();
                switch (listFn) {
                    case "Add" -> {
                        //addMovietoList(wishlistIndex, movieObj); // uncomment when wishlist is used
                        System.out.println("Adding movie to list");
                    }
                    case "Remove" -> {
                        //removeMovieFromList(wishlistIndex, movieObj);
                        System.out.println("Removing movie from list");
                    }
                    case "Swap" -> {
                        //swapMovieList(wishlistIndex, movieObj);
                        System.out.println("Swapping movie to list");
                    }
                }
            }};

        pAdd.addActionListener(menuListener);
        pRemove.addActionListener(menuListener);
        pSwap.addActionListener(menuListener);
        // end popup menu setup
    }

    /**
     * used to handle what happens when the mouse in clicked on a button
     * @param button button being acted upon
     * @param wishlistIndex ???
     * @param movieObj ???
     */
    public void handleMouse(JButton button, int wishlistIndex, Movie movieObj) {
        // handle the button click event
        button.addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent me) {
            if(me.getButton() == MouseEvent.BUTTON3) { // right click button

                // popup menu setup -- create on right click to ensure popup is interacting with correct Movie
                popup.removeAll();
                JMenuItem pAdd = new JMenuItem("Add");
                JMenuItem pRemove = new JMenuItem("Remove");
                JMenuItem pSwap = new JMenuItem("Swap");
                popup.add(pAdd); popup.add(pRemove); popup.add(pSwap);

                // handle the popup menu options
                ActionListener menuListener = new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        System.out.println("Popup menu item [" + event.getActionCommand() + "] was selected.");
                        listFn = event.getActionCommand();
                        switch (listFn) {
                            case "Add" -> {
                                //addMovietoList(wishlistIndex, movieObj); // uncomment when wishlist is used
                                System.out.println("Adding movie " + movieObj.getTitle() + " to list");
                            }
                            case "Remove" -> {
                                //removeMovieFromList(wishlistIndex, movieObj);
                                System.out.println("Removing " + movieObj.getTitle() + " from list");
                            }
                            case "Swap" -> {
                                //swapMovieList(wishlistIndex, movieObj);
                                System.out.println("Swapping movie " + movieObj.getTitle() + " to list");
                            }
                        }
                    }};

                pAdd.addActionListener(menuListener);
                pRemove.addActionListener(menuListener);
                pSwap.addActionListener(menuListener);
                // end popup menu setup

                // display info and popup menu
                System.out.println("Selected " + button.getText() + " movie in wishlist #" + wishlistIndex);
                System.out.println("Movie object: " + movieObj.getTitle() + " " + movieObj.getCountry());
                popup.show(button, me.getX(), me.getY());
            }
        }});
    }



    /**
     * Takes in a movie object, and its new and old location, and moves the object inside the wishlist matrix accordingly
     * @param movieToMove .
     * @param newList .
     * @param oldList .
     * @param oldListIndex .
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
    wishLists.add(new ArrayList<Movie>());
        UserAccount.getInstance().SetList(wishLists);
    }

    public void deleteList(int indexToRemove) {
    wishLists.remove(indexToRemove);
        UserAccount.getInstance().SetList(wishLists);

    }

    public void addMovietoList(int whichList, Movie movieToAdd) {
        wishLists.get(whichList).add(movieToAdd);
        UserAccount.getInstance().SetList(wishLists);
    }

    public void removeMovieFromList(int whichList, Movie movieToRemove) {
    wishLists.get(whichList).remove(movieToRemove);
        UserAccount.getInstance().SetList(wishLists);

    }


    
    // main method
    public static void main(String args[]){
        //maybe do a for loop, change the view to do one wishlist at a time
        MovieContainer cont = MovieContainer.getInstance();
        cont.collectMovies(null);
        WishlistView b=new WishlistView();
        b.showWishlists();
    }

}

