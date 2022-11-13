package com.universe.exploration.component.boardgrid;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.utils.Array;

public class BoardGraph implements IndexedGraph<GridNode> {
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
