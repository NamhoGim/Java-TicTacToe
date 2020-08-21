import java.util.Scanner;

public class TicTacToe implements Simulatable, Winnable, Playable, Printable {
    private static final Scanner scan = new Scanner(System.in);
    private static final TicTacToe ticTacToe = new TicTacToe();
    private static final Position[] directions = new Position[]{new Position(0, -1), new Position(-1, -1),
                                                                new Position(-1, 0), new Position(1, -1)};

    static boolean isQuit = false;
    static char[][] board = new char[3][3];

    private Player player1, player2;
    private Player currPlayer;
    private Position lastPos;
    private int numWin;

    private TicTacToe() {}

    public static TicTacToe getInstance() { return ticTacToe; }

    @Override
    public void play(Player player) {
        currPlayer = player;
        System.out.println(currPlayer.getName() + "님의 차레 입니다.");
        printStatus();
        player.getKeyboardInput();
        if (isQuit) {
            isFinished();
            reset();
            return;
        }

        if (!player.isMoreMoves(board)) {
            printStatus();
            System.out.println("무승부 입니다.");
            reset();
            return;
        }

        if (winCondition(lastPos)) {
            printStatus();
            System.out.println(player.getName() + "의 승리 !!!");
            player.setNumWin(player.getNumWin()+1);
            System.out.println(player.getNumWin() + "번 이겼다!");
            reset();
        }

        if (isFinished()) {
            reset();
            isQuit = true;
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
        String arg;
        boolean inValid = true;
        System.out.println("몇 선승 플레이 하시겠습니까?");
        while (inValid) {
            arg = scan.nextLine();
            if (arg.matches("^[0-9]+$")) {
                numWin = Integer.parseInt(arg);
                if (numWin < 1) {
                    System.out.println("무조건 한번 이상은 이겨야 합니다.");
                    continue;
                }
                inValid = false;
            } else {
                System.out.println("올바른 입력이 아닙니다. 다시 입력해 주세요.");
            }
        }

        for (int i = 1; i <= 2; i++) {
            System.out.println("Player" + i + " 설정 " + "AI로 진행 하시겠습니까? [y/n]: ");
            boolean isAI = false;
            inValid = true;
            while (inValid) {
                arg = scan.nextLine();
                if (arg.matches("^[yY]$")) {
                    isAI = true;
                    inValid = false;
                } else if (arg.matches("^[nN]$")) {
                    inValid = false;
                } else {
                    System.out.println("잘못된 입력입니다. 다시 입력해 주세요 [y/n]");
                }
            }

            if (i == 1) {
                if (isAI) {
                    setPlayer1(new AIPlayer("P1", 'O'));
                } else {
                    setPlayer1(new HumanPlayer("P1", 'O'));
                }
            } else {
                if (isAI) {
                    setPlayer2(new AIPlayer("P2", 'X'));
                } else {
                    setPlayer2(new HumanPlayer("P2", 'X'));
                }
            }
        }
        lastPos = new Position();
    }

    @Override
    public boolean isFinished() {
        if (player1.getNumWin() > player2.getNumWin() && player1.getNumWin() == numWin) {
            System.out.println(player1.getName() + "님의 최종 승리!!!");
            return true;
        } else if (player1.getNumWin() < player2.getNumWin() && player2.getNumWin() == numWin) {
            System.out.println(player2.getName() + "님의 최종 승리!!!");
            return true;
        }
        return false;
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

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer1() { return player1; }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player getPlayer2() { return player2; }

    public Player getCurrPlayer() {
        return currPlayer;
    }

    public Position getLastPos() { return lastPos; }
    public void setLastPos(int x, int y) {
        lastPos.setX(x);
        lastPos.setY(y);
    }
}
