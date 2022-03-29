package moviedatabase.userdata;

import com.google.gson.Gson;
import moviedatabase.exceptions.PasswordDoesNotMatchException;
import moviedatabase.exceptions.UserAlreadyExistsException;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;


public class User {
    private Integer uuid;
    private String Username;
    private String passwordHash;

    // guest login
    User() {
        uuid = -1;
        Username = "guest";
        passwordHash = "";
    }

    // new user login
    private User(String username, String password, Integer uuid) throws NoSuchAlgorithmException, FileNotFoundException {
        this.Username = username;
        this.passwordHash = getPasswordHash(password);
        this.uuid = uuid;
    }

    private static Path getUserFilePath(Path userFile){
        if (userFile == null){
            return Paths.get(System.getProperty("user.home"), ".users");
        }
        return userFile;
    }

    private static Reader getUserFileReader(Path userFile) throws FileNotFoundException {
        userFile = getUserFilePath(userFile);
        Reader userReader = new FileReader(String.valueOf(userFile));
        return userReader;
    }

    /*
    Gets the next UUID in sequence
     */
    private static Integer getNextUUID() throws FileNotFoundException {
        Gson users = new Gson();
        List<User> userList = users.fromJson(getUserFileReader(null), List.class);
        Integer uuid = 0;
        for (User u: userList) {
            if (u.uuid > uuid) {
                uuid = u.uuid;
            }
        }
        return uuid+1;
    }

    public static User createUser(String username, String password, Path userFile){
        // sanitize username entry
        username = username.toLowerCase().replace(" ", "");

        // read the file and parse data
        try {
            // Find the file that stores users
            Gson users = new Gson();
            List<User> userList = users.fromJson(getUserFileReader(userFile), List.class);
            String finalUsername = username;

            // Check if user already exists
            User user_found = userList.stream().filter(c -> c.getUsername() == finalUsername).collect(Collectors.toList()).get(0);
            if (user_found != null) {
                throw new UserAlreadyExistsException();
            }

            // Save user to file
            User new_user = new User(username, password, getNextUUID());
            userList.add(new_user);
            users.toJson(userList, new FileWriter(String.valueOf(getUserFilePath(userFile))));

            return new_user;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UserAlreadyExistsException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


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
            List<User> userList = users.fromJson(user_file, List.class);

            // A one liner to get a user matching the username
            // Also accesses first index because username should be unique.
            String finalUsername = username;
            User user_found = userList.stream().filter(c -> c.getUsername() == finalUsername).collect(Collectors.toList()).get(0);
            if (!user_found.checkPassword(password)){
                throw new Exception("Wrong Password.");
            }
            return user_found;

        } catch (FileNotFoundException e) {
            return new User();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setUsername(String newName){
        // Sanitizing the name
        newName = newName.toLowerCase();
        newName = newName.replace("", " ");

        Username = newName;
    }

    public User makeUser(Path userFile){
        if (userFile == null) {
            userFile = Paths.get(System.getProperty("user.home"), ".users");
        }
        try {
            Reader user_file = new FileReader(String.valueOf(userFile));
            Gson users = new Gson();
            List<User> userList = users.fromJson(user_file, List.class);

        } catch (FileNotFoundException e) {
            return new User();
        }

        return new User();
    }

    public void setPassword(String current_password, String password) throws Exception {
        if (!checkPassword(current_password)){
            throw new PasswordDoesNotMatchException();
        }
        passwordHash = getPasswordHash(password);
    }

    public Boolean checkPassword(String password) throws NoSuchAlgorithmException {
        String hash = getPasswordHash(password);
        return (hash == passwordHash);
    }

    public String getUsername() {
        return Username;
    }

    private String getPasswordHash(String password) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(password.getBytes());
        String hash = Base64.getDecoder().decode(md5.digest()).toString().toUpperCase();
        return hash;
    }
}
