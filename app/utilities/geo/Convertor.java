package utilities.geo;

import java.util.ArrayList;
import java.util.List;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Polygon;

import utilities.Point;

public class Convertor {

	/**
	 * method that creates PostGis form point from lat lon
	 * @param lat
	 * @param lon
	 * @return
	 */
	public static String pointFromCoordinates(double lat, double lon){
		StringBuilder builder = new StringBuilder();
		builder.append("POINT (").append(lon).append(" ").append(lat).append(")");
		return builder.toString();
	}
	
	/**
	 * method that makes polygon PostGis compatible format from list of points
	 * @param points
	 * @return
	 */
	public static String pointsToPolygon(List<Point> points){
		StringBuilder builder = new StringBuilder();
		if(points.size() < 3){
			return null;
		}
		builder.append("POLYGON ((");
		for(Point p : points){
			builder.append(p.getLongtitude()).append(" ").append(p.getLatiude()).append(", ");
		}
		builder.delete(builder.length()-2, builder.length()-1);
		builder.append("))");
		return builder.toString();
	}
	
	
	public static List<Point> polygonToPoints(Polygon p){
		List<Point> points = new ArrayList<Point>();
		
		for(Coordinate c : p.getCoordinates()){
			points.add(new Point((long) c.x, (long) c.y));
		}
		
		return points;
	}
	
}
