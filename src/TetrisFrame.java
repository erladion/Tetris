import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.EnumMap;

/**
 * Created by johja118 on 2014-02-14.
 */
public class TetrisFrame extends JFrame
{
    private Board board;
    private JTextArea textArea;
    private TetrisComponent tetrisComponent;

    public TetrisComponent getTetrisComponent() {
        return tetrisComponent;
    }

    public TetrisFrame(final Board board){
        super("Tetris");
        this.board = board;

        EnumMap<SquareType, java.awt.Color> colorEnumMap =
                new EnumMap<SquareType, java.awt.Color>(SquareType.class);

        // LightBlue/Cyan I Block
        //
        // ####
        colorEnumMap.put(SquareType.I, Color.CYAN);
        // Yellow O Block
        // ##
        // ##
        colorEnumMap.put(SquareType.O, Color.YELLOW);
        // Purple T Block
        //  #
        // ###
        colorEnumMap.put(SquareType.T, new Color(169, 39, 255));
        // Green S Block
        //  ##
        // ##
        colorEnumMap.put(SquareType.S, Color.GREEN);
        // Red Z Block
        // ##
        //  ##
        colorEnumMap.put(SquareType.Z, Color.RED);
        // Blue J Block
        //  #
        //  #
        // ##
        colorEnumMap.put(SquareType.J, Color.BLUE);
        // Orange L Block
        // #
        // #
        // ##
        colorEnumMap.put(SquareType.L, Color.ORANGE);

        colorEnumMap.put(SquareType.OUTSIDE, Color.BLACK);
        colorEnumMap.put(SquareType.EMPTY, Color.WHITE);




        this.tetrisComponent = new TetrisComponent(this.board, colorEnumMap);


        this.textArea = new JTextArea(board.getHeight(), board.getWidth());
        this.textArea.setText(TetrisTextView.convertToText(board));

        this.setLayout(new BorderLayout());
        this.add(this.tetrisComponent, BorderLayout.CENTER);

        createMenus();




        Font font = new Font("Courier", Font.BOLD, 20);
        textArea.setFont(font);

        this.pack();
        this.setVisible(true);
    }

    public void UpdateText(String text){
        textArea.setText(text);
    }

    private void createMenus(){
        final JMenu file = new JMenu("File");
        JMenuItem newGameItem = new JMenuItem("New Game");
        file.add(newGameItem);
        JMenuItem saveItem = new JMenuItem("Save");
        file.add(saveItem);
        JMenuItem quitItem = new JMenuItem("Quit");
        file.add(quitItem);


        final JMenuBar bar = new JMenuBar();
        bar.add(file);
        this.setJMenuBar(bar);

        final TetrisFrame frame = this;
        class QuitListener implements ActionListener{
            public void actionPerformed(ActionEvent event){
                Object[] options = {
                        "Yes I'm sure",
                        "No I don't want to quit",
                        "I'm a retard!"
                };

                int optionChosen = JOptionPane.showOptionDialog(frame,
                        "Are you sure you want to quit?",
                        "Retard check",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[2]);

                if(optionChosen == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        }
        quitItem.addActionListener(new QuitListener());
    }
}
