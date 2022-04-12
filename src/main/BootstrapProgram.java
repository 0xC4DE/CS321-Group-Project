package main;
import moviedatabase.moviedata.Movie;
import moviedatabase.moviedata.MovieContainer;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Scanner;

public class BootstrapProgram {
    private String movieFile;
    private File configFile;
    private String reviewPath;
    public void BoostrapProgram() throws IOException {
        this.configFile = new File("user.home/movieconfig.properties");
        this.configFile.createNewFile(); // does nothing if file exists
        try(FileInputStream in = new FileInputStream(configFile)) {
            Properties props = new Properties();
            props.load(in);
            props.setProperty("moviePath", "data/data.json");
            props.setProperty("reviewPath", "data/reviews.json");

            props.store(new FileOutputStream("xyz.properties"), null);
        }
        catch (IOException e){
        }
    }

    public void setConfigAttr(String newAttr, String attrName) throws IOException{
        FileInputStream fileStream = new FileInputStream(configFile);
        Properties prop = new Properties();
        prop.load(fileStream);
        prop.setProperty(attrName, newAttr);

        prop.store(new FileOutputStream(configFile), null);
    }

    public void setConfigFile(String newMoviePath, String newReviewPath) throws IOException {
        FileInputStream in = new FileInputStream(configFile);
        Properties props = new Properties();
        props.load(in);
        props.setProperty("moviePath", newMoviePath);
        props.setProperty("reviewPath", newReviewPath);

        // need to store changed props
        props.store(new FileOutputStream("configFile"), null);
    }

    public boolean loadConfig()  {
       try( FileInputStream in = new FileInputStream(configFile)) {
           Properties configInfo = new Properties();
           configInfo.load(in);
           movieFile = configInfo.getProperty("moviePath");
           reviewPath = configInfo.getProperty("reviewPath");
           return true;
       }
       catch (IOException e){
           return false;
       }

    }
    public void loadMovies(){
        MovieContainer temp = MovieContainer.getInstance();
        temp.collectMovies(Path.of(movieFile));
    }


}
