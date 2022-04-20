package views.client;
import moviedatabase.moviedata.Movie;
import moviedatabase.moviedata.MovieContainer;
//import moviedatabase.userdata.User;
import moviedatabase.moviesearch.Search;
import moviedatabase.userdata.UserAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO:
//  add submenus for the add and swap options
//  generate default reviews list
//  sort wishlists with comparable


public class WishlistView extends Frame {
    private List<ArrayList<Movie>> wishLists;
    private ArrayList<JButton> buttons = new ArrayList<>();
    static JPopupMenu popup = new JPopupMenu(); // popup menu for actions
    static JMenu submenu_add = new JMenu("Add");
    static JMenuItem pRemove = new JMenuItem("Remove");
    static JMenu submenu_swap = new JMenu("Swap");
    private String listFn = "none";
    JPanel wishlistPanel = new JPanel();
    //private ActionListener menuListener;

    /**
     * Default Constructor, sets up wishlists from the file loaded wishlists
     */
    public WishlistView() {
        //wishLists = UserAccount.getInstance().getWishlist();
        wishLists = new ArrayList<>();
        // TESTING - Setup Sample Wishlists (this will be the wishlist the user is interacting with)
        // List<ArrayList<Movie>> test = UserAccount.getInstance().getWishlist();
        List<Movie> test = MovieContainer.getInstance().getMovieList();
        //ist<ArrayList<Movie>> theList = new ArrayList<>();
        //sets up our test wishlists, 3 lists of 4 movies
        for(int i = 0; i < 3; i++){
            wishLists.add(new ArrayList<>());
            for(int j = 0; j < 4; j++) {
                wishLists.get(i).add(test.get(i+j));
            }
        }

        //setupPopUp();
        // setup panel & frame to display
        JFrame myFrame = new JFrame();
        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // close window on 'X' always

        if(wishLists.isEmpty()) {
            wishlistPanel.add(new JLabel("No Wishlists"));
        } else {
            wishlistPanel.removeAll();

            // Setup buttons for movies in wishlist
            for (int i = 0; i < wishLists.size(); i++) {
                JPanel listPanel = new JPanel();
                listPanel.add(new JLabel("Wishlist #" + i));

                for (int j = 0; j < wishLists.get(i).size(); j++) {
                    Movie testMovie = wishLists.get(i).get(j);
                    buttons.add(j, new JButton(testMovie.getTitle()));
                    buttons.get(j).addActionListener(e ->
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
        }
        JButton createListButton = new JButton("Create New Wishlist");
        createListButton.addActionListener(e->
        {
            createList();
        });
        wishlistPanel.add(createListButton);
        // the buttons will be placed vertically, when we have a nested list, it should display in columns
        wishlistPanel.setLayout(new BoxLayout(wishlistPanel, BoxLayout.Y_AXIS));
        wishlistPanel.setVisible(true);
    }

    public void setReviewWishList(Map<String, String> reviewTable) { //id, review
        if(wishLists.size() == 0) {
            createList();
        }
        Search search_movies = new Search();
        wishLists.get(0).clear(); // make sure empty
        for(Map.Entry<String,String> movie_id : reviewTable.entrySet()) {
            addMovietoList(0, search_movies.searchByID(movie_id.getKey()));
        }


    }

    public JPanel showWishlists() {
        //setupPopUp();
        // setup panel & frame to display
        JFrame myFrame = new JFrame();
        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // close window on 'X' always

//        // TESTING - Setup Sample Wishlists (this will be the wishlist the user is interacting with)
//        // List<ArrayList<Movie>> test = UserAccount.getInstance().getWishlist();
//        List<Movie> test = MovieContainer.getInstance().getMovieList();
//        List<ArrayList<Movie>> theList = new ArrayList<>();
//        //sets up our test wishlists, 3 lists of 4 movies
//        for(int i = 0; i < 3; i++){
//            theList.add(new ArrayList<>());
//            for(int j = 0; j < 4; j++) {
//                theList.get(i).add(test.get(i+j));
//            }
//        }


        if(wishLists.isEmpty()) {
            wishlistPanel.add(new JLabel("No Wishlists"));
        } else {
            wishlistPanel.removeAll();
            buttons.clear();

            // Setup buttons for movies in wishlist
            for (int i = 0; i < wishLists.size(); i++) {
                JPanel listPanel = new JPanel();
                listPanel.add(new JLabel("Wishlist #" + i));

                for (int j = 0; j < wishLists.get(i).size(); j++) {
                    Movie testMovie = wishLists.get(i).get(j);
                    buttons.add(j, new JButton(testMovie.getTitle()));
                    buttons.get(j).addActionListener(e ->
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
        }
        JButton createListButton = new JButton("Create New Wishlist");
        createListButton.addActionListener(e->
        {
            createList();
        });
        wishlistPanel.add(createListButton);
        // the buttons will be placed vertically, when we have a nested list, it should display in columns
        wishlistPanel.setLayout(new BoxLayout(wishlistPanel, BoxLayout.Y_AXIS));
        wishlistPanel.setVisible(true);
        return wishlistPanel;

    }

    /**
     * sets up the layout of the popup menus :)
     */
    public void setupPopUp(){
        // popup menu setup -- create on right click to ensure popup is interacting with correct Movie
        popup.removeAll();
        // popup menu setup
        popup.add(submenu_add); popup.add(pRemove); popup.add(submenu_swap);
        submenu_add.removeAll();submenu_swap.removeAll();

        for(int i = 1; i < wishLists.size(); i++) {
            submenu_add.add(String.valueOf(i));
            submenu_swap.add(String.valueOf(i));
        }

        popup.add(submenu_add); popup.add(submenu_swap);

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

        submenu_add.addActionListener(menuListener);
        pRemove.addActionListener(menuListener);
        submenu_swap.addActionListener(menuListener);
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
                    // popup menu setup
                    popup.add(submenu_add); popup.add(pRemove); popup.add(submenu_swap);
                    submenu_add.removeAll();submenu_swap.removeAll();

                    for(int i = 1; i < wishLists.size(); i++) {
                        submenu_add.add(String.valueOf(i));
                        submenu_swap.add(String.valueOf(i));
                    }

                    popup.add(submenu_add); popup.add(submenu_swap);

                    // handle the popup menu options
                    ActionListener menuListener = new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                            System.out.println("Popup menu item [" + event.getActionCommand() + "] was selected.");
                            listFn = event.getActionCommand();
                            switch (listFn) {
                                case "Add" -> {
                                    addMovietoList(wishlistIndex, movieObj); // uncomment when wishlist is used
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

                    submenu_add.addActionListener(menuListener);
                    pRemove.addActionListener(menuListener);
                    submenu_swap.addActionListener(menuListener);
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
//        //adds a new button that corresponds to the moved movie
//        buttons.add(new JButton(movieToMove.getTitle()));
//        //adds the action that clicking will bring up a single movie view of the movie
//        buttons.get(buttons.size()-1).addActionListener(e->{
//            SingleMovieView hey = new SingleMovieView();
//            hey.show(movieToMove);
//        });
//        //This should basically remake the whole view with the changed movie
//        setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));
//        setSize(400,400);
//        wishlistPanel.revalidate();
//        setVisible(true);
        showWishlists();
    }

    public void createList() {
        if(wishLists.isEmpty()) {
            wishLists = new ArrayList<>();
        }
        wishLists.add(new ArrayList<Movie>());
        UserAccount.getInstance().SetList(wishLists);
        showWishlists();
    }

    public void deleteList(int indexToRemove) {
        wishLists.remove(indexToRemove);
        UserAccount.getInstance().SetList(wishLists);
        showWishlists();
    }

    public void addMovietoList(int whichList, Movie movieToAdd) {
        wishLists.get(whichList).add(movieToAdd);
        UserAccount.getInstance().SetList(wishLists);
        showWishlists();
    }

    public void removeMovieFromList(int whichList, Movie movieToRemove) {
        wishLists.get(whichList).remove(movieToRemove);
        UserAccount.getInstance().SetList(wishLists);
        showWishlists();
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

