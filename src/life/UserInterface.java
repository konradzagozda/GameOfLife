package life;

import java.util.Scanner;

public class UserInterface {
    Scanner scanner;

    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
    }

    void start() {
        String[] parts = scanner.nextLine().split(" ");
        int size = Integer.parseInt(parts[0]);
        int seed = Integer.parseInt(parts[1]);
        int generation = Integer.parseInt(parts[2]);
        Universe universe = new Universe(size, seed, generation);
        universe.printView();
    }

    void startSimulation() throws InterruptedException {
        int size = scanner.nextInt();
        Universe universe = new Universe(size);
        int i = 0;
        while(i < 15){
            System.out.println("Generation #" + universe.getGeneration());
            System.out.println("Alive: " + universe.getAlive());

            universe.printView();
            universe.nextGeneration();
            i++;
            Thread.sleep(800);
        }
    }
}
