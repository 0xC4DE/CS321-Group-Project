package main.client;
// Java Program to create a popup and display
// it on a parent frame
import moviedatabase.moviedata.Movie;
import moviedatabase.moviesearch.Search;
import moviedatabase.userdata.UserAccount;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

class SingleMovieView {
    public void show(Movie movieToShow) {
        String chosenList = "1";
        JFrame jFrame = new JFrame();
        JPanel options = new JPanel(new GridLayout(8,1));
        JPanel whichList = new JPanel(new GridLayout(2,1));

        JButton add = new JButton("Add to Wishlist");
        JLabel title = new JLabel("Title: "+movieToShow.getTitle());
        JLabel director = new JLabel("Director: "+movieToShow.getDirector());
        JLabel rated = new JLabel("Rated: "+movieToShow.getRated());
        JLabel country = new JLabel("Country: "+movieToShow.getCountry());
        JLabel genre = new JLabel("Genre: "+movieToShow.getGenre());
        JLabel language = new JLabel("Language: "+movieToShow.getLanguage());
        JLabel similar = new JLabel("Similar Movies: "+findSimilar(movieToShow));
        JLabel runtime = new JLabel("Runtime: "+movieToShow.getRuntime());
        options.add(title);
        options.add(director);
        options.add(rated);
        options.add(country);
        options.add(genre);
        options.add(language);
        options.add(runtime);

        add.addActionListener(e -> {
            WishlistView addCall = new WishlistView();
            List< ArrayList<Movie>> ourList = UserAccount.getInstance().getWishlist();
            JTextField choose = new JTextField();
            choose.addActionListener(enter->{
             int list =  Integer.parseInt(choose.getText());
              if(list>0 && list<ourList.size()){
                  
                  addCall.addMovietoList(list,movieToShow);
              }
            });
            JLabel hint = new JLabel("Enter which list to add too, numbers 1 to "+ourList.size());
            whichList.add(hint);
            whichList.add(choose);

            JOptionPane.showOptionDialog(null,whichList,"Add movie to a list",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,null,null,null);
        });

        options.add(add);
        JOptionPane.showOptionDialog(null,options,movieToShow.getTitle(),JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,null,null,null);

        /*JOptionPane.showMessageDialog(jFrame, "Title: "+movieToShow.getTitle()+"\nDirector: "+movieToShow.getDirector()+"\nRated: "+
        movieToShow.getRated()+"\nCountry:"+movieToShow.getCountry()+"\nGenre: "+movieToShow.getGenre()+"\nLanguage: "+movieToShow.getLanguage()+"\nRuntime: "+
               movieToShow.getRuntime()+"\nYear: "+movieToShow.getYear()+"\n\tSimilar:  "+findSimilar(movieToShow));
        */

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


