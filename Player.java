public interface Player extends Inputtable {
    String getName();

    int getNumWin();

    void setNumWin(int val);

    char getStone();

    void setStone(char stone);

    default boolean isMoreMoves(char[][] board) {
        for (char[] chars : board) {
            for (char ch: chars) {
                if (ch == '.') {
                    return true;
                }
            }
        }
        return false;
    }
}
