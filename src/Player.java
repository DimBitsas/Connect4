import java.util.Scanner;

public class Player {
    private int color;
    private String controlledBy;
    private boolean isWinner;

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
        final String PROVIDE_PIECE_POSITION_COLOR = "Please provide Piece position (x,y)";

        while(true) {
            System.out.println(PROVIDE_PIECE_POSITION_COLOR);
            Scanner input = new Scanner(System.in);

            xpos = input.nextInt();
            ypos = input.nextInt();
            try {
                if(grid.addPiece(xpos, ypos, color, this)) {
                    break;
                }
                System.out.println("Invalid position");
            } catch (InvalidColor | ArrayIndexOutOfBoundsException e) {
                System.out.println("Could not add piece at "+xpos+" "+ypos);
            }

        }
    }

    /**
     * Scan grid for winning position (Player or opponent)
     * In case that a winning position is found then the piece
     * is added at the specific position
     *
     * @param grid Game Grid
     * @param scanColor Color(Player or opponent)
     * @return true winning position found, not found
     */
    private boolean scanGridForWinningPosition(Grid grid, int scanColor){

        for(int i=0; i<grid.getHeight(); i++){
            for(int j=0; j<grid.getWidth(); j++) {
                try{
                    if(grid.checkPosition(i,j,scanColor) &&
                            grid.addPiece(i,j,color,this)) {
                        return true;
                    }
                }
                catch (InvalidColor | ArrayIndexOutOfBoundsException e){
                    System.out.println(e.getMessage());
                    return false;
                }
            }
        }

        return false;
    }

    private void computerPlay(Grid grid) throws InvalidColor, ArrayIndexOutOfBoundsException{

        GameConstants gameConstants = GameConstants.getInstance();

        if(scanGridForWinningPosition(grid,color) ||
                scanGridForWinningPosition(grid, gameConstants.getYELLOW())){
            return;
        }

        for(int i=0; i<grid.getHeight(); i++){
            for(int j=0; j<grid.getWidth(); j++){
                try {
                    if(grid.addPiece(i,j,color,this)) {
                        return;
                    }
                }
                catch (InvalidColor | ArrayIndexOutOfBoundsException e){
                    throw e;
                }
            }
        }

        System.out.println("Computer play error!!No move!!");
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

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }
}
