/**
 * Tic-Tac-Toe Project
 * This project implements Tic-Tac-Toe game
 * The game can be played by two human player or human and AI player
 * Enjoy!
 */
public class Main {
    public static void main(String[] args) {
        TicTacToe ticTacToe = TicTacToe.getInstance();
        ticTacToe.initialize();
        ticTacToe.reset();

        int i = 0;
        while (!TicTacToe.isQuit) {
            Player currPlayer = ((i++ % Integer.MAX_VALUE) % 2 == 0) ? ticTacToe.getPlayer1() : ticTacToe.getPlayer2();
            ticTacToe.play(currPlayer);
        }
    }
}
