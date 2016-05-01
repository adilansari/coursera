package roadgraph;

import geography.GeographicPoint;

public class MapEdge {
    private GeographicPoint source;
    private GeographicPoint destination;
    private String name;
    private String type;
    private double length;

    public MapEdge(GeographicPoint source, GeographicPoint destination, String name, String type, double length) {
        this.source = source;
        this.destination = destination;
        this.name = name;
        this.type = type;
        this.length = length;
    }

    public GeographicPoint getSource() {
        return source;
    }

    public void setSource(GeographicPoint source) {
        this.source = source;
    }

    public GeographicPoint getDestination() {
        return destination;
    }

    public void setDestination(GeographicPoint destination) {
        this.destination = destination;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
}
