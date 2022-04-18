package main.reviews;


import com.google.gson.Gson;
import main.BootstrapProgram;

import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class ReviewManager {
    public Map getReviewTable() {
        return reviewTable;
    }
    public Path reviewPath;

    private Map reviewTable;
    private BootstrapProgram bootLocations;
    public ReviewManager(BootstrapProgram dataLocals){
       bootLocations = dataLocals;
       bootLocations.loadConfig();
        reviewPath = bootLocations.getReviewPath();
        try {
            loadReviews();
        } catch (FileNotFoundException e) {
            reviewTable = new HashMap<String,String>();
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
        reviewTable = reviewJson.fromJson(reviewReader,Map.class);

    }

    public static void main(String[] args)  {
        /*BootstrapProgram test = new BootstrapProgram();
        test.loadConfig();*/
        ReviewManager review = new ReviewManager(new BootstrapProgram());
        review.addToTable("100","Pretty good");
        try {
            review.saveReviews();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
