package simplealgoroute.application;

import simplealgoroute.algorithms.Algorithm;
import simplealgoroute.algorithms.dijkstra.DijkstraAlgorithm;
import simplealgoroute.tools.SafeKeyboardScanner;

import java.util.Scanner;

public class Menu
{
    public Algorithm requestUserChoice()
    {
        var scan = new SafeKeyboardScanner();

        System.out.println("Select an algorithm");
        System.out.println("1: Dijkstra (returns the sink tree starting from a root)");
        System.out.println("2: Bellman Ford (returns the routing table of each node)");
        System.out.println("0: Exit the program");
        System.out.print("Insert here: ");

        int choice = scan.nextInt();

        return switch (choice)
        {
            case 1 -> Algorithm.DIJKSTRA;
            case 2 -> Algorithm.BELLMAN_FORD;

            default -> null;
        };
    }
    public void dijkstraPerform(int[][] matrix) throws InterruptedException
    {
        System.out.print("Insert the name of the root[A-B...-Z]: ");
        String root = new Scanner(System.in).nextLine();
        System.out.println("Calculating sink tree using Dijkstra's algorithm...");

        var sinkTreeMatrix = DijkstraAlgorithm.execute(matrix, root);

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
    public void bellmanFordPerform(int[][] matrix)
    {

    }
    public void exitPerform()
    {
        System.out.println(AppData.getBorder());
        System.out.println("Program successfully terminated");
        System.out.println(AppData.getBorder());

        System.exit(0);
    }
}
