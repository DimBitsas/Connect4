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
                if(grid.addPiece(xpos,ypos,this)) {
                    break;
                }
                System.out.println("Invalid position");
            } catch (InvalidColorException | ArrayIndexOutOfBoundsException e) {
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
                            grid.addPiece(i,j,this)) {
                        return true;
                    }
                }
                catch (InvalidColorException | ArrayIndexOutOfBoundsException e){
                    System.out.println(e.getMessage());
                    return false;
                }
            }
        }

        return false;
    }

    /**
     * @param grid Game grid
     * @return true if piece was added at random position, false it ws not added
     * @throws InvalidColorException Exception
     * @throws ArrayIndexOutOfBoundsException Exception
     */
    private boolean isPieceAddedAtRandomPosition(Grid grid) throws InvalidColorException, ArrayIndexOutOfBoundsException{
        for (int i = 0; i < grid.getHeight(); i++) {
            for (int j = 0; j < grid.getWidth(); j++) {
                if (grid.addPiece(i, j, this)) {
                    return true;
                }
            }
        }

        return false;
    }

    private void computerPlay(Grid grid) throws InvalidColorException, ArrayIndexOutOfBoundsException, ComputerMoveException{
        GameConstants gameConstants = GameConstants.getInstance();

        if(scanGridForWinningPosition(grid,color) ||
                scanGridForWinningPosition(grid, gameConstants.getYELLOW()) ||
                isPieceAddedAtRandomPosition(grid)){
            System.out.println("Computer move is completed!!\n");
        }
        else{
            throw new ComputerMoveException("Error!!\nComputer failed to move piece");
        }
    }

    public void play(Grid grid) {
        if (controlledBy.equals("human")) {
            humanPlay(grid);
        } else {
            try {
                computerPlay(grid);
            }catch (ComputerMoveException | InvalidColorException | ArrayIndexOutOfBoundsException e){
                System.out.println(e.getMessage());
                System.exit(-1);
            }
        }
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }

    public int getColor() {
        return color;
    }
}
