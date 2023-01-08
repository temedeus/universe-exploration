package com.universe.exploration.component.boardgrid;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.math.Vector2;

public class NodeConnection implements Connection<GridNode> {
    private GridNode fromNode;
    private GridNode toNode;
    private final float cost;

    public NodeConnection(GridNode fromNode, GridNode toNode) {
        this.fromNode = fromNode;
        this.toNode = toNode;
        cost = Vector2.dst(fromNode.getX(), fromNode.getY(), toNode.getX(), toNode.getY());
    }

    @Override
    public float getCost() {
        return cost;
    }

    @Override
    public GridNode getFromNode() {
        return fromNode;
    }

    @Override
    public GridNode getToNode() {
        return toNode;
    }
}
