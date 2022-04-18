package main.client;

import moviedatabase.userdata.UserAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.security.spec.ECField;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LoginView {
    private UserAccount myUser = UserAccount.getInstance();
    private JFrame myFrame = new JFrame("Login");

    /**
     * Takes a thread lock and a Condition object to signal once the user has successfully logged in
     * This is used so we don't continue execution until the user has logged in somehow
     * @param loggedIn
     * @param lock
     */
    public void loginLoop(Condition loggedIn, Lock lock){

        JPanel infoSection = new JPanel(new GridLayout(3,2));
        JTextField username  = new JTextField();
        JTextField password = new JTextField();
        JButton login = new JButton("Login");
        JButton create = new JButton("Create User");
        JButton guest = new JButton("Login As Guest");
        JPanel buttonSection = new JPanel(new GridLayout(1,3));
        buttonSection.add(login);
        buttonSection.add(create);
        buttonSection.add(guest);
        infoSection.add(new JLabel("Username"));
        infoSection.add(new JLabel("Password"));
        infoSection.add(username);
        infoSection.add(password);
        myFrame.add(infoSection);
        myFrame.add(buttonSection);
        myFrame.setLayout(new GridLayout(4,4));
        //For each of these buttons, they have to be able to lock and unlock the thread, so we need the try catch finally in each, so the can lock, signal, then unlock
        login.addActionListener(e -> {
            lock.lock();
            try {

                if (myUser.login(username.getText(), password.getText(), null)) {
                    loggedIn.signal();
                }
            }
            catch(Exception t){

            }
            finally {
                lock.unlock();
            }
        });

        create.addActionListener(e ->{
            lock.lock();
            try {
                if(myUser.createAccount(username.getText(), password.getText(), null)) {
                    loggedIn.signal();
                }
                else{
                    JLabel error = new JLabel("User already Exists");
                    myFrame.add(error);
                    wait(1000);
                    myFrame.remove(error);
                }
            }
            catch(Exception t){

            }
            finally {
                lock.unlock();
            }
        } );
        guest.addActionListener(e->{
            lock.lock();
            try {
                myUser.loginAsGuest();
                loggedIn.signal();
            }
            catch(Exception t){

            }
            finally {
                lock.unlock();
            }
        });
        myFrame.setSize(700,400);
        myFrame.setVisible(true);
    }


}