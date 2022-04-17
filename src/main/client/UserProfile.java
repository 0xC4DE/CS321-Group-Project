package main.client;

import moviedatabase.userdata.UserAccount;

import javax.swing.*;
import java.awt.*;

public class UserProfile {
    public JPanel showProfile(){
        UserAccount user  = UserAccount.getInstance();
        JTextField newName = new JTextField();
        JPanel profileView = new JPanel();
        JLabel userPromt = new JLabel("Enter a new Username");
        profileView.setLayout(new BoxLayout(profileView,BoxLayout.PAGE_AXIS));
        JButton logout = new JButton("Logout");
        JButton changeName = new JButton("Change username");
        newName.addActionListener(e->{
            user.changeName(newName.getText());
            profileView.remove(newName);
        });
        logout.addActionListener(e -> {
            //logout
        });
        changeName.addActionListener(e->{
            //makes text field visible for changing name
           if(newName.isShowing()) {
               profileView.remove(userPromt);
               profileView.remove(newName);
           }
           profileView.add(userPromt);
            profileView.add(newName);
            profileView.revalidate();
        });
    profileView.add(logout);
    profileView.add(changeName);
    profileView.setSize(100,100);
    return profileView;
    }

    public static void main(String[] args) {
        JFrame test = new JFrame();
        UserProfile trying = new UserProfile();
        test.add(trying.showProfile());
        test.setVisible(true);

    }

}
