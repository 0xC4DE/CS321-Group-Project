package main.client;

import javax.swing.*;

public class Dashboard {

    /**
     *
     */
    Dashboard() {
        JFrame window =new JFrame("Dashboard");

        JPanel searchPanel = new JPanel();
        JPanel wishlistPanel = new JPanel();
        JPanel profilePanel = new JPanel();

        JTabbedPane tabPanel = new JTabbedPane();
        tabPanel.setBounds(50,50,500,200);

        // tab text is white when selected on macOS
        tabPanel.add("Search", searchPanel);
        tabPanel.add("Wishlists", wishlistPanel);
        tabPanel.add("Profile", profilePanel);

        window.add(tabPanel);
        window.setSize(400,400);
        window.setLayout(null);
        window.setVisible(true);
    }

    public static void main(String[] args) {
        Dashboard d = new Dashboard();
    }
}
