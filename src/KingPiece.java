public class KingPiece extends Piece{
    public KingPiece(int row, int column, Player player) {
        super(row, column, player);
    }

    public boolean movePiece(Player player, World world, int row, int column)
    {
        // check if controlled by player
        if(player.PlayerColor != this.player.PlayerColor)
            return false;

        int newRow = row - this.row;
        int newCol = column - this.column;

        // king moves max 1 cell
        if(newRow < -1 || newRow > 1) return false;
        if(newCol < -1 || newCol > 1) return false;

        // king did not move
        if(newRow == 0 && newCol == 0) return false;

        // out of bounds
        if(row > 7 || row < 0) return false;
        if(column > 7 || column < 0) return false;

        // check if another piece available there
        if(world.board[row][column] != null)
        {
            // friendly fire
            if(world.board[row][column].player.PlayerColor == player.PlayerColor)
                return false;

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
        if(this.player.PlayerColor == PlayerController.white)
            world.WhiteKingDead = true;
        else
            world.BlackKingDead = true;
    }

    @Override
    public String toString()
    {
        if(this.player.PlayerColor == PlayerController.white)
            return "♔";
        else if(this.player.PlayerColor == PlayerController.black)
            return "♚";
        else
            return "ERROR";
    }
}
