package net.simplealgoroute.algorithms.bellmanford.tables;

import java.util.ArrayList;
import java.util.Objects;

public class Table
{
    private ArrayList<TableRow> rows;

    public Table()
    {
        rows = new ArrayList<>();
    }

    public void addRowIfNotAlreadyPresent(TableRow row)
    {
        Objects.requireNonNull(row);
        rows.add(row);
        rows = new ArrayList<>(rows.stream().distinct().toList());
    }

    public void removeRow(TableRow row)
    {
        Objects.requireNonNull(row);
        rows.remove(row);
    }

    public ArrayList<TableRow> getRows()
    {
        return rows;
    }

    public void printContent()
    {
        System.out.println(" D  N  C");
        System.out.println("---------");

        for (var r : rows)
        {
            System.out.print("|");
            System.out.print(r.destination().getName() + "  " + r.nextHop().getName() + "  " + r.cost());
            System.out.println("|");
        }
    }
}
