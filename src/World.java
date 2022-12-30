public class World {
    public Piece[][] board = new Piece[8][8];
    public boolean WhiteKingDead = false;
    public boolean BlackKingDead = false;

    private static World instance;

    public static World getInstance(Player WhitePlayer, Player BlackPlayer) {
        if (instance == null) {
            instance = new World(WhitePlayer, BlackPlayer);
        }
        return instance;
    }

    private World(Player WhitePlayer, Player BlackPlayer)
    {
        board[0][4] = new KingPiece(0,4,WhitePlayer);
        board[0][0] = new RookPiece(0,0,WhitePlayer);
        board[0][7] = new RookPiece(0,7,WhitePlayer);
        board[0][5] = new BishopPiece(0, 5, WhitePlayer);
        board[0][2] = new BishopPiece(0, 2, WhitePlayer);
        board[0][1] = new KnightPiece(0, 1, WhitePlayer);
        board[0][6] = new KnightPiece(0, 6, WhitePlayer);
        board[0][3] = new QueenPiece(0, 3, WhitePlayer);
        board[1][0] = new PawnPiece(1, 0, WhitePlayer);
        board[1][1] = new PawnPiece(1, 1, WhitePlayer);
        board[1][2] = new PawnPiece(1, 2, WhitePlayer);
        board[1][3] = new PawnPiece(1, 3, WhitePlayer);
        board[1][4] = new PawnPiece(1, 4, WhitePlayer);
        board[1][5] = new PawnPiece(1, 5, WhitePlayer);
        board[1][6] = new PawnPiece(1, 6, WhitePlayer);
        board[1][7] = new PawnPiece(1, 7, WhitePlayer);

        board[7][4] = new KingPiece(7,4,BlackPlayer);
        board[7][7] = new RookPiece(7,7,BlackPlayer);
        board[7][0] = new RookPiece(7,0,BlackPlayer);
        board[7][5] = new BishopPiece(7, 5, BlackPlayer);
        board[7][2] = new BishopPiece(7, 2, BlackPlayer);
        board[7][1] = new KnightPiece(7, 1, BlackPlayer);
        board[7][6] = new KnightPiece(7, 6, BlackPlayer);
        board[7][3] = new QueenPiece(7, 3, BlackPlayer);
        board[6][0] = new PawnPiece(6, 0, BlackPlayer);
        board[6][1] = new PawnPiece(6, 1, BlackPlayer);
        board[6][2] = new PawnPiece(6, 2, BlackPlayer);
        board[6][3] = new PawnPiece(6, 3, BlackPlayer);
        board[6][4] = new PawnPiece(6, 4, BlackPlayer);
        board[6][5] = new PawnPiece(6, 5, BlackPlayer);
        board[6][6] = new PawnPiece(6, 6, BlackPlayer);
        board[6][7] = new PawnPiece(6, 7, BlackPlayer);
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
