package main.reviews;


import com.google.gson.Gson;
import main.BootstrapProgram;

import java.io.*;
import java.nio.file.Path;
import java.util.Hashtable;
import java.util.Map;

public class ReviewManager extends BootstrapProgram {
    public Map getReviewTable() {
        return reviewTable;
    }
    public Path reviewPath;

    private Map reviewTable;
    public ReviewManager(){
        reviewPath = getReviewPath();

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
    public void loadReviews() throws FileNotFoundException {
        Gson reviewJson = new Gson();
        Reader reviewReader = new FileReader(String.valueOf(reviewPath));
        reviewTable = reviewJson.fromJson(reviewReader,Map.class);

    }


}
