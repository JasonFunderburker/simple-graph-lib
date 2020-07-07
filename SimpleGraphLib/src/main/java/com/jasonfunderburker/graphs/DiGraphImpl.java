package com.jasonfunderburker.graphs;

import java.util.Collection;

public class DiGraphImpl<T> extends BaseGraphImpl<T> {

    public DiGraphImpl(Collection<T> vertices) {
        super(vertices);
    }

    @Override
    public void addEdge(T from, T to) {
        checkVertex(from);
        checkVertex(to);

        adjVertices.get(from).add(to);
    }

}