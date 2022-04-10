package moviedatabase.userdata;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import moviedatabase.exceptions.PasswordDoesNotMatchException;
import moviedatabase.exceptions.UserAlreadyExistsException;
import moviedatabase.moviedata.Movie;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;


public class User {
    private int uuid;
    private String Username;
    private String passwordHash;
    public List<Movie> wishlistsToStore;

    // guest login
    public User() {
        uuid = -1;
        Username = "guest";
        passwordHash = "";
    }

    public int getUUID() {return uuid;}
    public String getUsername() { return Username; }
    public String getPasswordHash() { return passwordHash; }

    /*
    This isn't intended to be constructed directly. rather you use login
     */
    private User(String username, String password, Integer uuid) throws NoSuchAlgorithmException, FileNotFoundException {
        this.Username = username;
        this.passwordHash = hashPassword(password);
        this.uuid = uuid;
    }

    private Path getUserFilePath(Path userFile) throws IOException {
        if (userFile == null){
            userFile = Paths.get(System.getProperty("user.home"), ".users");
        }
        // Create if not exists
        if (!Files.exists(userFile)){
            Files.createFile(userFile);

            List<User> emptyList = new ArrayList<>();
            emptyList.add(new User());
            saveUserFile(emptyList, userFile);
        }

        return userFile;
    }

    private Reader getUserFileReader(Path userFile) throws IOException {
        userFile = getUserFilePath(userFile);

        return new FileReader(String.valueOf(userFile));
    }

    /*
    Gets the next UUID in sequence, as each user has a unique UUID, and it needs to find the next unfilled UUID
     */
    private Integer getNextUUID() throws IOException {
        Gson users = new Gson();
        Type userType = new TypeToken<ArrayList<User>>(){}.getType();
        List<User> userList = users.fromJson(getUserFileReader(null), userType);
        int uuid = 0;
        for (User u: userList) {
            if (u.uuid > uuid) {
                uuid = u.uuid;
            }
        }
        return uuid+1;
    }

    public void saveUserFile(List<User> userList, Path userFile) throws IOException {
        FileWriter writer = new FileWriter(String.valueOf(getUserFilePath(userFile)));
        Gson users = new Gson();
        users.toJson(userList, writer);
        writer.flush();
        writer.close();
    }

    public User createUser(String username, String password, Path userFile){
        // sanitize username entry
        username = username.toLowerCase().replace(" ", "");

        // read the file and parse data
        try {
            // Find the file that stores users
            Gson users = new Gson();
            Type userType = new TypeToken<ArrayList<User>>(){}.getType();
            List<User> userList = users.fromJson(getUserFileReader(userFile), userType);

            // Check if user already exists
            boolean user_found = false;

            for (User user : userList){
                if (Objects.equals(user.getUsername(), username)) {
                    user_found = true;
                    break;
                }
            }
            if (user_found) { throw new UserAlreadyExistsException(); }

            // Save user to file
            User new_user = new User(username, password, getNextUUID());
            userList.add(new_user);
            new_user.saveUserFile(userList, userFile);
            return new_user;

        } catch (UserAlreadyExistsException | NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    Gets a User given the username, password, and file containing that user.
     */
    public User getUser(String username, String password, Path userFile){
        if (userFile == null) {
            userFile = Paths.get(System.getProperty("user.home"), ".users");
        }

        // sanitize username entry
        username = username.toLowerCase().replace(" ", "");

        // read the file and parse data
        try {
            Reader user_file = new FileReader(String.valueOf(userFile));
            Gson users = new Gson();
            Type userType = new TypeToken<ArrayList<User>>(){}.getType();
            List<User> userList = users.fromJson(user_file, userType);

            // find the user
            User user_found = new User();
            for (User user : userList){
                if (Objects.equals(user.getUsername(), username)){
                    user_found = user;
                }
            }

            if (!user_found.checkPassword(password)){
                throw new Exception("Wrong Password.");
            }

            return user_found;

        } catch (FileNotFoundException e) {
            return new User();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    Returns true if the **current** user was successfully deleted, false if not found or improper login.
     */
    public Boolean delUser(Path userFile) throws IOException, NoSuchAlgorithmException {
        Gson users = new Gson();
        List<User> userList = users.fromJson(getUserFileReader(userFile), List.class);

        if (userList.remove(this)){
            saveUserFile(userList, userFile);
            return true;
        }

        return false;
    }

    /*
    This is a method intended to be called by a setting in the profile, where the user can freely change their name
     */
    public void setUsername(String newName){
        // Sanitizing the name
        newName = newName.toLowerCase();
        newName = newName.replace("", " ");

        Username = newName;
    }

    /*
    Given the CORRECT current password, and a new password, set a new hashed password.
     */
    public void setPassword(String current_password, String password) throws Exception {
        if (!checkPassword(current_password)){
            throw new PasswordDoesNotMatchException();
        }
        passwordHash = hashPassword(password);
    }

    /*
    Given a password, will check it against the current password hash.
    (Used for login)
     */
    private Boolean checkPassword(String password) throws NoSuchAlgorithmException {
        String hash = hashPassword(password);
        return (hash.equals(passwordHash));
    }

    /*
    Returns the hash of the string inserted. This would work better as an util, but we have no such util
     */
    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(password.getBytes());
        byte[] digest = md5.digest();
        String hash = HexFormat.of().formatHex(digest);
        return hash;
    }
}

