package com.universe.exploration.component.boardgrid;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

import java.util.ArrayList;
import java.util.List;

public class BoardGraph implements IndexedGraph<GridNode> {
    private BoardNodeHeuristic boardNodeHeuristic = new BoardNodeHeuristic();
    private List<GridNode> gridNodes = new ArrayList<>();
    private List<NodeConnection> connections = new ArrayList<>();
    private ObjectMap<GridNode, Array<NodeConnection>> connectionsMap = new ObjectMap<>();
    private int lastNodeIndex = 0;

    public void addGridNode(GridNode gridNode) {
        gridNode.setIndex(lastNodeIndex);
        gridNodes.add(gridNode);
        lastNodeIndex++;
    }

    public void connectGridNodes(GridNode from, GridNode to) {
        NodeConnection connection = new NodeConnection(from, to);
        if (!connectionsMap.containsKey(from)) {
            connectionsMap.put(from, new Array<>());
        }
        connectionsMap.get(from).add(connection);
        connections.add(connection);
    }

    public GraphPath<GridNode> findPath(GridNode from, GridNode to) {
        GraphPath<GridNode> gridNodePath = new DefaultGraphPath<>();
        new IndexedAStarPathFinder<>(this).searchNodePath(from, to, boardNodeHeuristic, gridNodePath);
        return gridNodePath;
    }

    @Override
    public int getIndex(GridNode node) {
        return 0;
    }

    @Override
    public int getNodeCount() {
        return 0;
    }

    @Override
    public Array<Connection<GridNode>> getConnections(GridNode fromNode) {
        return null;
    }
}
