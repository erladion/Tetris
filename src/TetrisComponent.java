/**
 * Created by Johan on 2014-02-27.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.util.EnumMap;

/**
 * Created by johja118 on 2014-02-14.
 */
public class TetrisComponent extends JComponent implements BoardListener
{
    private Board board;
    private EnumMap<SquareType, Color> colorEnumMap;
    private final static int BLOCK_SIZE = 30;

    public TetrisComponent(final Board board, EnumMap<SquareType, java.awt.Color> colorEnumMap) {
        this.board = board;
        this.colorEnumMap = colorEnumMap;
    }

    public Dimension getPreferredSize() {
        return new Dimension(board.getWidth() * (BLOCK_SIZE + 1) + 1, 683);
    }

    @Override
    public boolean isFocusable() {
        return true;
    }

    @Override protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;

        for (int rowIndex = 0; rowIndex < board.getHeight(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < board.getWidth(); columnIndex++) {
                g2d.setColor(colorEnumMap.get(board.getValue(rowIndex, columnIndex)));
                g2d.fillRect(columnIndex * (BLOCK_SIZE + 1), rowIndex * (BLOCK_SIZE + 1), BLOCK_SIZE, BLOCK_SIZE);
            }
        }
        if (board.getFalling() != null) {
            for (int fallingRowIndex = 0; fallingRowIndex < board.getFalling().getShape().length; fallingRowIndex++) {
                for (int fallingColumnIndex = 0;
                     fallingColumnIndex < board.getFalling().getShape().length;
                     fallingColumnIndex++) {
                    g2d.setColor(colorEnumMap.get(board.getFalling().getShape()[fallingColumnIndex][fallingRowIndex]));
                    if(board.getFalling().getShape()[fallingColumnIndex][fallingRowIndex] != null ){
                        g2d.fillRect((fallingRowIndex + board.getFallingX()) * (BLOCK_SIZE + 1),
                                (fallingColumnIndex + board.getFallingY()) * (BLOCK_SIZE + 1), BLOCK_SIZE, BLOCK_SIZE);
                    }
                }
            }
        }
    }

    @Override public void boardChanged() {
        repaint();
    }
}
