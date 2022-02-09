package movieviewer.main;


import movieviewer.dataparser.DataParser;

public class main {
    public static void main(String []args) {
        DataParser dat = new DataParser();
        System.out.println(dat.getData());
    }
}
