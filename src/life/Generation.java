package life;

public class Generation {


    public static boolean[][] generateNext(Universe universe){
        boolean[][] currentBoard = universe.getBoard();
        boolean[][] newBoard = new boolean[currentBoard.length][currentBoard.length];

        for (int i = 0; i < currentBoard.length; i++) {
            for (int j = 0; j < currentBoard.length; j++) {
                int neighboursCount = getNeighbourCount(i, j, universe.getBoard());
                if (currentBoard[i][j]) { // if alive
                    if (neighboursCount == 2 || neighboursCount == 3){
                        newBoard[i][j] = true;
                    } else {
                        newBoard[i][j] = false;
                    }
                } else { // if dead
                    if (neighboursCount == 3){
                        newBoard[i][j] = true;
                    } else {
                        newBoard[i][j] = false;
                    }
                }
            }
        }
        return newBoard;
    }

    static int getNeighbourCount(int i, int j, boolean[][] board) {
        int sum = 0;

        // NW:
        if ( i != 0 && j != 0){
            sum += board[i-1][j-1] ? 1 : 0;
        } else if (i == 0 && j != 0){
            sum += board[board.length-1][j-1] ? 1 : 0;
        } else if (i != 0 && j == 0) { // j = 0, i != 0
            sum += board[i-1][board.length-1] ? 1 : 0;
        } else { // i == 0 && j == 0
            sum += board[board.length-1][board.length-1] ? 1 : 0;
        }

        // N:
        if (i == 0) {
            sum += board[board.length-1][j] ? 1 : 0;
        } else {
            sum += board[i-1][j] ? 1 : 0;
        }

        // NE:
        if ( i != 0 && j != board.length-1){
            sum += board[i-1][j+1] ? 1 : 0;
        } else if (i == 0 && j != board.length-1){
            sum += board[board.length-1][j+1] ? 1 : 0;
        } else if (i != 0 && j == board.length -1){
            sum += board[i-1][0] ? 1 : 0;
        } else { // i == 0 && j == board.length - 1
            sum += board[board.length-1][0] ? 1 : 0;
        }

        // W:
        if (j == 0){
            sum += board[i][board.length-1] ? 1 : 0;
        } else {
            sum += board[i][j-1] ? 1 : 0;
        }

        // E:
        if (j == board.length-1) {
            sum += board[i][0] ? 1: 0;
        } else {
            sum += board[i][j+1] ? 1 : 0;
        }

        // SW:
        if ( i != board.length -1 && j != 0 ){
            sum += board[i+1][j-1] ? 1 : 0;
        } else if ( i == board.length -1 && j != 0) {
            sum += board[0][j-1] ? 1 : 0;
        } else if ( i != board.length -1 && j == 0){
            sum += board[i+1][board.length-1] ? 1 : 0;
        } else { // i == board.length-1 && j == 0
            sum += board[0][board.length-1] ? 1 : 0;
        }

        // S:
        if (i == board.length-1) {
            sum += board[0][j] ? 1 : 0;
        } else {
            sum += board[i+1][j] ? 1 : 0;
        }

        // SE:
        if ( i != board.length -1 && j != board.length -1 ){
            sum += board[i+1][j+1] ? 1 : 0;
        } else if ( i != board.length -1 && j == board.length -1 ) {
            sum += board[i+1][0] ? 1 : 0;
        } else if ( i == board.length -1 && j != board.length -1 ) {
            sum += board[0][j+1] ? 1 : 0;
        } else { // i == board.length -1 && j == board.length -1
            sum += board[0][0] ? 1 : 0;
        }

        return sum;
    }
}
