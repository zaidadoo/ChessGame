public class World {
    public Piece[][] board = new Piece[8][8];
    public boolean WhiteKingDead = false;
    public boolean BlackKingDead = false;

    World(Player WhitePlayer, Player BlackPlayer)
    {
        board[0][4] = new KingPiece(0,4,WhitePlayer);
        board[0][0] = new RookPiece(0,0,WhitePlayer);
        board[0][7] = new RookPiece(0,7,WhitePlayer);
        board[0][5] = new BishopPiece(0, 5, WhitePlayer);
        board[0][2] = new BishopPiece(0, 2, WhitePlayer);
        board[0][1] = new KnightPiece(0, 1, WhitePlayer);
        board[0][6] = new KnightPiece(0, 6, WhitePlayer);

        board[7][4] = new KingPiece(7,4,BlackPlayer);
        board[7][7] = new RookPiece(7,7,BlackPlayer);
        board[7][0] = new RookPiece(7,0,BlackPlayer);
        board[7][5] = new BishopPiece(7, 5, BlackPlayer);
        board[7][2] = new BishopPiece(7, 2, BlackPlayer);
        board[7][1] = new KnightPiece(7, 1, BlackPlayer);
        board[7][6] = new KnightPiece(7, 6, BlackPlayer);
    }

    void printBoard() {
        System.out.println("===============================");
        System.out.println("\t\tWhite Player");
        System.out.println("    [a][b][c ][d ][e][f ][g ][h]");
        for (int i = 0; i < 8; i++) {
            System.out.print("[" + (i + 1) + "]");
            for (int j = 0; j < 8; j++) {
                if(board[i][j] == null)
                    System.out.print("[ ]");
                else
                    System.out.print("[" + board[i][j].toString() + "]");
            }
            System.out.println();
        }

        System.out.println("\t\tBlack Player");
        System.out.println("===============================");
    }
}
