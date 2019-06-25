public class GameConstants {
    private final String HUMAN = "human";
    private final String COMPUTER = "computer";
    private final int YELLOW = 1;
    private final int RED = 2;

    private static GameConstants instance = new GameConstants();

    private GameConstants(){

    }

    public static GameConstants getInstance(){
        return instance;
    }

    public String getHUMAN() {
        return HUMAN;
    }

    public String getCOMPUTER() {
        return COMPUTER;
    }

    public int getYELLOW() {
        return YELLOW;
    }

    public int getRED() {
        return RED;
    }
}
