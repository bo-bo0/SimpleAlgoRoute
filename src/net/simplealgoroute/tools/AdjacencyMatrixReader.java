package net.simplealgoroute.tools;

import java.util.Scanner;

public class AdjacencyMatrixReader
{
    public int[][] getMatrix()
    {
        System.out.print("Insert the number of columns and rows[num]: ");
        int size = new SafeKeyboardScanner().nextInt();

        var matrix = new int[size][size];

        var scan = new Scanner(System.in);
        for (int i = 0; i < size; i++)
        {
            System.out.print("Insert row " + (i + 1) + "[num1 num2...lastNum]: ");
            String row = scan.nextLine();
            var tokens = row.split("\\s+");

            if (tokens.length != size)
            { throw new IllegalArgumentException("Wrong parameters number."); }

            for (int j = 0; j < size; j++)
            { matrix[i][j] = Integer.parseInt(tokens[j]); }
        }

        return matrix;
    }
}
