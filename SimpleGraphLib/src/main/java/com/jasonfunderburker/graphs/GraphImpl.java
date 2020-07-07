package com.jasonfunderburker.graphs;

import java.util.Collection;

public class GraphImpl<T> extends BaseGraphImpl<T> {

    public GraphImpl(Collection<T> vertices) {
        super(vertices);
    }

    @Override
    public void addEdge(T from, T to) {
        checkVertex(from);
        checkVertex(to);

        adjVertices.get(from).add(to);
        adjVertices.get(to).add(from);
    }
}
