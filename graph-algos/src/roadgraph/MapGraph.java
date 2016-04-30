/**
 * @author UCSD MOOC development team and YOU
 * <p>
 * A class which reprsents a graph of geographic locations
 * Nodes in the graph are intersections between
 */
package roadgraph;


import java.util.*;
import java.util.function.Consumer;

import geography.GeographicPoint;
import util.GraphLoader;

/**
 * @author UCSD MOOC development team and YOU
 *         <p>
 *         A class which represents a graph of geographic locations
 *         Nodes in the graph are intersections between
 */
public class MapGraph {
    //TODO: Add your member variables here in WEEK 2
    private Map<GeographicPoint, LinkedList<Edge>> graph;


    /**
     * Create a new empty MapGraph
     */
    public MapGraph() {
        // TODO: Implement in this constructor in WEEK 2
        graph = new HashMap<>();
    }

    /**
     * Get the number of vertices (road intersections) in the graph
     *
     * @return The number of vertices in the graph.
     */
    public int getNumVertices() {
        //TODO: Implement this method in WEEK 2
        return graph.size();
    }

    /**
     * Return the intersections, which are the vertices in this graph.
     *
     * @return The vertices in this graph as GeographicPoints
     */
    public Set<GeographicPoint> getVertices() {
        //TODO: Implement this method in WEEK 2
        return graph.keySet();
    }

    /**
     * Get the number of road segments in the graph
     *
     * @return The number of edges in the graph.
     */
    public int getNumEdges() {
        //TODO: Implement this method in WEEK 2
        int edges = 0;
        for (List e : graph.values()) {
            edges += e.size();
        }
        return edges;
    }

    public LinkedList<Edge> getNeighbors(GeographicPoint v) {
        return graph.get(v);
    }

    /**
     * Add a node corresponding to an intersection at a Geographic Point
     * If the location is already in the graph or null, this method does
     * not change the graph.
     *
     * @param location The location of the intersection
     * @return true if a node was added, false if it was not (the node
     * was already in the graph, or the parameter is null).
     */
    public boolean addVertex(GeographicPoint location) {
        // TODO: Implement this method in WEEK 2
        if (!graph.containsKey(location))
            graph.put(location, new LinkedList<>());
        return false;
    }

    /**
     * Adds a directed edge to the graph from pt1 to pt2.
     * Precondition: Both GeographicPoints have already been added to the graph
     *
     * @param from     The starting point of the edge
     * @param to       The ending point of the edge
     * @param roadName The name of the road
     * @param roadType The type of the road
     * @param length   The length of the road, in km
     * @throws IllegalArgumentException If the points have not already been
     *                                  added as nodes to the graph, if any of the arguments is null,
     *                                  or if the length is less than 0.
     */
    public void addEdge(GeographicPoint from, GeographicPoint to, String roadName,
                        String roadType, double length) throws IllegalArgumentException {

        //TODO: Implement this method in WEEK 2
        if (!(graph.containsKey(from) || graph.containsKey(to)))
            throw new IllegalArgumentException();

        Edge edge = new Edge(from, to, roadName, roadType, length);
        graph.get(from).add(edge);
    }


    /**
     * Find the path from start to goal using breadth first search
     *
     * @param start The starting location
     * @param goal  The goal location
     * @return The list of intersections that form the shortest (unweighted)
     * path from start to goal (including both start and goal).
     */
    public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {
        // Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {
        };
        return bfs(start, goal, temp);
    }

    /**
     * Find the path from start to goal using breadth first search
     *
     * @param start        The starting location
     * @param goal         The goal location
     * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
     * @return The list of intersections that form the shortest (unweighted)
     * path from start to goal (including both start and goal).
     */
    public List<GeographicPoint> bfs(GeographicPoint start,
                                     GeographicPoint goal, Consumer<GeographicPoint> nodeSearched) {
        // TODO: Implement this method in WEEK 2

        // Hook for visualization.  See writeup.
        //nodeSearched.accept(next.getLocation());
        LinkedList<GeographicPoint> queue = new LinkedList<>();
        LinkedHashSet<GeographicPoint> visited = new LinkedHashSet<>();
        HashMap<GeographicPoint, GeographicPoint> parentMap = new HashMap<>();
        queue.add(start);
        visited.add(start);
        parentMap.put(start, null);
        GeographicPoint current = null;

        while (!(queue.isEmpty() || goal.equals(current))) {
            current = queue.pollFirst();

            for (Edge e : getNeighbors(current)) {
                GeographicPoint n = e.getDestination();
                if (visited.contains(n))
                    continue;
                visited.add(n);
                parentMap.put(n, current);
                queue.add(n);
            }
        }

        if (goal.equals(current)) {
            LinkedList<GeographicPoint> result = new LinkedList<>();
            while (current != null) {
                result.add(current);
                current = parentMap.get(current);
            }
            Collections.reverse(result);
            return result;
        }

        return null;
    }


    /**
     * Find the path from start to goal using Dijkstra's algorithm
     *
     * @param start The starting location
     * @param goal  The goal location
     * @return The list of intersections that form the shortest path from
     * start to goal (including both start and goal).
     */
    public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal) {
        // Dummy variable for calling the search algorithms
        // You do not need to change this method.
        Consumer<GeographicPoint> temp = (x) -> {
        };
        return dijkstra(start, goal, temp);
    }

    /**
     * Find the path from start to goal using Dijkstra's algorithm
     *
     * @param start        The starting location
     * @param goal         The goal location
     * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
     * @return The list of intersections that form the shortest path from
     * start to goal (including both start and goal).
     */
    public List<GeographicPoint> dijkstra(GeographicPoint start,
                                          GeographicPoint goal, Consumer<GeographicPoint> nodeSearched) {
        // TODO: Implement this method in WEEK 3

        // Hook for visualization.  See writeup.
        //nodeSearched.accept(next.getLocation());
        PriorityQueue<VertexDistanceVector> queue = new PriorityQueue<>(new Comparator<VertexDistanceVector>() {
            @Override
            public int compare(VertexDistanceVector o1, VertexDistanceVector o2) {
                return (int) (o1.getDistance() - o2.getDistance());
            }
        });
        LinkedHashSet<GeographicPoint> visited = new LinkedHashSet<>();
        HashMap<GeographicPoint, GeographicPoint> parentMap = new HashMap<>();
        VertexDistanceMap distanceMap = new VertexDistanceMap(getVertices());

        distanceMap.updateDistance(start, 0);
        queue.add(new VertexDistanceVector(start, 0));
        parentMap.put(start, null);

        VertexDistanceVector current = null;

        while (!queue.isEmpty()) {
            current = queue.poll();

            if (visited.contains(current.getVertex()))
                continue;
            visited.add(current.getVertex());

            if (current.getVertex().equals(goal))
                break;

            for(Edge e: getNeighbors(current.getVertex())){
                GeographicPoint n = e.getDestination();
                double newDistance = current.getDistance() + e.getLength();

                if (newDistance > distanceMap.getDistance(n))
                    continue;
                distanceMap.updateDistance(n, newDistance);
                parentMap.put(n, current.getVertex());
                queue.add(new VertexDistanceVector(n, newDistance));
            }
        }

        if (goal.equals(current.getVertex())) {
            LinkedList<GeographicPoint> result = new LinkedList<>();
            GeographicPoint currentVertex = current.getVertex();
            while (currentVertex != null) {
                result.add(currentVertex);
                currentVertex = parentMap.get(currentVertex);
            }
            Collections.reverse(result);
            return result;
        }

        return null;
    }

    /**
     * Find the path from start to goal using A-Star search
     *
     * @param start The starting location
     * @param goal  The goal location
     * @return The list of intersections that form the shortest path from
     * start to goal (including both start and goal).
     */
    public List<GeographicPoint> aStarSearch(GeographicPoint start, GeographicPoint goal) {
        // Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {
        };
        return aStarSearch(start, goal, temp);
    }

    /**
     * Find the path from start to goal using A-Star search
     *
     * @param start        The starting location
     * @param goal         The goal location
     * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
     * @return The list of intersections that form the shortest path from
     * start to goal (including both start and goal).
     */
    public List<GeographicPoint> aStarSearch(GeographicPoint start,
                                             GeographicPoint goal, Consumer<GeographicPoint> nodeSearched) {
        // TODO: Implement this method in WEEK 3

        // Hook for visualization.  See writeup.
        //nodeSearched.accept(next.getLocation());

        return null;
    }


    public static void main(String[] args) {
        System.out.print("Making a new map...");
        MapGraph theMap = new MapGraph();
        System.out.print("DONE. \nLoading the map...");
        GraphLoader.loadRoadMap("data/testdata/simpletest.map", theMap);
        System.out.println("DONE.");

        // You can use this method for testing.

		/* Use this code in Week 3 End of Week Quiz
        MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		System.out.println("DONE.");

		GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
		GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);
		
		
		List<GeographicPoint> route = theMap.dijkstra(start,end);
		List<GeographicPoint> route2 = theMap.aStarSearch(start,end);

		*/

    }

}
