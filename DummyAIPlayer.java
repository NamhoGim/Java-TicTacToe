import java.util.Random;

public class DummyAIPlayer implements Player {
    static final Position[] positions;

    static {
        positions = new Position[]{new Position(0, 0), new Position(0, 1), new Position(0, 2),
                                   new Position(1, 0), new Position(1, 1), new Position(1, 2),
                                   new Position(2, 0), new Position(2, 1), new Position(2, 2)};
    }

    private final String name;
    private char stone;
    private int numWin;

    public DummyAIPlayer(String name, char stone) {
        this.name = name;
        this.stone = stone;
        this.numWin = 0;
    }

    @Override
    public String getName() { return name; }

    @Override
    public int getNumWin() { return numWin; }

    @Override
    public void setNumWin(int val) { numWin = val; }

    @Override
    public char getStone() { return stone; }

    @Override
    public void setStone(char stone) { this.stone = stone; }

    @Override
    public String toString() {
        return "DummyAIPlayer{" +
                "name='" + name + '\'' +
                ", stone=" + stone +
                ", numWin=" + numWin +
                '}';
    }

    @Override
    public void getKeyboardInput() {
        Position randomPosition = getRandomPosition();
        int x = randomPosition.getX(), y = randomPosition.getY();
        TicTacToe.board[x][y] = stone;
        TicTacToe ticTacToe = TicTacToe.getInstance();
        ticTacToe.setLastPos(x, y);
    }

    private Position getRandomPosition() {
        Random r = new Random();
        int x, y;
        do{
            int i = r.nextInt(10);
            x = positions[i].getX();
            y = positions[i].getY();
        }while(TicTacToe.board[x][y] != '.');
        return new Position(x, y);
    }
}


