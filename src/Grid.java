public class Grid {
    private final int NO_COLOR = 0;
    private final int NUMBER_OF_WINNING_PIECES = 4;
    private final int WINDOW = 4;
    private int width;
    private int height;
    private int[][] gridArray;
    private int[] piecesCurrentHeight;

    public Grid(int height, int width){
        this.height = height;
        this.width = width;
        piecesCurrentHeight = new int[this.width];
        gridArray = new int[this.height][this.width];
    }

    /**
     * Print Grid
     */
    public void printGrid(){
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                System.out.print(gridArray[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Check if it is possible to move
     * a piece at the provided position
     *
     * @param x position
     * @param y position
     * @return true valid, false invalid
     */
    private boolean isValidMove(int x, int y) throws ArrayIndexOutOfBoundsException{

        return ((gridArray[x][y] == NO_COLOR && x==(height-1)) ||
                (gridArray[x][y] == NO_COLOR && gridArray[x+1][y] != NO_COLOR ));
    }

    /**
     * Add piece at the (x,y) position
     * Update height
     *
     * @param x position
     * @param y position
     * @param color piece color
     * @throws InvalidColor, ArrayIndexOutOfBoundsException
     * @return true piece added, false piece not added
     */
    public boolean addPiece(int x, int y, int color, Player player) throws InvalidColor, ArrayIndexOutOfBoundsException{
        GameConstants constants = GameConstants.getInstance();

        if(!isValidMove(x,y)){
            return false;
        }

        if(color != constants.getYELLOW() && color != constants.getRED()){
            throw new InvalidColor();
        }

        gridArray[x][y] = color;
        piecesCurrentHeight[y]++;

        if(isWinningPiece(x,y)){
            player.setWinner(true);
        }

        return true;
    }

    /**
     * Check if game grid is full
     *
     * @return true game grid full, false not full
     */
    public boolean isFull(){
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                if(gridArray[i][j] == NO_COLOR){
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Checks if the piece at (x,y) position
     * created a line of four
     * (four pieces with the same color)
     *
     * @param x position
     * @param y position
     * @return true winning, false not winning
     */
    public boolean isWinningPiece(int x, int y){
        boolean result = false;

        if(gridArray[x][y] == NO_COLOR){
            return false;
        }

        try{
            if(this.isWinningPieceVertical(x, y) ||
                    this.isWinningPieceHorizontally(x, y) ||
                    this.isWinningPieceDiagonalLeft(x,y) ||
                    this.isWinningPieceDiagonalRight(x,y))
            {
                result = true;
            }
        }
        catch (ArrayIndexOutOfBoundsException e){
            result = false;
        }

        return result;
    }

    private boolean isWinningPieceVertical(int x, int y) throws ArrayIndexOutOfBoundsException{
        int count = 1;
        int color = gridArray[x][y];

        if(piecesCurrentHeight[y] < NUMBER_OF_WINNING_PIECES){
            return false;
        }

        for (int i=1; i<NUMBER_OF_WINNING_PIECES; i++){
            if(gridArray[x+i][y] == color){
                count++;
            }
        }

        return (count == NUMBER_OF_WINNING_PIECES);
    }

    private boolean isWinningPieceHorizontally(int x, int y) throws ArrayIndexOutOfBoundsException{
        int color = gridArray[x][y];

        for(int j = 0; j< WINDOW; j++) {
            int count = 0;
            int start = y - 3 + j;
            int end = y+j;
            for (int i = start; i <= end; i++) {
                if (i < 0 || i > (width - 1)) {
                    break;
                }

                if (gridArray[x][i] == color) {
                    count++;
                }
            }
            if(count == NUMBER_OF_WINNING_PIECES){
                return true;
            }
        }

        return false;
    }

    private boolean isWinningPieceDiagonalLeft(int x, int y) throws ArrayIndexOutOfBoundsException{
        int color = gridArray[x][y];

        for(int j = 0; j< WINDOW; j++) {
            int count = 0;
            int xcur = x-3+j;
            int ycur = y-3+j;

            for(int i=0; i< WINDOW; i++){
                if (xcur < 0 || xcur >= height) {
                    break;
                }
                if(ycur < 0 || ycur >= width ) {
                    break;
                }

                if (gridArray[xcur][ycur] == color) {
                    count++;
                }

                xcur++;
                ycur++;
            }

            if(count == NUMBER_OF_WINNING_PIECES){
                return true;
            }
        }

        return false;
    }

    private boolean isWinningPieceDiagonalRight(int x, int y) throws ArrayIndexOutOfBoundsException{
        int color = gridArray[x][y];

        for(int j = 0; j< WINDOW; j++) {
            int count = 0;
            int xcur = x-3+j;
            int ycur = y+3+j;

            for(int i=0; i< WINDOW; i++){
                if (xcur < 0 || xcur >= height) {
                    break;
                }
                if(ycur < 0 || ycur >= width){
                    break;
                }

                if (gridArray[xcur][ycur] == color) {
                    count++;
                }

                xcur++;
                ycur--;
            }

            if(count == NUMBER_OF_WINNING_PIECES){
                return true;
            }
        }

        return false;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * Check if adding piece with the specific
     * Color at (x,y) position creates a line of four
     * It does not add the piece at the grid
     *
     * @param x position
     * @param y position
     * @param color piece color
     * @return true piece at x,y creates line of four, false it does not
     */
    public boolean checkPosition(int x, int y, int color) throws ArrayIndexOutOfBoundsException{

        if(color == NO_COLOR ||
                gridArray[x][y] != NO_COLOR) {
            return false;
        }

        gridArray[x][y] = color;
        piecesCurrentHeight[y]++;

        boolean result = isWinningPiece(x,y);

        gridArray[x][y] = NO_COLOR;//Remove color
        piecesCurrentHeight[y]--;

        return result;
    }
}
