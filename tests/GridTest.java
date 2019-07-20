import org.junit.Before;

import static org.junit.Assert.*;

public class GridTest {

    private Grid grid;
    private GameConstants gameConstants;
    private Player player;

    @Before
    public void setUp(){
        grid = new Grid(6,7);
        gameConstants = GameConstants.getInstance();
        player = new Player(gameConstants.getYELLOW());
    }

    @org.junit.Test
    public void isFull() {
        int[][] fullGridArray = new int[6][7];

        for(int i=0; i<6; i++){
            for(int j=0; j<7; j++){
                fullGridArray[i][j] = gameConstants.getYELLOW();
            }
        }

        grid.setGridArray(fullGridArray);

        assertTrue(grid.isFull());
    }

    @org.junit.Test
    public void isNotFull() {
        assertFalse(grid.isFull());
    }

    @org.junit.Test
    public void addPieceAtValidPosition() throws InvalidColor, ArrayIndexOutOfBoundsException{
        assertTrue(grid.addPiece(5,0, player));
    }

    @org.junit.Test
    public void winningWithVerticalLineOfFour() throws InvalidColor, ArrayIndexOutOfBoundsException{
        Grid gridWithVerticalLineOfFour = new Grid(6,7);
        Player player = new Player(gameConstants.getYELLOW());

        gridWithVerticalLineOfFour.addPiece(5,0,player);
        gridWithVerticalLineOfFour.addPiece(4,0,player);
        gridWithVerticalLineOfFour.addPiece(3,0,player);
        gridWithVerticalLineOfFour.addPiece(2,0,player);

        assertTrue(gridWithVerticalLineOfFour.isWinningPiece(2,0));
    }

    @org.junit.Test
    public void winningWithHorizontalLineOfFour() throws InvalidColor, ArrayIndexOutOfBoundsException{
        Grid gridWithHorizontalLineOfFour = new Grid(6,7);
        Player player = new Player(gameConstants.getYELLOW());

        gridWithHorizontalLineOfFour.addPiece(5,0,player);
        gridWithHorizontalLineOfFour.addPiece(5,1,player);
        gridWithHorizontalLineOfFour.addPiece(5,2,player);
        gridWithHorizontalLineOfFour.addPiece(5,3,player);

        assertTrue(gridWithHorizontalLineOfFour.isWinningPiece(5,3));
    }

    @org.junit.Test
    public void winningWithDiagonalLeftLineOfFour() throws InvalidColor, ArrayIndexOutOfBoundsException{
        Grid gridWithDiagonalLeftLineOfFour = new Grid(6,7);
        Player player = new Player(gameConstants.getYELLOW());
        Player otherPlayer = new Player(gameConstants.getRED());

        gridWithDiagonalLeftLineOfFour.addPiece(5,0,otherPlayer);
        gridWithDiagonalLeftLineOfFour.addPiece(5,1,otherPlayer);
        gridWithDiagonalLeftLineOfFour.addPiece(5,2,otherPlayer);

        gridWithDiagonalLeftLineOfFour.addPiece(4,0,player);
        gridWithDiagonalLeftLineOfFour.addPiece(4,1,otherPlayer);
        gridWithDiagonalLeftLineOfFour.addPiece(5,0,player);

        gridWithDiagonalLeftLineOfFour.addPiece(3,0,otherPlayer);

        gridWithDiagonalLeftLineOfFour.addPiece(3,1,player);
        gridWithDiagonalLeftLineOfFour.addPiece(2,0,player);
        gridWithDiagonalLeftLineOfFour.addPiece(4,2,player);
        gridWithDiagonalLeftLineOfFour.addPiece(5,3,player);

        assertTrue(gridWithDiagonalLeftLineOfFour.isWinningPiece(5, 3));
    }

    @org.junit.Test
    public void winningWithDiagonalRightLineOfFour() throws InvalidColor, ArrayIndexOutOfBoundsException{
        Grid gridWithDiagonalRightLineOfFour = new Grid(6,7);
        Player player = new Player(gameConstants.getYELLOW());
        Player otherPlayer = new Player(gameConstants.getRED());


        gridWithDiagonalRightLineOfFour.addPiece(5,1,player);
        gridWithDiagonalRightLineOfFour.addPiece(5,2,otherPlayer);
        gridWithDiagonalRightLineOfFour.addPiece(5,3,player);

        gridWithDiagonalRightLineOfFour.addPiece(4,2,otherPlayer);
        gridWithDiagonalRightLineOfFour.addPiece(4,3,otherPlayer);

        gridWithDiagonalRightLineOfFour.addPiece(3,3,player);

        gridWithDiagonalRightLineOfFour.addPiece(5,0,player);
        gridWithDiagonalRightLineOfFour.addPiece(4,1,player);
        gridWithDiagonalRightLineOfFour.addPiece(3,2,player);
        gridWithDiagonalRightLineOfFour.addPiece(2,3,player);

        assertTrue(gridWithDiagonalRightLineOfFour.isWinningPiece(2,3));
    }

    @org.junit.Test
    public void checkPositionWinningPiece() throws InvalidColor, ArrayIndexOutOfBoundsException{
        Grid grid = new Grid(6,7);
        Player player = new Player(gameConstants.getYELLOW());

        grid.addPiece(5,0,player);
        grid.addPiece(5,1,player);
        grid.addPiece(5,2,player);

        assertTrue(grid.checkPosition(5,3,gameConstants.getYELLOW()));
    }

    @org.junit.Test
    public void checkPositionNotWinningPiece() throws InvalidColor, ArrayIndexOutOfBoundsException{
        Grid grid = new Grid(6,7);
        Player player = new Player(gameConstants.getYELLOW());

        grid.addPiece(5,0,player);

        assertFalse(grid.checkPosition(5,1,gameConstants.getYELLOW()));
    }
}