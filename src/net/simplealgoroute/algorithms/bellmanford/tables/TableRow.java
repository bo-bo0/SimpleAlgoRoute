package net.simplealgoroute.algorithms.bellmanford.tables;

import net.simplealgoroute.algorithms.bellmanford.graphs.Node;

public record TableRow(Node destination, Node nextHop, int cost) { }
