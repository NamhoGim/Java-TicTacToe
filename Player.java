import java.util.Scanner;

public class Player implements Inputtable {
    private static final Scanner scan = new Scanner(System.in);
    static Position lastPos = new Position();

    protected final String name;
    protected char stone;
    protected int numWin;

    public Player() { this(scan.nextLine()); }

    public Player(String name) {
        this.name = name;
        this.numWin = 0;
    }

    public String getName() { return name; }

    public int getNumWin() { return numWin; }

    public void setNumWin(int val) { numWin = val; }

    public char getStone() { return stone; }

    public void setStone(char stone) { this.stone = stone; }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", stone=" + stone +
                ", numWin=" + numWin +
                '}';
    }

    @Override
    public void getKeyboardInput() {
        boolean isInvalid = false;
        int x, y;

        do {
            if (isInvalid) {
                System.out.println("가능한 위치가 아닙니다. 다시 입력해 주세요.");
            }

            String line = scan.nextLine();
            String[] split = line.split(" ");
            if ((split[0].matches("^[qQ]$"))) {
                TicTacToe.isQuit = true;
                return;
            } else {
                if (split.length > 2) {
                    isInvalid = true;
                    continue;
                } else if (split.length == 1) {
                    isInvalid = true;
                    continue;
                } else if (!split[0].matches("[0-9]+") || !split[1].matches("[0-9]+")) {
                    isInvalid = true;
                    continue;
                }

                x = Integer.parseInt(split[0]);
                y = Integer.parseInt(split[1]);
                if (!TicTacToe.getInstance().boundaryCheck(x, y)) {
                    isInvalid = true;
                    continue;
                }

                if (TicTacToe.board[x][y] != '.') {
                    isInvalid = true;
                    continue;
                }

                TicTacToe.board[x][y] = (this == TicTacToe.getInstance().getPlayer1()) ? 'O' : 'X';
                lastPos.setX(x);
                lastPos.setY(y);
                isInvalid = false;
            }
        } while(isInvalid);
    }
}
