package net.simplealgoroute.algorithms.bellmanford;

import net.simplealgoroute.algorithms.bellmanford.graphs.Neighbour;
import net.simplealgoroute.algorithms.bellmanford.graphs.Node;
import net.simplealgoroute.algorithms.bellmanford.tables.TableRow;

public abstract class BellmanFordAlgorithm
{
    public static Node[] execute(int[][] matrix)
    {
        var nodes = getNodesFromMatrix(matrix);
        addNeighboursToNodes(nodes, matrix);
        setNeighboursDataInTable(nodes);

        boolean wasChanged;

        do { wasChanged = sendDataToNeighbours(nodes); }
        while (wasChanged);

        sendDataToNeighbours(nodes);

        return nodes;
    }

    private static Node[] getNodesFromMatrix(int[][] matrix)
    {
        var nodes = new Node[matrix.length];

        char name = 'A';
        for (int i = 0; i < matrix.length; i++)
        {
            nodes[i] = new Node(Character.toString(name));
            name++;
        }

        return nodes;
    }

    private static void addNeighboursToNodes(Node[] nodes, int[][] adjacencyMatrix)
    {
        for (int i = 0; i < adjacencyMatrix.length; i++)
        {
            for (int j = 0; j < adjacencyMatrix[i].length; j++)
            {
                int cost = adjacencyMatrix[i][j];

                if (cost > 0)
                {
                    nodes[i].addNeighbour(new Neighbour(nodes[j], cost));
                    nodes[j].addNeighbour(new Neighbour(nodes[i], cost));
                }
            }
        }
    }

    private static void setNeighboursDataInTable(Node[] nodes)
    {
        for (var node : nodes)
        {
            for (var neighbour : node.getNeighbours())
            { node.addTableRow(new TableRow(neighbour.node(), neighbour.node(), neighbour.cost())); }
        }
    }

    private static boolean sendDataToNeighbours(Node[] nodes)
    {
        boolean success = false;

        for (var node : nodes)
        {
            for (var neighbour : node.getNeighbours())
            {
                var nodeTable = node.getTable();

                for (var nodeTableRow : nodeTable.getRows())
                { success = trySendRowToNeighbour(node, nodeTableRow, neighbour.node()); }
            }
        }

        return success;
    }

    private static boolean trySendRowToNeighbour(Node sourceNode, TableRow sourceRow, Node toNode)
    {
        if (sourceRow.destination() == toNode)
        { return false; }

        boolean success = false;

        var destinationTable = toNode.getTable();

        var sourceNeighbour = toNode.getNeighbours().stream()
                .filter(n -> n.node() == sourceNode)
                .findAny()
                .orElseThrow();

        var maybeRow = destinationTable.getRows().stream()
                .filter(r -> r.destination() == sourceRow.destination())
                .findAny();

        if (maybeRow.isPresent())
        {
            TableRow row = maybeRow.get();

            int newCost = sourceNeighbour.cost() + sourceRow.cost();

            if (newCost < row.cost())
            {
                destinationTable.removeRow(row);
                var newRow = new TableRow(sourceRow.destination(), sourceNode, newCost);
                destinationTable.addRowIfNotAlreadyPresent(newRow);

                success = true;
            }
        }

        else
        {
            int cost = sourceNeighbour.cost() + sourceRow.cost();

            var newRow = new TableRow(sourceRow.destination(), sourceNode, cost);
            destinationTable.addRowIfNotAlreadyPresent(newRow);

            success = true;
        }

        return success;
    }
}
