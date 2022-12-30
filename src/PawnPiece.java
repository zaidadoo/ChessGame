public class PawnPiece extends Piece {
    private boolean hasMoved;

    public PawnPiece(int row, int col, Player player)
    {
        super(row, col, player);
        this.hasMoved = false;
    }

    @Override
    public boolean movePiece(Player player, World world, int row, int column)
    {
        // check if controlled by player
        if (player.PlayerColor != this.player.PlayerColor)
        {
            return false;
        }

        int newRow = row - this.row;
        int newCol = column - this.column;

        // pawn can only move forward
        if (player.PlayerColor == PlayerController.white)
        {
            if (newRow < 0)
            {
                return false;
            }
        }
        else
        {
            if (newRow > 0)
            {
                return false;
            }
        }

        // pawn can only move one square diagonally if capturing an opponent's piece
        if (Math.abs(newCol) > 1)
        {
            return false;
        }

        // pawn cannot move more than 2 steps in all cases
        if(Math.abs(newRow) > 2)
            return false;

        // already took first move, cannot do 2 steps
        if(Math.abs(newRow) == 2 && hasMoved)
            return false;

        // out of bounds
        if (row > 7 || row < 0)
        {
            return false;
        }
        if (column > 7 || column < 0)
        {
            return false;
        }

        // check if another piece is available at the destination
        if (world.board[row][column] != null)
        {
            // friendly fire
            if (world.board[row][column].player.PlayerColor == player.PlayerColor)
            {
                return false;
            }

            // can only capture diagonally
            if (Math.abs(newCol) != 1)
            {
                return false;
            }

            // kill piece
            world.board[row][column].killPiece(world);
        }

        // if destination has no piece
        if(world.board[row][column] == null)
        {
            // pawn can only move two squares forward on its first move if both squares are unoccupied
            if (Math.abs(newRow) == 2)
            {
                if (this.hasMoved == false)
                {
                    if (player.PlayerColor == PlayerController.white)
                    {
                        if (world.board[this.row + 1][this.column] != null || world.board[this.row + 2][this.column] != null)
                        {
                            return false;
                        }
                    }
                    else
                    {
                        if (world.board[this.row - 1][this.column] != null || world.board[this.row - 2][this.column] != null)
                        {
                            return false;
                        }
                    }
                }
                else
                {
                    return false;
                }
            }

            // cannot move diagonally if null
            if(newCol != 0)
                return false;
        }

        // logic to prevent 2 steps if first move done
        if(this.hasMoved == false)
            this.hasMoved = true;

        // move piece
        world.board[row][column] = this;
        world.board[this.row][this.column] = null;
        this.row = row;
        this.column = column;

        return true;
    }

        public void killPiece(World world)
        {
            world.board[row][column] = null;
        }

        @Override
        public String toString()
        {
            if (this.player.PlayerColor == PlayerController.white) {
                return "♙";
            } else if (this.player.PlayerColor == PlayerController.black) {
                return "\u26E8";
            } else {
                return "ERROR";
            }
        }
    }

