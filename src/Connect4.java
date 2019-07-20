import java.util.Scanner;

public class Connect4 {
    public static void main(String[] args) {
        int counter = 1;
        final String CHOOSE_HUMAN_OR_COMPUTER_PROMPT = "Do you want to play against " +
                "a human(Type human) or against a computer(Type computer)";
        final String PLAYER1_TURN = "Player1 it's your turn now!!";
        final String PLAYER2_TURN = "Player2 it's your turn now!!";
        String userChoice;
        Scanner input;
        GameConstants constants = GameConstants.getInstance();
        Grid gameGrid = new Grid(constants.getGRID_HEIGHT(),constants.getGRID_WIDTH());
        Player player1 = new Player(constants.getYELLOW());

        do{
            System.out.println(CHOOSE_HUMAN_OR_COMPUTER_PROMPT);
            input = new Scanner(System.in);
            userChoice = input.next().toLowerCase();
        }while ((!userChoice.equals(constants.getHUMAN()) && !userChoice.equals(constants.getCOMPUTER()) ));

        Player player2 = new Player(constants.getRED(), userChoice);

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
        }while (!gameGrid.isFull() &&
                !player1.isWinner() &&
                !player2.isWinner());

        if(player1.isWinner()){
            System.out.println("Congratulations Player1 you have won!!");
        }
        else if(player2.isWinner()){
            System.out.println("Congratulations Player2 you have won!!");
        }
        else {
            System.out.println("Draw!!The game grid is full!!");
        }

        gameGrid.printGrid();
    }
}
