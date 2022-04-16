package main.client;

import javax.swing.*;
import java.awt.*;

public class LoginView {
    LoginView(){
        JFrame myFrame = new JFrame("Login");

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
       /* myFrame.add(login);
        myFrame.add(guest);
        myFrame.add(create);*/
        myFrame.setSize(700,400);
        myFrame.setVisible(true);
    }

    public static void main(String[] args) {
        LoginView test = new LoginView();
    }

}
