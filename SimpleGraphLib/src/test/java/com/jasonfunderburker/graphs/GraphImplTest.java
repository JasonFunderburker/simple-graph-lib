package com.jasonfunderburker.graphs;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class GraphImplTest {

    private Graph<String> transportGraph;

    @Before
    public void setUp() {
        transportGraph = new GraphImpl<>(asList(
                "Metro A1", "Metro A2", "Metro A3", "Metro A4",
                "Metro C1", "Metro C2", "Metro C3",
                "Bus 1", "Bus 2", "Bus 3"));
    }

    @Test
    public void addVertexTest() {
        //when
        transportGraph.addVertex("Metro D1");
        //then
        assertTrue(transportGraph.getVertices().contains("Metro D1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addEdgeWithNonExistVertexTest() {
        //when
        transportGraph.addEdge("Metro F4", "Bus 2");
    }

    @Test
    public void addEdgeTest() {
        //when
        transportGraph.addEdge("Metro A2", "Metro C2");
        //then
        assertTrue(transportGraph.getAdjacentVertices("Metro A2").contains("Metro C2"));
        assertTrue(transportGraph.getAdjacentVertices("Metro C2").contains("Metro A2"));
    }

    @Test
    public void getPathTest() {
        //when
        fillTransportConnections();
        System.out.println(transportGraph);
        //and
        List<String> path = transportGraph.getPath("Metro A4", "Metro C3");
        //then
        transportGraph.printPath(path);
        assertNotNull(path);
        assertTrue(path.containsAll(asList("Metro A4", "Metro A3", "Metro A2", "Metro C2", "Metro C3")));
    }

    @Test(expected = NoSuchElementException.class)
    public void getPathWhenThereIsNoSuchPathTest() {
        //when
        fillTransportConnections();
        //when
        transportGraph.getPath("Metro C1", "Bus 2");
    }

    private void fillTransportConnections() {
        transportGraph.addEdge("Metro A1", "Metro A2");
        transportGraph.addEdge("Metro A2", "Metro A3");
        transportGraph.addEdge("Metro A3", "Metro A4");

        transportGraph.addEdge("Metro C1", "Metro C2");
        transportGraph.addEdge("Metro C2", "Metro C3");

        transportGraph.addEdge("Metro A2", "Metro C2");

        transportGraph.addEdge("Bus 1", "Bus 2");
        transportGraph.addEdge("Bus 2", "Bus 3");
        transportGraph.addEdge("Bus 3", "Bus 1");
    }
}
