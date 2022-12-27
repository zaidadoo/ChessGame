import java.util.Scanner;

public class ChessGame {
    // After number of total moves reach value, game ends in draw
    private int tieCounter = 30;

    private boolean inputChecker(String[] moveList)
    {
        try
        {
            if(!Character.isAlphabetic(moveList[0].charAt(0)))
                return false;

            if(moveList[0].charAt(0) < 'a' || moveList[0].charAt(0) > 'h')
                return false;

            if(!Character.isDigit(moveList[0].charAt(1)))
                return false;

            if(Character.getNumericValue(moveList[0].charAt(1)) < 0 || Character.getNumericValue(moveList[0].charAt(1)) > 8)
                return false;

            if(!Character.isAlphabetic(moveList[1].charAt(0)))
                return false;

            if(moveList[1].charAt(0) < 'a' || moveList[1].charAt(0) > 'h')
                return false;

            if(!Character.isDigit(moveList[1].charAt(1)))
                return false;

            if(Character.getNumericValue(moveList[1].charAt(1)) < 0 || Character.getNumericValue(moveList[1].charAt(1)) > 8)
                return false;

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public void start()
    {
        Player WhitePlayer = new Player(PlayerController.white);
        Player BlackPlayer = new Player(PlayerController.black);

        World world = new World(WhitePlayer, BlackPlayer);

        int rounds = 0;
        Scanner input = new Scanner(System.in);

        Player[] turn = new Player[2];
        turn[0] = WhitePlayer;
        turn[1] = BlackPlayer;

        while(rounds != tieCounter && world.WhiteKingDead == false && world.BlackKingDead == false)
        {
            world.printBoard();

            boolean LegalMove = false;
            while(LegalMove == false)
            {
                System.out.print(turn[rounds % 2] + " turn, input move: ");
                String move = input.nextLine();

                String[] moveList = move.split(" ", 2);

                if(inputChecker(moveList) == false)
                {
                    System.out.println("[ Invalid input, please try again. ]");
                    continue;
                }

                // column translate
                int SourceCol = moveList[0].charAt(0) - 'a';
                int TargetCol = moveList[1].charAt(0) - 'a';

                // row translate
                int SourceRow = moveList[0].charAt(1) - '1';
                int TargetRow = moveList[1].charAt(1) - '1';

                if(world.board[SourceRow][SourceCol] == null)
                {
                    System.out.println("[ No piece in chosen square, please try again. ]");
                    continue;
                }

                if(world.board[SourceRow][SourceCol].movePiece(turn[rounds % 2], world, TargetRow, TargetCol))
                    LegalMove = true;
                else
                    System.out.println("[ Illegal move, please try again. ]");
            }

            rounds++;
        }

        System.out.println("===========================");

        if(world.BlackKingDead == true)
            System.out.println("Checkmate, White player wins.");

        if(world.WhiteKingDead == true)
            System.out.println("Checkmate, Black player wins.");

        if(world.WhiteKingDead == false && world.BlackKingDead == false)
            System.out.println("Tie, move count reached.");

        System.out.println("===========================");
    }
}
