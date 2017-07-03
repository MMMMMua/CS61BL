import java.util.*;
public class TracedPoint extends Point {

    public TracedPoint(int x, int y) {
        super(x, y);
    }

    // public void move(int x, int y) {
    //     System.out.print("point moved from" + super.toString());
    //     super.move(x, y);
    //     System.out.println(" to " + super.toString());
    // }
	public static void moveto79(Point p) {
		p.move(7, 9);
	}
	public static void anothermoverto79(TracedPoint p) {
		p.move(7, 9);
	}
    public static void main (String[] args) {
        TracedPoint p1 = new TracedPoint(5, 6);
        p1.move(3, 4); // prints: "point moved from (5,6) to (3,4)
        p1.move(9, 10); // prints: "point moved from (3,4) to (9,10)

        TracedPoint p2 = new TracedPoint (25, 30);
        p2.move(45, 50); // prints: "point moved from (25,30) to (45,50)

        System.out.println("p1 is " + p1);
        System.out.println("p2 is " + p2);

		Point p = new Point(3, 4);
		moveto79(p);
		System.out.println(p);
		moveto79(p1);
		anothermoverto79(p1);

		ArrayList<Point> a = new ArrayList<Point>();
		a.add(new TracedPoint(5, 6));
		a.add(new Point(10, 11));
		for (int k = 0; k < a.size(); k++) {
			a.get(k).move(7, 9);
		}
    }
}
