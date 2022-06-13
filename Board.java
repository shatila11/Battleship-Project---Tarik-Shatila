public class Board {

    private char[][] squares;

    Board()
    {
        squares = new char[10][10];

        for(int r = 0; r < squares.length; ++r)
        {
            for(int i = 0; i < squares[r].length; ++i)
            {
                squares[r][i] = '-';
            }
        }
    }


    @Override
    public String toString()
    {
        String returnstr = "";
        for (char[] row : squares)
        {
            for (char ch : row)
            {
                returnstr += String.valueOf(ch) + " ";
            }
            returnstr += "\n";
        }
        return returnstr;
    }

    public boolean addShip(int row, int col, int len, boolean horizontal)
    {
        try {
            for(int i = 0; i < len; ++i)
            {
                if (squares[row + (horizontal ? 0 : i)][col + (horizontal ? i : 0)] == 'b')
                {
                    throw new java.lang.ArrayIndexOutOfBoundsException();
                }
            }
        }
        catch (java.lang.ArrayIndexOutOfBoundsException e)
        {
            return false;
        }
        for(int i = 0; i < len; ++i)
        {
            squares[row + (horizontal ? 0 : i)][col + (horizontal ? i : 0)] = 'b';
        }
        return true;
    }

    public int shoot(int row, int col)
    {
        try {
            if(squares[row][col] == '-')
            {
                squares[row][col] = 'm';
                return 0;
            }
            else if(squares[row][col] == 'b')
            {
                squares[row][col] = 'x';
                return 1;
            }
        }
        catch (java.lang.ArrayIndexOutOfBoundsException e)
        {
            return -1;
        }

        return 2;
    }

    public boolean gameOver()
    {
        for (char[] row : squares)
        {
            for (char c : row)
            {
                if(c == 'b')
                    return false;
            }
        }
        return true;
    }

    public boolean foundShip(int length)
    {

        for (char[] row : squares)
        {
            int shipLength = 0;

            for(int i = 0; i < row.length; ++i)
            {
                if (row[i] == 'b')
                    ++shipLength;
                else if (shipLength > 0 && row[i] != 'b')
                    shipLength = 0;
                if(shipLength == length && i + 1 < row.length && row[i + 1] != 'b')
                    return true;
            }
        }

        for(int iHorizontalRow = 0; iHorizontalRow < squares[0].length; ++iHorizontalRow)
        {
            int shipLength = 0;
            for(int iVerticalRow = 0; iVerticalRow < squares.length; ++iVerticalRow)
            {
                if(squares[iVerticalRow][iHorizontalRow] == 'b')
                    ++shipLength;
                else if (shipLength > 0 && squares[iVerticalRow][iHorizontalRow] != 'b')
                    shipLength = 0;
                if(shipLength == length && iVerticalRow + 1 < squares.length && squares[iVerticalRow + 1][iHorizontalRow] != 'b')
                    return true;
            }
        }
        return false;
    }
}