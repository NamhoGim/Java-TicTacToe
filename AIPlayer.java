public class AIPlayer extends Player implements Inputtable {
    public AIPlayer() {
        super();
    }

    @Override
    public String toString() {
        return "AIPlayer{" +
                "name='" + name + '\'' +
                ", stone=" + stone +
                ", numWin=" + numWin +
                '}';
    }

    @Override
    public void getKeyboardInput() {
        Position optPosition = optimalMove(TicTacToe.board);
        int x = optPosition.getX(), y = optPosition.getY();
        TicTacToe.board[x][y] = stone;
        lastPos.setX(x);
        lastPos.setY(y);
    }

    private int evaluate(char[][] board) {
        char other = (stone == 'O') ? 'X' : 'O';

        for (int r = 0; r < board.length; r++) {
            if (board[r][0] == board[r][1] && board[r][1] == board[r][2]) {
                if (board[r][0] == stone) {
                    return 10;
                } else if (board[r][0] == other){
                    return -10;
                }
            }
        }

        for (int c = 0; c < board[0].length; c++) {
            if (board[0][c] == board[1][c] && board[1][c] == board[2][c]) {
                if (board[0][c] == stone) {
                    return 10;
                } else if (board[0][c] == other) {
                    return -10;
                }
            }
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == stone) {
                return 10;
            } else if (board[0][0] == other) {
                return -10;
            }
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == stone) {
                return 10;
            } else if (board[0][2] == other) {
                return -10;
            }
        }

        return 0;
    }

    private int miniMax(char[][] board, int depth, boolean isMax) {
        int value = evaluate(board);
        if (value != 0) {
            return value;
        }

        if (!isMoreMoves(board)) {
            return 0;
        }

        int optValue;
        if (isMax) {
            optValue = Integer.MIN_VALUE;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == '.') {
                        board[i][j] = stone;
                        optValue = Math.max(optValue, miniMax(board, depth+1, false));
                        board[i][j] = '.';
                    }
                }
            }
        } else {
            optValue = Integer.MAX_VALUE;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == '.') {
                        board[i][j] = (stone == 'O') ? 'X' : 'O';
                        optValue = Math.min(optValue, miniMax(board, depth+1, true));
                        board[i][j] = '.';
                    }
                }
            }
        }

        return optValue;
    }

    private Position optimalMove(char[][] board) {
        int optValue = Integer.MIN_VALUE;
        Position result = new Position();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    board[i][j] = stone;
                    int candiVal = miniMax(board, 0, false);
                    board[i][j] = '.';

                    System.out.println(i + ", " + j + ", " + candiVal);
                    if (candiVal > optValue) {
                        result.setX(i);
                        result.setY(j);
                        optValue = candiVal;
                    }
                }
            }
        }
        return result;
    }
}


