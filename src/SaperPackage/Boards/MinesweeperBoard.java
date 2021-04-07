package SaperPackage.Boards;

import java.util.Random;

/**
 * This class prepares the game board for minesweeper game.
 */

public class MinesweeperBoard extends Board {
    private final int firstSize;
    private final int secondSize;
    private final int mines;

    // Initialize sizes and number of mines.
    public MinesweeperBoard(int firstSize, int secondSize, int mines){
        super();
        this.firstSize = firstSize;
        this.secondSize = secondSize;
        this.mines = mines;
    }

    public int getFirstSize(){
        return firstSize;
    }

    public int getSecondSize(){
        return secondSize;
    }

    public int getMines(){
        return mines;
    }

    // Returns the value of the single field
    public int checkField(int firstCoordinate, int secondCoordinate){
        return getBoard()[firstCoordinate][secondCoordinate];
    }

    /*
        This function prepare the game board. Mines are signed as -200. Other fields are signed as -100.
        If the field has a tip, the value of the tip is added to the field value.
     */
    @Override
    public void fillGameBoard(){
        int[][] tempBoard = new int[firstSize][secondSize];
        tempBoard = setMines(tempBoard);
        for(int i = 0; i < secondSize; ++i){
            for(int j = 0; j < firstSize; ++j){
                tempBoard[j][i] -= 100;
            }
        }
        tempBoard = calculateTips(tempBoard);
        setBoard(tempBoard);
    }

    // This function choose the coordinate for mines
    private int[][] setMines(int[][] arr){
        Random rand = new Random();
        int firstCoordinate, secondCoordinate;
        for(int i = 0; i < mines; ++i){
            firstCoordinate = rand.nextInt(firstSize);
            secondCoordinate = rand.nextInt(secondSize);
            if(arr[firstCoordinate][secondCoordinate] == 0){
                arr[firstCoordinate][secondCoordinate] -= 100;
            }else{
                i--;
            }
        }
        return arr;
    }

    // This function check the surrounding for each field and calculate the number of mines in the
    // neighbourhood.
    private int[][] calculateTips(int[][] arr){
        for(int i = 0; i < secondSize; ++i){
            for(int j = 0; j < firstSize; ++j){
                if(arr[j][i] == -200){
                    for (int a = -1; a <= 1; ++a){
                        for(int b = -1; b <= 1; ++b){
                            if((i+b >= 0) && (j+a >= 0) && (i+b < secondSize) && (j+a < firstSize)){
                                if(arr[j+a][i+b] != -200) {
                                    arr[j + a][i + b] += 1;
                                }
                            }
                        }
                    }
                }
            }
        }
        return arr;
    }

    // This function check whether user loose a game or not.
    public boolean isLose(){
        int minesCounter = 0;
        for(int i = 0; i < secondSize; ++i){
            for(int j = 0; j < firstSize; ++j){
                if(getBoard()[j][i] < 0){
                    if(getBoard()[j][i] == -200){
                        minesCounter++;
                    }
                }
            }
        }
        if(minesCounter < mines){
            return true;
        }
        return false;
    }

    // This function checks whether user win the game or not.
    public boolean isWin(){
        int fieldCounter = 0;
        for(int i = 0; i < secondSize; ++i){
            for(int j = 0; j < firstSize; ++j){
                if(getBoard()[j][i] < 0){
                    fieldCounter++;
                }
            }
        }

        if(fieldCounter == mines){
            return true;
        }
        return false;
    }

    // This function change value of the clicked field/
    public void changeFieldValue(int firstCoordinate, int secondCoordinate){
        getBoard()[firstCoordinate][secondCoordinate] += 100;
        if(getBoard()[firstCoordinate][secondCoordinate] == 0){
            for(int a = -1; a <= 1; ++a){
                for(int b = -1; b <= 1; ++b){
                    if((a+secondCoordinate >= 0) && (b+firstCoordinate >= 0) && (a+secondCoordinate < secondSize) && (b+firstCoordinate < firstSize)){
                        if(getBoard()[b+firstCoordinate][a+secondCoordinate] < 0) {
                            changeFieldValue(b + firstCoordinate, a + secondCoordinate);
                        }
                    }
                }
            }
        }
    }


    // Print function for tests
    public void printValues(){
        for(int i = 0; i < getSecondSize(); ++i){
            for(int j = 0; j < getFirstSize(); ++j){
                System.out.print(getBoard()[j][i] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
}
