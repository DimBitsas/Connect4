public class Main {

    public static void main(String[] args) {
        Grid gameGrid = new Grid(6,7);

        try {
            gameGrid.addPiece(1,4, 2);
            gameGrid.addPiece(1,3, 2);
            gameGrid.addPiece(1,2, 2);
            gameGrid.addPiece(1,1, 2);
            System.out.println(gameGrid.isWinningPiece(1,1));
//            gameGrid.addPiece(5,0, 2);
//            gameGrid.addPiece(4,0, 2);
//            gameGrid.addPiece(3,0, 2);
//            gameGrid.addPiece(2,0, 2);
//            System.out.println(gameGrid.isWinningPiece(2,0));
        } catch (InvalidColor invalidColor) {
            invalidColor.getMessage();
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }

        gameGrid.printGrid();
    }
}
