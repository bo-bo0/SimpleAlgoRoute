package simplealgoroute.graphs;

public class Node
{
    private final String name;
    private final int id;

    private boolean chosen;
    private Label label;

    public Node(String name, int id)
    {
        this.name = name;
        this.id = id;
        chosen = false;
        label = null;
    }

    @SuppressWarnings("all")
    public boolean isChosen()
    {
        return chosen;
    }

    public void choose()
    {
        chosen = true;
    }

    public String getName()
    {
        return name;
    }

    public int getId()
    {
        return id;
    }

    public Label getLabel()
    {
        return label;
    }

    public void setLabel(Label label)
    {
        this.label = label;
    }
}
