package main.reviews;


import com.google.gson.Gson;
import main.BootstrapProgram;
import moviedatabase.moviedata.Movie;
import moviedatabase.moviedata.MovieContainer;
import views.client.WishlistView;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class ReviewManager {
    public Map getReviewTable() {
        return reviewTable;
    }
    public Path reviewPath;

    private static Map reviewTable;
    private static BootstrapProgram bootLocations;
    public ReviewManager(BootstrapProgram dataLocals){
       bootLocations = dataLocals;
       bootLocations.loadConfig();
        reviewPath = bootLocations.getReviewPath();
        try {
            loadReviews();
        } catch (FileNotFoundException e) {
            reviewTable = new HashMap<String,String>();
            MovieContainer.getInstance().addReviews(reviewTable);
            WishlistView wishlistView = new WishlistView();
            wishlistView.setReviewWishList(reviewTable);
        }
    }
    public ReviewManager(){
        if(bootLocations == null){
             new ReviewManager(new BootstrapProgram());
        }
    }
    public void addToTable(String id, String review){
        reviewTable.put(id,review);
    }
    public void saveReviews() throws IOException {
        FileWriter reviewWriter = new FileWriter(String.valueOf(reviewPath));
        Gson reviewJson = new Gson();
        reviewJson.toJson(reviewTable,reviewWriter);
        reviewWriter.flush();
        reviewWriter.close();
    }
    private void loadReviews() throws FileNotFoundException {
        Gson reviewJson = new Gson();
        Reader reviewReader = new FileReader(String.valueOf(reviewPath));
        reviewTable = reviewJson.fromJson(reviewReader, Map.class);
        if(reviewTable == null){
            reviewTable = new Hashtable<String,String>();
        }
        MovieContainer.getInstance().addReviews(reviewTable);
        WishlistView wishlistView = new WishlistView();
        wishlistView.setReviewWishList(reviewTable);
    }





}
