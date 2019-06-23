import java.util.Scanner;

public class Connect4 {
    public static final String HUMAN = "human";
    public static final String COMPUTER = "computer";
    public static final int YELLOW = 1;
    public static final int RED = 2;
    public static boolean gameWon;

    public static void main(String[] args) {
        int counter = 1;
        final int GRID_HEIGHT = 6;
        final int GRID_WIDTH = 7;
        final String CHOOSE_HUMAN_OR_COMPUTER_PROMPT = "Do you want to play against " +
                "a human(Type human) or against a computer(Type computer)";
        final String PLAYER1_TURN = "Player1 it's your turn now!!";
        final String PLAYER2_TURN = "Player2 it's your turn now!!";
        String userChoice;
        Scanner input;
        Grid gameGrid = new Grid(GRID_HEIGHT,GRID_WIDTH);
        Player player1 = new Player(YELLOW);
        gameWon = false;

        do{
            System.out.println(CHOOSE_HUMAN_OR_COMPUTER_PROMPT);
            input = new Scanner(System.in);
            userChoice = input.next().toLowerCase();
        }while ((!userChoice.equals(HUMAN) && !userChoice.equals(COMPUTER) ));

        Player player2 = new Player(RED, userChoice);

        do{
            gameGrid.printGrid();
            if(counter % 2 == 0){
                System.out.println(PLAYER2_TURN);
                player2.play(gameGrid);
            }
            else {
                System.out.println(PLAYER1_TURN);
                player1.play(gameGrid);
            }
            counter++;
        }while (!gameGrid.isFull() && !gameWon);

        if(gameWon){
            System.out.println("Congratulations you have won!!");
        }
        else{
            System.out.println("Draw!!The game grid is full!!");
        }

        gameGrid.printGrid();
    }
}
