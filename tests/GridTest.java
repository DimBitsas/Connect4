import org.junit.Before;

import static org.junit.Assert.*;

public class GridTest {

    private Grid grid;
    private GameConstants gameConstants;
    private Player playerOne;
    private Player playerTwo;

    @Before
    public void setUp(){
        grid = new Grid(6,7);
        gameConstants = GameConstants.getInstance();
        playerOne = new Player(gameConstants.getYELLOW());
        playerTwo = new Player(gameConstants.getRED());
    }

    @org.junit.Test
    public void isFull() throws InvalidColor,ArrayIndexOutOfBoundsException{
        Grid grid = new Grid(6,7);

        grid.createFullGrid(gameConstants.getYELLOW());

        assertTrue(grid.isFull());
    }

    @org.junit.Test
    public void isNotFull() {
        assertFalse(grid.isFull());
    }

    @org.junit.Test
    public void addPieceAtValidPosition() throws InvalidColor, ArrayIndexOutOfBoundsException{
        assertTrue(grid.addPiece(5,0, playerOne));
    }

    @org.junit.Test
    public void winningWithVerticalLineOfFour() throws InvalidColor, ArrayIndexOutOfBoundsException{
        Grid gridWithVerticalLineOfFour = new Grid(6,7);

        gridWithVerticalLineOfFour.addPiece(5,0, playerOne);
        gridWithVerticalLineOfFour.addPiece(4,0, playerOne);
        gridWithVerticalLineOfFour.addPiece(3,0, playerOne);
        gridWithVerticalLineOfFour.addPiece(2,0, playerOne);

        assertTrue(gridWithVerticalLineOfFour.isWinningPiece(2,0));
    }

    @org.junit.Test
    public void winningWithHorizontalLineOfFour() throws InvalidColor, ArrayIndexOutOfBoundsException{
        Grid gridWithHorizontalLineOfFour = new Grid(6,7);

        gridWithHorizontalLineOfFour.addPiece(5,0, playerOne);
        gridWithHorizontalLineOfFour.addPiece(5,1, playerOne);
        gridWithHorizontalLineOfFour.addPiece(5,2, playerOne);
        gridWithHorizontalLineOfFour.addPiece(5,3, playerOne);

        assertTrue(gridWithHorizontalLineOfFour.isWinningPiece(5,3));
    }

    @org.junit.Test
    public void winningWithDiagonalLeftLineOfFour() throws InvalidColor, ArrayIndexOutOfBoundsException{
        Grid gridWithDiagonalLeftLineOfFour = new Grid(6,7);

        gridWithDiagonalLeftLineOfFour.addPiece(5,0, playerTwo);
        gridWithDiagonalLeftLineOfFour.addPiece(5,1, playerTwo);
        gridWithDiagonalLeftLineOfFour.addPiece(5,2, playerTwo);

        gridWithDiagonalLeftLineOfFour.addPiece(4,0, playerOne);
        gridWithDiagonalLeftLineOfFour.addPiece(4,1, playerTwo);
        gridWithDiagonalLeftLineOfFour.addPiece(5,0, playerOne);

        gridWithDiagonalLeftLineOfFour.addPiece(3,0, playerTwo);

        gridWithDiagonalLeftLineOfFour.addPiece(3,1, playerOne);
        gridWithDiagonalLeftLineOfFour.addPiece(2,0, playerOne);
        gridWithDiagonalLeftLineOfFour.addPiece(4,2, playerOne);
        gridWithDiagonalLeftLineOfFour.addPiece(5,3, playerOne);

        assertTrue(gridWithDiagonalLeftLineOfFour.isWinningPiece(5, 3));
    }

    @org.junit.Test
    public void winningWithDiagonalRightLineOfFour() throws InvalidColor, ArrayIndexOutOfBoundsException{
        Grid gridWithDiagonalRightLineOfFour = new Grid(6,7);

        gridWithDiagonalRightLineOfFour.addPiece(5,1, playerOne);
        gridWithDiagonalRightLineOfFour.addPiece(5,2, playerTwo);
        gridWithDiagonalRightLineOfFour.addPiece(5,3, playerOne);

        gridWithDiagonalRightLineOfFour.addPiece(4,2, playerTwo);
        gridWithDiagonalRightLineOfFour.addPiece(4,3, playerTwo);

        gridWithDiagonalRightLineOfFour.addPiece(3,3, playerOne);

        gridWithDiagonalRightLineOfFour.addPiece(5,0, playerOne);
        gridWithDiagonalRightLineOfFour.addPiece(4,1, playerOne);
        gridWithDiagonalRightLineOfFour.addPiece(3,2, playerOne);
        gridWithDiagonalRightLineOfFour.addPiece(2,3, playerOne);

        assertTrue(gridWithDiagonalRightLineOfFour.isWinningPiece(2,3));
    }

    @org.junit.Test
    public void checkPositionWinningPiece() throws InvalidColor, ArrayIndexOutOfBoundsException{
        Grid grid = new Grid(6,7);

        grid.addPiece(5,0, playerOne);
        grid.addPiece(5,1, playerOne);
        grid.addPiece(5,2, playerOne);

        assertTrue(grid.checkPosition(5,3,gameConstants.getYELLOW()));
    }

    @org.junit.Test
    public void checkPositionNotWinningPiece() throws InvalidColor, ArrayIndexOutOfBoundsException{
        Grid grid = new Grid(6,7);

        grid.addPiece(5,0, playerOne);

        assertFalse(grid.checkPosition(5,1,gameConstants.getYELLOW()));
    }
}