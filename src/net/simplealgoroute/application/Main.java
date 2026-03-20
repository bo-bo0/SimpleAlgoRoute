package net.simplealgoroute.application;

import net.simplealgoroute.algorithms.Algorithm;
import net.simplealgoroute.tools.AdjacencyMatrixReader;

import java.util.Scanner;

public class Main
{
    @SuppressWarnings("all")
    public static void main(String[] args) throws InterruptedException
    {
        System.out.println(AppData.getBorder());
        System.out.println("Simple Algo Route v" + AppData.getVersion());
        System.out.println(AppData.getBorder());

        var menu = new Menu();

        while (true)
        {
            Algorithm algorithm = menu.requestUserChoice();

            var matrix = new int[0][];

            if (algorithm != null)
            {
                var reader = new AdjacencyMatrixReader();
                matrix = reader.getMatrix();

                System.out.println(AppData.getBorder());
            }

            switch (algorithm)
            {
                case DIJKSTRA -> menu.dijkstraPerform(matrix);
                case BELLMAN_FORD -> menu.bellmanFordPerform(matrix);

                case null -> menu.exitPerform();
            }

            System.out.print("Press enter to continue...");
            new Scanner(System.in).nextLine();
            System.out.println(AppData.getBorder());
            System.out.println();
        }
    }
}