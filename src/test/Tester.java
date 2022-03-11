package test;

public abstract class Tester {
    public Tester() {
        System.out.println(getClass().getName());
        run();
    }

    public abstract void run();
}
