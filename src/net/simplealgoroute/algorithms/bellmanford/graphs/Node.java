package net.simplealgoroute.algorithms.bellmanford.graphs;

import net.simplealgoroute.algorithms.bellmanford.tables.Table;
import net.simplealgoroute.algorithms.bellmanford.tables.TableRow;

import java.util.ArrayList;
import java.util.Objects;

public class Node
{
    private final String name;
    private final Table table;
    private final ArrayList<Neighbour> neighbours;

    public Node(String name)
    {
        this.name = name;
        table = new Table();
        neighbours = new ArrayList<>();
    }

    public ArrayList<Neighbour> getNeighbours()
    {
        return neighbours;
    }

    public void addNeighbour(Neighbour neighbour)
    {
        Objects.requireNonNull(neighbour);
        neighbours.add(neighbour);
    }

    public void addTableRow(TableRow row)
    {
        Objects.requireNonNull(row);
        table.addRowIfNotAlreadyPresent(row);
    }

    public Table getTable()
    {
        return table;
    }

    public String getName()
    {
        return name;
    }
}
