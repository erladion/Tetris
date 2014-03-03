/**
 * Created by johja118 on 2014-02-07.
 */
public class TetrisTextView
{
    public static String convertToText(Board board){
        StringBuilder builder = new StringBuilder();
        for (int rowIndex = 0; rowIndex < board.getHeight(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < board.getWidth(); columnIndex++) {
                builder.append(getString(board.getValue(rowIndex, columnIndex)));
            }
            if(rowIndex != board.getHeight() - 1){
                builder.append("\n");
            }
        }
        if(board.getFalling() != null){
            Poly fallingBlock = board.getFalling();
            for (int polyY = 0; polyY < fallingBlock.getShape().length; polyY++) {
                for (int polyX = 0; polyX < fallingBlock.getShape()[0].length; polyX++) {
                    if(fallingBlock.getShape()[polyY][polyX] != null){
                        int position = (board.getFallingY()+polyY) * (board.getWidth()+1) + (board.getFallingX()+polyX);
                        builder.replace(position, position+1, getString(fallingBlock.getShape()[polyY][polyX]));

                    }
                }
            }
        }
        return builder.toString();
    }

    public static String getString(SquareType s){
        switch(s){
            case EMPTY:
                return "-";
            case I:
                return "§";
            case O:
                return "@";
            case T:
                return "/";
            case S:
                return "%";
            case Z:
                return "$";
            case J:
                return "&";
            case L:
                return "¤";
            case OUTSIDE:
                return "#";
            default:
                return " ";
        }
    }
}
