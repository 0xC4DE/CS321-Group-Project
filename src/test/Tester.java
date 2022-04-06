package test;

public abstract class Tester {
    public Tester() throws Exception {
        System.out.println(getClass().getName());
        run();
    }

    public abstract void run() throws Exception;
}
