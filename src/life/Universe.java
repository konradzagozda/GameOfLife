package life;

import java.util.Random;

public class Universe {
    Random random;
    private int size;
    private int seed;
    private int generation;
    private boolean[][] board;

    public int getGeneration() {
        return generation + 1;
    }

    public int getAlive() {
        int alive = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j]){
                    alive++;
                }
            }
        }
        return alive;
    }

    public Universe(int size, int seed) {
        this(size, seed, 0);
    }

    public Universe(int size){
        random = new Random();
        this.size = size;
        this.generation = 0;

        board = new boolean[size][size];
        initFillBoard();
    }

    public Universe(int size, int seed, int generation) {
        this.size = size;
        this.seed = seed;
        this.generation = generation;

        random = new Random(seed);
        board = new boolean[size][size];
        initFillBoard();
        skipGenerations(generation);
    }

    public void skipGenerations(int generations) { // 1 = one generations skipped, 0 - base generation
        boolean[][] outBoard = board;
        for (int i = 0; i < generations; i++) {
            board = Generation.generateNext(this);
        }
    }

    public void initFillBoard() {

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = random.nextBoolean();
            }
        }
    }

    public void nextGeneration() {
        board = Generation.generateNext(this);
        generation++;
    }

    public void printView() {
        for (boolean row[] : board
        ) {
            for (boolean elem : row
            ) {
                if (elem == true) {
                    System.out.print('O');
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }


    public boolean[][] getBoard() {
        return board;
    }
}
