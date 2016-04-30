package roadgraph;

import geography.GeographicPoint;

import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.Set;

public class VertexDistanceMap {

    private HashMap<GeographicPoint, Double> map;

    public VertexDistanceMap(Set<GeographicPoint> vertices) {
        this.map = new HashMap<>();
        for (GeographicPoint v: vertices){
            map.put(v, Double.MAX_VALUE);
        }
    }

    public double getDistance(GeographicPoint v){
        return this.map.get(v);
    }

    public void updateDistance(GeographicPoint v, double distance){
        this.map.put(v, distance);
    }
}
