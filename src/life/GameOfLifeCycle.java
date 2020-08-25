package life;

public class GameOfLifeCycle extends Thread {
    GameOfLife gameOfLife;

    public GameOfLifeCycle(GameOfLife gameOfLife) {
        this.gameOfLife = gameOfLife;
    }

    @Override
    public void run() {
        gameOfLife.play();
    }
}
