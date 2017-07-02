/** A class that represents a path via pursuit curves.
 *  @author Surprise MotherF**ker.
 */

public class Path{

    /** What to do, what to do... */
	private Point currPoint, nextPoint;

	public Path(double x, double y) {
	    this.currPoint = new Point(x, y);
		this.nextPoint = new Point(x, y);
	}
	
	public double getCurrX() { return this.currPoint.getX(); }
	public double getCurrY() { return this.currPoint.getY(); }
	public double getNextX() { return this.nextPoint.getX(); }
	public double getNextY() { return this.nextPoint.getY(); }
	
	public Point getCurrentPoint() { return new Point(this.currPoint); }
	public void setCurrentPoint(Point point) { this.currPoint = new Point(point); }
	
	public void iterate(double dx, double dy) {
		this.currPoint = new Point(this.nextPoint);
		this.nextPoint = new Point(this.nextPoint.getX() + dx, this.nextPoint.getY() + dy);
	}
}

