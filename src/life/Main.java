package life;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        GameOfLife gui = new GameOfLife();
        GameOfLifeCycle thread = new GameOfLifeCycle(gui);
        thread.start();
    }
}
