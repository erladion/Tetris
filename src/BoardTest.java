import javax.swing.*;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

/**
 * Created by johja118 on 2014-02-07.
 */
public class BoardTest
{
    public static void main(String[] args) {
        final Board board = new Board(20, 10);
        final TetrisFrame tetrisWindow = new TetrisFrame(board);

        board.addBoardListener(tetrisWindow.getTetrisComponent());
        final Action randomBoard = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e) {
                board.tick();
            }
        };

        //
        //  Method moveLeftAndRight in board takes a parameter to decide
        //  which way it should move the block -1 is left and 1 is right
        //
        final Action moveLeft = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e) {
                board.moveLeftAndRight(-1);
            }
        };

        final Action moveRight = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e) {
                board.moveLeftAndRight(1);
            }
        };

        final Timer clockTimer = new Timer(500, randomBoard);

        clockTimer.setCoalesce(true);
        clockTimer.start();

        tetrisWindow.getTetrisComponent().getInputMap().put(KeyStroke.getKeyStroke("A"),
                "moveLeft");
        tetrisWindow.getTetrisComponent().getActionMap().put("moveLeft",
                moveLeft);

        tetrisWindow.getTetrisComponent().getInputMap().put(KeyStroke.getKeyStroke("D"),
                "moveRight");
        tetrisWindow.getTetrisComponent().getActionMap().put("moveRight",
                moveRight);
    }
}
