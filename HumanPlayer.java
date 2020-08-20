public class HumanPlayer extends Player implements Inputtable {
    public HumanPlayer() {
        super();
    }

    @Override
    public String toString() {
        return "HumanPlayer{" +
                "name='" + name + '\'' +
                ", stone=" + stone +
                ", numWin=" + numWin +
                '}';
    }
}
