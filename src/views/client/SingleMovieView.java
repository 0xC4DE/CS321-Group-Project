package views.client;
// Java Program to create a popup and display
// it on a parent frame
import main.reviews.ReviewManager;
import moviedatabase.moviedata.Movie;
import moviedatabase.moviesearch.Search;
import moviedatabase.userdata.UserAccount;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
//TODO:
// look into star wars acting bizzare with multiple monitors
class SingleMovieView {
    /**
     * Creates a popup with the selected movies info, with the option to add the movie to a wishlist,
     * which is entered as a number in a text box
     * @param movieToShow .
     */
    public void show(Movie movieToShow) {
        String chosenList = "1";
        JFrame jFrame = new JFrame();
        JPanel options = new JPanel(new GridLayout(8,1));
        JPanel whichList = new JPanel(new GridLayout(2,1));
        JPanel reviewBox = new JPanel(new GridLayout(2,2));
        JTextField review  = new JTextField();
        JLabel reviewHint = new JLabel("Enter your review here");
        reviewBox.add(reviewHint);
        reviewBox.add(review);
        //Movie info
        JButton add = new JButton("Add to Wishlist");
        JButton reviewAdd = new JButton("Add a review!");
        JLabel title = new JLabel("Title: "+movieToShow.getTitle());
        JLabel director = new JLabel("Director: "+movieToShow.getDirector());
        JLabel rated = new JLabel("Rated: "+movieToShow.getRated());
        JLabel country = new JLabel("Country: "+movieToShow.getCountry());
        JLabel genre = new JLabel("Genre: "+movieToShow.getGenre());
        JLabel language = new JLabel("Language: "+movieToShow.getLanguage());
        JLabel similar = new JLabel("Similar Movies: "+findSimilar(movieToShow));
        JLabel runtime = new JLabel("Runtime: "+movieToShow.getRuntime());

        //adding that info into the panel
        options.add(title);
        options.add(director);
        options.add(rated);
        options.add(country);
        options.add(genre);
        options.add(language);
        options.add(runtime);
        options.add(similar);

        reviewAdd.addActionListener(e -> {
            JOptionPane.showOptionDialog(null,reviewBox,"Write a review",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,null,null,null);
        });

        review.addActionListener(e->{
            ReviewManager allReviews = new ReviewManager();
            allReviews.addToTable(movieToShow.getImdbID(),review.getText());

        });
        //creates the pane for adding the movie to a wishlist
        add.addActionListener(e -> {
            WishlistView addCall = new WishlistView();
            List< ArrayList<Movie>> ourList = UserAccount.getInstance().getWishlist();
            //I could not make an array of buttons work, so we ask the user to enter the wishlist number in a text feild
            JTextField choose = new JTextField();
            choose.addActionListener(enter->{
             int list =  Integer.parseInt(choose.getText());
              if(list>=1 && list<ourList.size()){

                  addCall.addMovietoList((list-1),movieToShow);
                  System.out.println("Added to wishlist "+list);
              }
            });
            JLabel hint = new JLabel("Enter which list to add too, numbers 1 to "+ourList.size());
            whichList.add(hint);
            whichList.add(choose);
            //dialog for adding the movie to a list
            JOptionPane.showOptionDialog(null,whichList,"Add movie to a list",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,null,null,null);
        });

        options.add(add);
        options.add(reviewAdd);
        JOptionPane.showOptionDialog(null,options,movieToShow.getTitle(),JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,null,null,null);

        /*JOptionPane.showMessageDialog(jFrame, "Title: "+movieToShow.getTitle()+"\nDirector: "+movieToShow.getDirector()+"\nRated: "+
        movieToShow.getRated()+"\nCountry:"+movieToShow.getCountry()+"\nGenre: "+movieToShow.getGenre()+"\nLanguage: "+movieToShow.getLanguage()+"\nRuntime: "+
               movieToShow.getRuntime()+"\nYear: "+movieToShow.getYear()+"\n\tSimilar:  "+findSimilar(movieToShow));
        */

    }

    /**
     * uses Erik's search functions to find a couple of movies with the same genre, put the titles of the similar movies into a string,
     * and returns it for use in the single movie dialog
     * @param base .
     * @return .
     */
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


