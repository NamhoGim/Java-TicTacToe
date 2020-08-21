public class HumanPlayer implements Player {
    private final String name;
    private char stone;
    private int numWin;

    public HumanPlayer(String name, char stone) {
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
        return "HumanPlayer{" +
                "name='" + name + '\'' +
                ", stone=" + stone +
                ", numWin=" + numWin +
                '}';
    }
}
