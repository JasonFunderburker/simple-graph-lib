package com.jasonfunderburker.graphs;

import java.util.List;

public interface Graph<T> {

    /**
     * adds vertex to the graph
     * @param vertex
     */
    void addVertex(T vertex);

    /**
     * adds edge to the graph
     * @param from vertex
     * @param to vertex
     */
    void addEdge(T from, T to);

    /**
     *
     * @param fromVertex
     * @param toVertex
     * @return a list of edges between 2 vertices
     */
    List<T> getPath(T fromVertex, T toVertex);

}
