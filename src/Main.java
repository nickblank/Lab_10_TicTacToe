import java.util.Scanner;

public class Main
{
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String board [][] = new String[ROWS][COLS];

    public static void main(String[] args)
    {
        boolean finished = false;
        boolean playing = true;
        Scanner in = new Scanner(System.in);
        String player = "X";
        int turnCnt = 0;
        int row = -1;
        int col = -1;
        final int WIN_MOVES = 5;
        final int TIE_MOVES = 7;

        do
        {
            player = "X";
            playing = true;
            turnCnt = 0;
            clearBoard();

            do
            {
                do
                {
                    display();
                    System.out.println("Enter move for " + player);
                    row = SafeInput.getRangedInt(in,"Enter row: ", 1,3);
                    col = SafeInput.getRangedInt(in, "Enter col: ", 1, 3);
                    row--;
                    col--;
                }while(!isValidMove(row, col));
                board[row][col] = player;
                turnCnt++;

                if (turnCnt >= WIN_MOVES)
                {
                    if (isWin(player))
                    {
                        display();
                        System.out.println("Player " + player + " wins! ");
                        playing = false;
                    }
                }

                if (turnCnt >= TIE_MOVES)
                {
                    if (isTie())
                    {
                        display();
                        System.out.println("It's a tie!");
                        playing = false;
                    }
                }

                if (player.equals("X"))
                {
                    player = "O";
                }
                else
                {
                    player = "X";
                }

            }while(playing);

            finished = SafeInput.getYNConfirm(in,"Are you done finished playing? ");

        }while (!finished);
    }

    private static void clearBoard()
    {
        for(int row = 0; row < ROWS; row++)
        {
            for(int col = 0; col < COLS; col++)
            {
                board[row][col] = " ";
            }
        }
    }

    private static void display()
    {
        for(int row = 0;row < ROWS; row++)
        {
            System.out.print("| ");
            for (int col = 0; col < COLS; col++)
            {
                System.out.print(board[row][col] + " | ");
            }
            System.out.println();
        }
    }

    private static boolean isValidMove(int row, int col)
    {
        boolean retVal = false;
        if(board[row][col].equals(" "))
        {
            retVal = true;
        }
        return retVal;
    }
    private static boolean isWin(String player)
    {
        if(isColWin(player) || isRowWin(player) || isDiagnalWin(player))
        {
            return true;
        }
        return false;
    }

    private static boolean isColWin(String player)
    {
        for(int col = 0; col < COLS; col++)
        {
            if(board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player))
            {
                return true;
            }
        }
        return false;
    }
    private static boolean isRowWin(String player)
    {
        for(int row = 0; row < ROWS; row++)
        {
            if(board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player))
            {
                return true;
            }
        }
        return false;
    }
    private static boolean isDiagnalWin(String player)
    {
        if(board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player) )
        {
            return true;
        }
        if(board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player) )
        {
            return true;
        }
        return false;
    }
    private static boolean isTie()
    {
        boolean xFlag = false;
        boolean oFlag = false;
        for(int row = 0; row < ROWS; row++)
        {
            if(board[row][0].equals("X") || board[row][1].equals("X") || board[row][2].equals("X"))
            {
                xFlag = true;
            }
            if(board[row][0].equals("O") || board[row][1].equals("O") || board[row][2].equals("O"))
            {
                oFlag = true;
            }

            if(!xFlag && oFlag)
            {
                return false;
            }

            xFlag = oFlag = false;
        }

        // check columns
        for(int col = 0; col < COLS; col++)
        {
            if (board[0][col].equals("X") || board[1][col].equals("X") || board[2][col].equals("X"))
            {
                xFlag = true;
            }
            if (board[0][col].equals("O") || board[1][col].equals("O") || board[2][col].equals("O"))
            {
                oFlag = true;
            }
            if (! (xFlag && oFlag))
            {
                return false;
            }
        }
        // check diagonals

        xFlag = oFlag = false;
        if (board[0][0].equals("X") || board[1][1].equals("X") || board[2][2].equals("X"))
        {
            xFlag = true;
        }
        if (board[0][0].equals("O") || board[1][1].equals("O") || board[2][2].equals("O"))
        {
            oFlag = true;
        }
        if (! (xFlag && oFlag))
        {
            return false;
        }

        xFlag = oFlag = false;

        if (board[0][2].equals("X") || board[1][1].equals("X") || board[2][0].equals("X"))
        {
            xFlag = true;
        }
        if (board[0][2].equals("O") || board[1][1].equals("O") || board[2][0].equals("O"))
        {
            oFlag = true;
        }
        if (! (xFlag && oFlag))
        {
            return false;
        }

        return true;
    }

}
