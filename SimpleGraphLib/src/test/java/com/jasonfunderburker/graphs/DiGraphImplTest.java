package com.jasonfunderburker.graphs;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class DiGraphImplTest {

    private Graph<String> jediGraph;

    @Before
    public void setUp() {
        jediGraph = new DiGraphImpl<>(asList("Qui-Gon Jinn", "Obi-Wan Kenobi", "Anakin Skywalker",
                "Yoda", "Luke Skywalker", "Mace Windu", "Leia Organa"));
    }

    @Test
    public void addVertexTest() {
        //when
        jediGraph.addVertex("Rey");
        //then
        assertTrue(jediGraph.getVertices().contains("Rey"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addEdgeWithNonExistVertexTest() {
        //when
        jediGraph.addEdge("Luke Skywalker", "Kylo Ren");
    }

    @Test
    public void addEdgeTest() {
        //when
        jediGraph.addVertex("Kylo Ren");
        //and
        jediGraph.addEdge("Luke Skywalker", "Kylo Ren");
        //then
        assertTrue(jediGraph.getAdjacentVertices("Luke Skywalker").contains("Kylo Ren"));
        assertFalse(jediGraph.getAdjacentVertices("Kylo Ren").contains("Luke Skywalker"));
    }

    @Test
    public void getPathTest() {
        //when
        fillJediMasters();
        System.out.println(jediGraph);
        //and
        List<String> path = jediGraph.getPath("Qui-Gon Jinn", "Leia Organa");
        //then
        jediGraph.printPath(path);
        assertNotNull(path);
        assertTrue(path.containsAll(asList("Qui-Gon Jinn", "Obi-Wan Kenobi", "Luke Skywalker", "Leia Organa")));
    }

    @Test(expected = NoSuchElementException.class)
    public void getPathWhenThereIsNoSuchPathTest() {
        //when
        fillJediMasters();
        //when
        jediGraph.getPath("Qui-Gon Jinn", "Mace Windu");
    }

    private void fillJediMasters() {
        jediGraph.addEdge("Qui-Gon Jinn", "Obi-Wan Kenobi");
        jediGraph.addEdge("Obi-Wan Kenobi", "Anakin Skywalker");
        jediGraph.addEdge("Obi-Wan Kenobi", "Luke Skywalker");
        jediGraph.addEdge("Yoda", "Luke Skywalker");
        jediGraph.addEdge("Yoda", "Mace Windu");
        jediGraph.addEdge("Luke Skywalker", "Leia Organa");
    }
}
