package SaperPackage.Boards;

/**
 * This class prepares a board. I decided to create it abstract, because in the future
 * I will merge minesweeper, tic tac toe and snake games.
 */

public abstract class Board {
    private int[][] board;

    public Board(){
        this.board = null;
    }

    public abstract void fillGameBoard();

    public int[][] getBoard(){
        return board;
    }

    public void setBoard(int[][] board){
        this.board = board;
    }
}
