package main.client;

import moviedatabase.moviesearch.Search;

import javax.swing.*;

public class Dashboard {

    /**
     *
     */
    public Dashboard() {
        JFrame window = new JFrame("Dashboard");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // close window on 'X' always

        SearchView searchPanel = new SearchView();
        WishlistView wishlistPanel = new WishlistView();
        JPanel profilePanel = new JPanel();

        JTabbedPane tabPanel = new JTabbedPane();
        tabPanel.setBounds(50,30,600,300);

        // tab text is white when selected on macOS
        tabPanel.add("Search", searchPanel.SearchBox());
        tabPanel.add("Wishlists", wishlistPanel.showWishlists());
        tabPanel.add("Profile", profilePanel);
        window.add(tabPanel);
        window.setSize(700,400);
        window.setLayout(null);
        window.setVisible(true);

    }

}
