package com.jasonfunderburker.graphs;

import java.util.List;
import java.util.Set;

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
     * @return a list of edges between 2 vertices or throw NoSuchElementException if there's no path
     */
    List<T> getPath(T fromVertex, T toVertex);

    /**
     * Representation of the path
     * @param path
     */
    void printPath(List<T> path);

    /**
     * @return set of vertices
     */
    Set<T> getVertices();

    /**
     *
     * @param vertex
     * @return List of adjacent vertices for input vertex
     */
    List<T> getAdjacentVertices(T vertex);
}
