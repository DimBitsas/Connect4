import java.util.Scanner;

public class Player {
    private int color;
    private String controlledBy;
    private boolean isWinner;
    private final String PROVIDE_PIECE_POSITION_COLOR = "Please provide Piece position (x,y)";

    public Player(int color, String controlledBy) {
        this.color = color;
        this.controlledBy = controlledBy;
        isWinner = false;
    }

    public Player(int color) {
        this.color = color;
        this.controlledBy = GameConstants.getInstance().getHUMAN();
        isWinner = false;
    }

    private void humanPlay(Grid grid){
        int xpos, ypos;
        boolean continuePrompting;

        do {
            System.out.println(PROVIDE_PIECE_POSITION_COLOR);
            Scanner input = new Scanner(System.in);

            xpos = input.nextInt();
            ypos = input.nextInt();
            if(grid.isValidMove(xpos, ypos)){
                try {
                    grid.addPiece(xpos, ypos, color, this);
                    continuePrompting = false;
                } catch (InvalidColor invalidColor) {
                    continuePrompting = true;
                }
            }
            else {
                continuePrompting = true;
            }

        }while (continuePrompting);
    }

    private void computerPlay(Grid grid) throws InvalidColor, ArrayIndexOutOfBoundsException{
        for(int i=0; i<grid.getHeight(); i++){
            for(int j=0; j<grid.getWidth(); j++){
                if(grid.isWinningPiece(i,j) &&
                        grid.getColor(i,j)==color)
                {
                    try {
                        grid.addPiece(i,j,color,this);
                        return;
                    } catch (InvalidColor e) {
                        throw e;
                    }
                    catch (ArrayIndexOutOfBoundsException e){
                        throw e;
                    }
                }
            }
        }

        for(int i=0; i<grid.getHeight(); i++){
            for(int j=0; j<grid.getWidth(); j++){
                if(grid.isWinningPiece(i,j)){
                    try {
                        grid.addPiece(i,j,color,this);
                        return;
                    } catch (InvalidColor e) {
                        throw e;
                    }
                    catch (ArrayIndexOutOfBoundsException e){
                        throw e;
                    }
                }
            }
        }

        for(int i=0; i<grid.getHeight(); i++){
            for(int j=0; j<grid.getWidth(); j++){
                try {
                    if (grid.isPositionEmpty(i, j)){
                        if(i == (grid.getHeight()-1)){
                            grid.addPiece(i,j,color,this);
                            return;
                        }
                        else{
                            if(!grid.isPositionEmpty(i+1, j)){
                                grid.addPiece(i,j,color,this);
                                return;
                            }
                        }
                    }
                }
                catch (InvalidColor e){
                    throw e;
                }
                catch (ArrayIndexOutOfBoundsException e){
                    throw e;
                }
            }
        }

        System.out.println("Computer play error!!No move implemented");
    }

    public void play(Grid grid) {
        if (controlledBy.equals("human")) {
            humanPlay(grid);
        } else {
            try {
                computerPlay(grid);
            } catch (InvalidColor invalidColor) {
                System.out.println("Error: Invalid color");
            }
            catch (ArrayIndexOutOfBoundsException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public int getColor() {
        return color;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }
}
