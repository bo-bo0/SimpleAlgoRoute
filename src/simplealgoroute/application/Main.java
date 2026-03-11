package simplealgoroute.application;

import simplealgoroute.algorithms.Dijkstra;
import simplealgoroute.tools.AdjacencyMatrixReader;

import java.util.Scanner;

public class Main
{
    @SuppressWarnings("unused")
    public static void main(String[] args) throws InterruptedException
    {
        System.out.println(AppData.getBorder());
        System.out.println("Simple Algo Route v" + AppData.getVersion());
        System.out.println(AppData.getBorder());

        var reader = new AdjacencyMatrixReader();
        var matrix = reader.getMatrix();

        System.out.println(AppData.getBorder());

        System.out.print("Insert the name of the root[A-B...-Z]: ");
        String root = new Scanner(System.in).nextLine();
        System.out.println("Calculating sink tree using Dijkstra's algorithm...");

        var sinkTreeMatrix = Dijkstra.execute(matrix, root);

        Thread.sleep(2000);

        System.out.println(AppData.getBorder());

        System.out.println("Done! Here's the adjacency matrix of the sink tree:\n");

        System.out.print("    ");

        char name = 'A';
        for (int i = 0; i < matrix.length; i++)
        {
            System.out.print(name + " ");
            name++;
        }

        System.out.println();

        name = 'A';
        for (var row : sinkTreeMatrix)
        {
            System.out.print(name);
            System.out.print(" [ ");
            name++;

            for (var col : row)
            { System.out.print(col + " "); }

            System.out.print("]");

            System.out.println();
        }

        System.out.println(AppData.getBorder());
    }
}