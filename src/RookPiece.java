public class RookPiece extends Piece{
    public RookPiece(int row, int column, Player player) {
        super(row, column, player);
    }

    public boolean movePiece(Player player, World world, int row, int column)
    {
        // check if controlled by player
        if(player.PlayerColor != this.player.PlayerColor)
            return false;

        int newRow = row - this.row;
        int newCol = column - this.column;

        // rook moves max 7 cells
        if(newRow < -7 || newRow > 7) return false;
        if(newCol < -7 || newCol > 7) return false;

        // rook did not move
        if(newRow == 0 && newCol == 0) return false;

        // rook moved diagonally
        if(newRow != 0 && newCol != 0) return false;

        // out of bounds
        if(row > 7 || row < 0) return false;
        if(column > 7 || column < 0) return false;

        // check if another piece available there
        if(world.board[row][column] != null)
        {
            // if moving more than 1 cell
            // check if left blocked
            if(newCol < -1)
            {
                for(int i = -1; i > newCol; i--)
                {
                    if(world.board[this.row][this.column + i] != null) return false;
                }
            }

            // check if right blocked
            if(newCol > 1)
            {
                for(int i = 1; i < newCol; i++)
                {
                    if(world.board[this.row][this.column + i] != null) return false;
                }
            }

            // check if top blocked
            if(newRow > 1)
            {
                for(int i = -1; i > newRow; i--)
                {
                    if(world.board[this.row + 1][this.column] != null) return false;
                }
            }

            // check if bottom blocked
            if(newRow < -1)
            {
                for(int i = 1; i < newRow; i++)
                {
                    if(world.board[this.row + 1][this.column] != null) return false;
                }
            }

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
        world.board[row][column] = null;
    }

    @Override
    public String toString()
    {
        if(this.player.PlayerColor == PlayerController.white)
            return "♖";
        else if(this.player.PlayerColor == PlayerController.black)
            return "♜";
        else
            return "ERROR";
    }
}
