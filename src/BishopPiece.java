public class BishopPiece extends Piece {
    public BishopPiece(int row, int col, Player player) {
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

        // bishop can only move diagonally
        if (Math.abs(newRow) != Math.abs(newCol)) {
            return false;
        }

        // out of bounds
        if (row > 7 || row < 0) {
            return false;
        }
        if (column > 7 || column < 0) {
            return false;
        }

        // check if path is blocked
        // moving diagonally downwards to the right
        if (newRow > 0 && newCol > 0) {
            for (int i = 1; i < newRow; i++) {
                if (world.board[this.row + i][this.column + i] != null) {
                    return false;
                }
            }
        }

        // moving diagonally downwards to the left
        if (newRow > 0 && newCol < 0) {
            for (int i = 1; i < newRow; i++) {
                if (world.board[this.row + i][this.column - i] != null) {
                    return false;
                }
            }
        }

        // moving diagonally upwards to the right
        if (newRow < 0 && newCol > 0) {
            for (int i = -1; i > newRow; i--) {
                if (world.board[this.row + i][this.column - i] != null) {
                    return false;
                }
            }
        }

        // moving diagonally upwards to the left
        if (newRow < 0 && newCol < 0) {
            for (int i = -1; i > newRow; i--) {
                if (world.board[this.row + i][this.column + i] != null) {
                    return false;
                }
            }
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
            return "♗";
        } else if (this.player.PlayerColor == PlayerController.black) {
            return "♝";
        } else {
            return "ERROR";
        }
    }
}

