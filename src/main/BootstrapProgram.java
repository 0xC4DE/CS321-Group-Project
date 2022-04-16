package main;
import moviedatabase.moviedata.MovieContainer;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * BootstrapProgram handles the setup of our data locations, creating and loading a .properties file that points to where the movie json and the reviews data will be stored.
 * An admin user can edit this, and change where our data is pointed too
 */
public class BootstrapProgram {
    private Path configFile;
    private Path moviePath;
    private Path reviewPath;

    public BootstrapProgram() throws IOException {
        Path test = Paths.get(System.getProperty("user.home"));
        Properties props = new Properties();
        //these are defaults
        props.setProperty("moviePath", String.valueOf(test));
        props.setProperty("reviewPath", String.valueOf(test));
            //this will actually create the file, in the base directory of the project
            props.store(new FileWriter("config.properties"), "This contains the file locations of our data");
            configFile = Paths.get("config.properties");

    }


    /**
     * Optional alternative to setConfigFile, where you dont have to update moviepath or reviewpath
     * Can also set other attributes, should the config file need more attributes.
     * @param newAttr
     * @param attrName
     * @throws IOException
     */

    /**
     * SetConfigFile, allows an admin to set the locations of the data files we pull from for both the full list of movies and the path to the stored reviews
     * @param newMoviePath
     * @param newReviewPath
     * @throws IOException
     */
    public void setConfigFile(String newMoviePath, String newReviewPath) throws IOException {
        FileInputStream in = new FileInputStream(String.valueOf(configFile));
        Properties props = new Properties();
        props.load(in);
        props.setProperty("moviePath", newMoviePath);
        props.setProperty("reviewPath", newReviewPath);

        // need to store changed props
        props.store(new FileOutputStream("configFile"), null);
    }

    /**
     * loadConfig will load the previously created .properties file, populating the moviePath and reviewsPath
     * @return true if successful, false if writing failed
     */
    public boolean loadConfig()  {
       try( FileInputStream in = new FileInputStream(String.valueOf(configFile))) {
           Properties configInfo = new Properties();
           configInfo.load(in);
           moviePath = Path.of(configInfo.getProperty("moviePath"));
           reviewPath = Path.of(configInfo.getProperty("reviewPath"));
           return true;
       }
       catch (IOException e){
           return false;
       }
    }

    //run this main to see how the Properties stuff works if you'd like to see :)
    public static void main(String []args) throws IOException {
        BootstrapProgram test = new BootstrapProgram();
        test.loadConfig();
        System.out.println(test.moviePath);
    }

}
