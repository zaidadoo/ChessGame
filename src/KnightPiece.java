public class KnightPiece extends Piece {
    public KnightPiece(int row, int col, Player player) {
        super(row, col, player);
    }

    @Override
    public boolean movePiece(Player player, World world, int row, int column) {
        // check if controlled by player
        if (player.PlayerColor != this.player.PlayerColor) {
            return false;
        }

        int newRow = row - this.row;
        int newCol = column - this.column;

        // knight can only move 2 cells in one direction and 1 cell in the other direction
        if ((Math.abs(newRow) != 2 || Math.abs(newCol) != 1) && (Math.abs(newRow) != 1 || Math.abs(newCol) != 2)) {
            return false;
        }

        // out of bounds
        if (row > 7 || row < 0) {
            return false;
        }
        if (column > 7 || column < 0) {
            return false;
        }

        // check if another piece is available at the destination
        if (world.board[row][column] != null) {
            // friendly fire
            if (world.board[row][column].player.PlayerColor == player.PlayerColor) {
                return false;
            }

            // kill piece
            world.board[row][column].killPiece(world);
        }

        // move piece
        world.board[row][column] = this;
        world.board[this.row][this.column] = null;
        this.row = row;
        this.column = column;

        return true;
    }

    public void killPiece(World world) {
        world.board[row][column] = null;
    }

    @Override
    public String toString() {
        if (this.player.PlayerColor == PlayerController.white) {
            return "♘";
        } else if (this.player.PlayerColor == PlayerController.black) {
            return "♞";
        } else {
            return "ERROR";
        }
    }
}
