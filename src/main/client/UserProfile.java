package main.client;

import moviedatabase.userdata.UserAccount;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class UserProfile {
    public JPanel showProfile(){
        UserAccount user  = UserAccount.getInstance();
        JTextField newName = new JTextField();
        JPanel profileView = new JPanel();
        JTextField newPass= new JTextField();
        JTextField oldPass = new JTextField();
        JLabel passPrompt1 = new JLabel("Old password");
        JLabel passPromt2 = new JLabel("New password");
        JLabel userPromt = new JLabel("Enter a new Username");
        profileView.setLayout(new BoxLayout(profileView,BoxLayout.PAGE_AXIS));
        JButton logout = new JButton("Logout");
        JButton changeName = new JButton("Change username");
        JButton changePass = new JButton("Change Password");


        newPass.addActionListener(e->{
            try {
                user.changePassword(oldPass.getText(),newPass.getText());
            } catch (Exception ex) {
                JLabel error = new JLabel("Incorrect previous password");
                profileView.add(error);
            }
        });
        changePass.addActionListener(e->{
            if(newPass.isShowing()){
                profileView.remove(newPass);
                profileView.remove(oldPass);
                profileView.remove(passPrompt1);
                profileView.remove(passPromt2);
            }
            profileView.add(passPrompt1);
            profileView.add(oldPass);
            profileView.add(passPromt2);
            profileView.add(newPass);
            profileView.revalidate();
        });
        newName.addActionListener(e->{
            user.changeName(newName.getText());
            profileView.remove(newName);
        });
        logout.addActionListener(e -> {
            try {
                user.logOut();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            System.exit(0);
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
    profileView.add(changePass);
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
