package com.jasonfunderburker.graphs;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public abstract class BaseGraphImpl<T> implements Graph<T> {

    protected Map<T, List<T>> adjVertices = new HashMap<>();

    public BaseGraphImpl(Collection<T> vertices) {
        if (nonNull(vertices)) {
            vertices.forEach(this::addVertex);
        }
    }

    @Override
    public void addVertex(T vertex) {
        adjVertices.putIfAbsent(vertex, new LinkedList<>());
    }

    @Override
    public List<T> getPath(T from, T to) {
        checkVertex(from);
        checkVertex(to);

        Set<T> visited = new LinkedHashSet<>();
        Stack<T> toVisit = new Stack<>();

        toVisit.push(from);
        while (!toVisit.isEmpty()) {
            T current = toVisit.pop();
            if (!visited.contains(current)) {
                visited.add(current);
                if (to.equals(current)) {
                    return new ArrayList<>(visited);
                }
                adjVertices.get(current).forEach(toVisit::push);
            }
        }
        throw new NoSuchElementException("There's no path from vertex: "+from+ " to vertex: "+to);
    }

    @Override
    public void printPath(List<T> path) {
        System.out.println(path.stream()
                .map(Objects::toString)
                .collect(Collectors.joining(" -> "))
        );
    }

    @Override
    public Set<T> getVertices() {
        return adjVertices.keySet();
    }

    @Override
    public List<T> getAdjacentVertices(T vertex) {
        checkVertex(vertex);
        return adjVertices.get(vertex);
    }

    protected void checkVertex(T vertex) {
        if (!adjVertices.containsKey(vertex)) {
            throw new IllegalArgumentException("Vertex: "+ vertex+" not found in graph");
        }
    }

    @Override
    public String toString() {
        return "Graph:" + adjVertices;
    }
}
