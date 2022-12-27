public abstract class Piece {
    public int row;
    public int column;
    public Player player;

    public Piece(int row, int column, Player player) {
        this.row = row;
        this.column = column;
        this.player = player;
    }

    // returns true if legal move, false if illegal move
    public abstract boolean movePiece(Player player, World world, int row, int column);

    public abstract void killPiece(World world);
}
