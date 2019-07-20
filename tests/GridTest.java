import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GridTest {

    private Grid grid;
    private GameConstants gameConstants;
    private Player playerOne;
    private Player playerTwo;

    @Before
    public void setUp(){
        gameConstants = GameConstants.getInstance();
        grid = new Grid(gameConstants.getGRID_HEIGHT(),gameConstants.getGRID_WIDTH());
        playerOne = new Player(gameConstants.getYELLOW());
        playerTwo = new Player(gameConstants.getRED());
    }

    @org.junit.Test
    public void isFull() {
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

    @Test
    public void winningWithVerticalLineOfFour() throws InvalidColor, ArrayIndexOutOfBoundsException{
        grid.addPiece(5,0, playerOne);
        grid.addPiece(4,0, playerOne);
        grid.addPiece(3,0, playerOne);
        grid.addPiece(2,0, playerOne);

        assertTrue(grid.isWinningPiece(2,0));
    }

    @Test
    public void winningWithHorizontalLineOfFour() throws InvalidColor, ArrayIndexOutOfBoundsException{
        grid.addPiece(5,0, playerOne);
        grid.addPiece(5,1, playerOne);
        grid.addPiece(5,2, playerOne);
        grid.addPiece(5,3, playerOne);

        assertTrue(grid.isWinningPiece(5,3));
    }

    @Test
    public void winningWithDiagonalLeftLineOfFour() throws InvalidColor, ArrayIndexOutOfBoundsException{
        grid.addPiece(5,0, playerTwo);
        grid.addPiece(5,1, playerTwo);
        grid.addPiece(5,2, playerTwo);

        grid.addPiece(4,0, playerOne);
        grid.addPiece(4,1, playerTwo);
        grid.addPiece(5,0, playerOne);

        grid.addPiece(3,0, playerTwo);

        grid.addPiece(3,1, playerOne);
        grid.addPiece(2,0, playerOne);
        grid.addPiece(4,2, playerOne);
        grid.addPiece(5,3, playerOne);

        assertTrue(grid.isWinningPiece(5, 3));
    }

    @Test
    public void winningWithDiagonalRightLineOfFour() throws InvalidColor, ArrayIndexOutOfBoundsException{
        grid.addPiece(5,1, playerOne);
        grid.addPiece(5,2, playerTwo);
        grid.addPiece(5,3, playerOne);

        grid.addPiece(4,2, playerTwo);
        grid.addPiece(4,3, playerTwo);

        grid.addPiece(3,3, playerOne);

        grid.addPiece(5,0, playerOne);
        grid.addPiece(4,1, playerOne);
        grid.addPiece(3,2, playerOne);
        grid.addPiece(2,3, playerOne);

        assertTrue(grid.isWinningPiece(2,3));
    }

    @org.junit.Test
    public void checkPositionWinningPiece() throws InvalidColor, ArrayIndexOutOfBoundsException{
        grid.addPiece(5,0, playerOne);
        grid.addPiece(5,1, playerOne);
        grid.addPiece(5,2, playerOne);

        assertTrue(grid.checkPosition(5,3,gameConstants.getYELLOW()));
    }

    @org.junit.Test
    public void checkPositionNotWinningPiece() throws InvalidColor, ArrayIndexOutOfBoundsException{
        grid.addPiece(5,0, playerOne);

        assertFalse(grid.checkPosition(5,1,gameConstants.getYELLOW()));
    }
}