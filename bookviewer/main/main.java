package bookviewer.main;


import bookviewer.dataparser.DataParser;

public class main {
    public static void main(String []args) {
        DataParser dat = new DataParser();
        System.out.println(dat.getData());
    }
}
