import java.util.Scanner;

public interface Inputtable {
    Scanner scan = new Scanner(System.in);
    default void getKeyboardInput() {
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

                TicTacToe ticTacToe = TicTacToe.getInstance();
                TicTacToe.board[x][y] = ticTacToe.getCurrPlayer().getStone();
                ticTacToe.setLastPos(x, y);
                isInvalid = false;
            }
        } while(isInvalid);
    }
}
