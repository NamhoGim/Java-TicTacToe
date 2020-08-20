public class TicTacToe implements Simulatable, Winnable, Playable, Printable {
    private static final TicTacToe ticTacToe = new TicTacToe();
    private static final Position[] directions = new Position[]{new Position(0, -1), new Position(-1, -1),
                                                                new Position(-1, 0), new Position(1, -1)};

    static boolean isQuit = false;
    static char[][] board = new char[3][3];

    private Player player1, player2;

    private TicTacToe() {}

    public static TicTacToe getInstance() { return ticTacToe; }

    @Override
    public void play(Player player, Position pos) {
        System.out.println(player.getName() + "님의 차레 입니다.");
        printStatus();
        player.getKeyboardInput();
        if (isQuit) {
            isFinished();
            return;
        }

        if (winCondition(pos)) {
            printStatus();
            System.out.println(player.getName() + "의 승리 !!!");
            player.setNumWin(player.getNumWin()+1);
            System.out.println(player.getNumWin() + "번 이겼다!");
            reset();
        }
    }

    private boolean winCondition(Position p) {
        return checkSequence(p);
    }

    private boolean checkSequence(Position p) {
        int maxCount = 0;
        int px = p.getX(), py = p.getY();
        char stone = board[px][py];

        for (Position d : directions) {
            int count = 1;
            int dx = d.getX(), dy = d.getY();
            for (int cx = px + dx, cy = py + dy; boundaryCheck(cx, cy) &&
                    (stone == board[cx][cy]); cx += dx, cy += dy) {
                if (stone == board[cx][cy]) count++;
            }

            for (int cx = px - dx, cy = py - dy; boundaryCheck(cx, cy) &&
                    (stone == board[cx][cy]); cx -= dx, cy -= dy) {
                if (stone == board[cx][cy]) count++;
            }
            maxCount = Math.max(maxCount, count);
        }
        return maxCount == 3;
    }


    @Override
    public void printStatus() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int j = 0; j <= board[0].length; j++) {
            if (j == 0) {
                stringBuilder.append("   ");
            } else {
                stringBuilder.append(String.format("%2d", j-1)).append(" ");
            }
        }
        stringBuilder.append('\n');

        for (int i = 0; i < board.length; i++) {
            stringBuilder.append(String.format("%2d", i)).append(" ");
            for (int j = 0; j < board[0].length; j++) {
                stringBuilder.append(" ").append(board[i][j]).append(" ");
            }
            stringBuilder.append('\n');
        }
        System.out.print(stringBuilder.toString());
    }

    @Override
    public void initialize() {
        System.out.println("Player1의 이름을 입력하세요: ");
        setPlayer1(new Player());

        System.out.println("Player2의 이름을 입력하세요: ");
        setPlayer2(new Player());
    }

    @Override
    public void isFinished() {

    }

    @Override
    public void reset() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = '.';
            }
        }
    }

    @Override
    public Player getWinner() {
        return null;
    }

    public boolean boundaryCheck(int x, int y) {
        return ((x >= 0 && x < board.length) && (y >= 0 && y < board[0].length));
    }

    public void setPlayer1(Player player1) { ticTacToe.player1 = player1; }

    public Player getPlayer1() { return player1; }

    public void setPlayer2(Player player2) { this.player2 = player2; }

    public Player getPlayer2() { return player2; }
}
