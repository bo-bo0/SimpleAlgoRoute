package net.simplealgoroute.application;

import net.simplealgoroute.algorithms.Algorithm;
import net.simplealgoroute.tools.AdjacencyMatrixReader;

public class Main
{
    @SuppressWarnings("unused")
    public static void main(String[] args) throws InterruptedException
    {
        System.out.println(AppData.getBorder());
        System.out.println("Simple Algo Route v" + AppData.getVersion());
        System.out.println(AppData.getBorder());

        var menu = new Menu();
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
    }
}