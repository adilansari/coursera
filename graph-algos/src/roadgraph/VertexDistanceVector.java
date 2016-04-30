package roadgraph;

import geography.GeographicPoint;

public class VertexDistanceVector {
    private GeographicPoint vertex;
    private double distance;

    public VertexDistanceVector(GeographicPoint vertex) {
        this.vertex = vertex;
        this.distance = Integer.MAX_VALUE;
    }

    public VertexDistanceVector(GeographicPoint vertex, double distance) {
        this.vertex = vertex;
        this.distance = distance;
    }

    public GeographicPoint getVertex() {
        return vertex;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
