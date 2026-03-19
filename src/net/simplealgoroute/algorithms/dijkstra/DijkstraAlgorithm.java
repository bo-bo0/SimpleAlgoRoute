package net.simplealgoroute.algorithms.dijkstra;

import net.simplealgoroute.algorithms.dijkstra.graphs.Label;
import net.simplealgoroute.algorithms.dijkstra.graphs.Node;

import java.util.Arrays;
import java.util.Comparator;

public abstract class DijkstraAlgorithm
{
    public static int[][] execute(int[][] matrix, String rootName)
    {
        if (matrix.length != matrix[0].length)
        { throw new IllegalArgumentException("Invalid matrix provided."); }

        int size = matrix.length;

        var nodes = new Node[size];

        char name = 'A';
        for (int i = 0; i < size; i++)
        {
            nodes[i] = new Node(Character.toString(name), i);
            name++;
        }

        chooseNode(rootName, nodes);
        int rootId = getIdFromName(rootName, nodes);
        nodes[rootId].setLabel(new Label(0, null));

        Node chosen = nodes[rootId];

        while (chosen != null)
        {
            labelNeighbours(chosen.getName(), nodes, matrix);
            chosen = chooseLabelledNodeWithMinCost(nodes);
        }

        var newMatrix = new int[size][size];

        for (int i = 0; i < size; i++)
        { Arrays.fill(newMatrix[i], 0); }

        for (int i = 0; i < size; i++)
        {
            Node neighbour = nodes[i].getLabel().neighbour();

            if (neighbour != null)
            {
                newMatrix[i][neighbour.getId()] = matrix[i][neighbour.getId()];
                newMatrix[neighbour.getId()][i] = matrix[i][neighbour.getId()];
            }
        }

        return newMatrix;
    }

    private static int getIdFromName(String name, Node[] nodes)
    {
        return Arrays.stream(nodes)
                    .filter(n -> n.getName().equals(name))
                    .findFirst()
                    .orElseThrow()
                    .getId();
    }

    private static void chooseNode(String name, Node[] nodes)
    {
        Node node = Arrays.stream(nodes)
                .filter(n -> n.getName().equals(name))
                .findFirst()
                .orElseThrow();

        node.choose();
    }

    private static void labelNeighbours(String name, Node[] nodes, int[][] matrix)
    {
        int rowIndex = getIdFromName(name, nodes);

        for (int i = 0; i < matrix[0].length; i++)
        {
            if (matrix[rowIndex][i] != 0)
            {
                if (!nodes[i].isChosen())
                {
                    int cost = nodes[rowIndex].getLabel().cost() + matrix[rowIndex][i];
                    Label oldLabel = nodes[i].getLabel();
                    Label newLabel = new Label(cost, nodes[rowIndex]);

                    if (oldLabel == null)
                    { nodes[i].setLabel(newLabel); }

                    else if (cost < oldLabel.cost())
                    { nodes[i].setLabel(newLabel); }
                }
            }
        }
    }

    private static Node chooseLabelledNodeWithMinCost(Node[] nodes)
    {
        Node[] labelledNodes = Arrays.stream(nodes)
                .filter(n -> n.getLabel() != null && !n.isChosen()).toArray(Node[]::new);

        Node minNode = Arrays.stream(labelledNodes)
                .min(Comparator.comparing(n -> n.getLabel().cost()))
                .orElse(null);

        if (minNode != null)
        { minNode.choose(); }

        return minNode;
    }
}
