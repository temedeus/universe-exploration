package com.universe.exploration.component.boardgrid;

import com.badlogic.gdx.ai.pfa.Heuristic;
import com.badlogic.gdx.math.Vector2;

public class BoardNodeHeuristic implements Heuristic<GridNode> {
    @Override
    public float estimate(GridNode startNode, GridNode endNode) {
        return Vector2.dst(startNode.getX(), startNode.getY(), endNode.getX(), endNode.getY());
    }
}
