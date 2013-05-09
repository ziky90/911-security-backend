package utilities;

public class Point {

	private long longtitude;
	private long latiude;
	
	public Point(long longtitude, long latiude) {
		super();
		this.longtitude = longtitude;
		this.latiude = latiude;
	}
	
	public long getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(long longtitude) {
		this.longtitude = longtitude;
	}
	public long getLatiude() {
		return latiude;
	}
	public void setLatitude(long latiude) {
		this.latiude = latiude;
	}
	
	
	
}
