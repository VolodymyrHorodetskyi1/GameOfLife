package vhorodetskyi;

/**
 * The GameOfLife class simulates Conway's Game of Life.
 */
public class GameOfLife {

    private boolean[][] grid;
    private final int size = 25;

    /**
     * Constructor to create a GameOfLife instance.
     * Initializes the grid and places a glider pattern on it.
     */
    public GameOfLife() {
        this.grid = new boolean[size][size];
        initializeGlider();
    }

    /**
     * Initializes the grid with a glider pattern.
     */
    private void initializeGlider() {
        int centerX = size / 2;
        int centerY = size / 2;

        grid[centerY - 1][centerX] = true;
        grid[centerY][centerX + 1] = true;
        grid[centerY + 1][centerX - 1] = true;
        grid[centerY + 1][centerX] = true;
        grid[centerY + 1][centerX + 1] = true;
    }

    /**
     * Generates the next generation of the grid.
     *
     * @return A 2D boolean array representing the next state of the grid.
     */
    private boolean[][] nextGeneration() {
        boolean[][] nextGrid = new boolean[size][size];

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                int liveNeighbors = countLiveNeighbors(y, x);

                if (grid[y][x]) {
                    nextGrid[y][x] = liveNeighbors == 2 || liveNeighbors == 3;
                } else {
                    nextGrid[y][x] = liveNeighbors == 3;
                }
            }
        }

        grid = nextGrid;
        return grid;
    }

    /**
     * Counts the number of live neighbors around a specific cell.
     *
     * @param row The row index of the cell.
     * @param col The column index of the cell.
     * @return The number of live neighbors.
     */
    private int countLiveNeighbors(int row, int col) {
        int liveNeighbors = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;

                int newRow = row + i;
                int newCol = col + j;

                if (newRow >= 0 && newRow < size && newCol >= 0 && newCol < size && grid[newRow][newCol]) {
                    liveNeighbors++;
                }
            }
        }

        return liveNeighbors;
    }

    /**
     * Prints the current state of the grid to the console.
     */
    private void printGrid() {
        for (boolean[] row : grid) {
            for (boolean cell : row) {
                System.out.print(cell ? "X" : ".");
            }
            System.out.println();
        }
    }

    /**
     * Runs the Game of Life simulation for a specified number of generations.
     *
     * @param numberOfGenerations The number of generations to simulate.
     */
    public void runSimulation(int numberOfGenerations) {
        for (int i = 0; i < numberOfGenerations; i++) {
            System.out.println("Generation " + (i + 1) + ":");
            printGrid();
            nextGeneration();
            System.out.println();
        }
    }
}

