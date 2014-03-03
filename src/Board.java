import java.util.ArrayList;
import java.util.Random;

/**
 * Created by johja118 on 2014-02-07.
 */
public class Board {
    private SquareType[][] squares;
    private int width;
    private int height;
    private Poly falling;
    private int fallingX;
    private int fallingY;
    private ArrayList<BoardListener> boardListeners;

    public Board(int height, int width) {
        this.width = width + 2;
        this.height = height + 2;
        this.squares = new SquareType[this.width][this.height];
        this.boardListeners = new ArrayList<>();

        for (int rowIndex = 0; rowIndex < this.height; rowIndex++) {
            for (int columnIndex = 0; columnIndex < this.width; columnIndex++) {
                if (rowIndex == 0 || columnIndex == 0 || rowIndex == this.height - 1 || columnIndex == this.width - 1) {
                    squares[columnIndex][rowIndex] = SquareType.OUTSIDE;
                } else {
                    squares[columnIndex][rowIndex] = SquareType.EMPTY;
                }
            }
        }
    }

    public void tick() {
        if (falling != null) {
            fallingY += 1;
        } else {
            newFalling();
        }
        checkIfAtBottom();
        removeFilledRow();
        notifyListeners();
    }

    public void checkIfAtBottom() {
        for (int checkReverseX = falling.getShape().length - 1; checkReverseX >= 0; checkReverseX--) {
            for (int checkReverseY = falling.getShape().length - 1; checkReverseY >= 0; checkReverseY--) {
                if (falling.getShape()[checkReverseY][checkReverseX] != null) {
                    if (squares[checkReverseX + fallingX][checkReverseY + fallingY] != SquareType.EMPTY) {
                        addBlock();
                        return;
                    }
                }
                break;
            }
        }
    }

    //
    // This method takes a parameter which should be either -1 or 1 to decide which way to move the block
    // -1 for left and 1 for right
    //
    public void moveLeftAndRight(int way) {
        boolean checker = true;
        if (falling != null) {
            for (int checkReverseX = falling.getShape().length - 1; checkReverseX >= 0; checkReverseX--) {
                for (int checkReverseY = falling.getShape().length - 1; checkReverseY >= 0; checkReverseY--) {
                    if (falling.getShape()[checkReverseY][checkReverseX] != null) {
                        if (squares[checkReverseX + fallingX + way][checkReverseY + fallingY] != SquareType.EMPTY) {
                            checker = false;
                        }
                    }
                    break;
                }
            }
            if (checker == true) {
                fallingX += way;
                notifyListeners();
            }
        }
    }

    public void addBlock() {
        for (int fallingYIndex = 0; fallingYIndex < falling.getShape().length; fallingYIndex++) {
            for (int fallingXIndex = 0; fallingXIndex < falling.getShape().length; fallingXIndex++) {
                if (falling.getShape()[fallingYIndex][fallingXIndex] != null) {
                    squares[fallingXIndex + fallingX][fallingYIndex + fallingY - 1] = falling.getShape()[fallingYIndex][fallingXIndex];
                }
            }
        }
        falling = null;
    }

    public void newFalling() {
        Random randomNumber = new Random();
        Poly newFalling = TetrominoMaker.getPoly(randomNumber.nextInt(TetrominoMaker.getNumberOfTypes()));
        //Poly newFalling = TetrominoMaker.getPoly(1);
        falling = newFalling;
        fallingX = width / 2 - 2;
        fallingY = 1;
    }

    public void removeFilledRow() {
        boolean rowIsFilled = true;
        for (int boardY = height - 2; boardY > 1; boardY--) {
            for (int boardX = 1; boardX < width - 1; boardX++) {
                if (squares[boardX][boardY] == SquareType.EMPTY) {
                    rowIsFilled = false;
                }
            }
            if (rowIsFilled == true) {
                for (int columnIndex = 1; columnIndex < width - 1; columnIndex++) {
                    //squares[columnIndex][boardY] = SquareType.EMPTY;
                    squares[columnIndex][boardY] = squares[columnIndex][boardY - 1];
                }
            }
        }
    }

    public void ChangeBoard() {
        Random rnd = new Random();

        for (int rowIndex = 0; rowIndex < height; rowIndex++) {
            for (int columnIndex = 0; columnIndex < width; columnIndex++) {
                if (rowIndex == 0 || columnIndex == 0 || rowIndex == height - 1 || columnIndex == width - 1) {
                    squares[columnIndex][rowIndex] = SquareType.OUTSIDE;
                } else {
                    squares[columnIndex][rowIndex] = SquareType.values()[rnd.nextInt(SquareType.values().length - 1)];
                }
            }
        }
        notifyListeners();
    }

    public void addFalling(Poly poly, int x, int y) {
        falling = poly;
        fallingX = x;
        fallingY = y;
        notifyListeners();
    }

    public void addBoardListener(BoardListener bl) {
        boardListeners.add(bl);
    }

    private void notifyListeners() {
        for (int listIndex = 0; listIndex < boardListeners.size(); listIndex++) {
            boardListeners.get(listIndex).boardChanged();
        }
    }

    public SquareType getValue(int x, int y) {
        return squares[y][x];
    }

    public int getFallingX() {
        return fallingX;
    }

    public int getFallingY() {
        return fallingY;
    }

    public Poly getFalling() {
        return falling;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(final int height) {
        this.height = height;
    }

    public void setWidth(final int width) {
        this.width = width;
    }
}
