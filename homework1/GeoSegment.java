package homework1;


/**
 * A GeoSegment models a straight line segment on the earth. GeoSegments
 * are immutable.
 * <p>
 * A compass heading is a nonnegative real number less than 360. In compass
 * headings, north = 0, east = 90, south = 180, and west = 270.
 * <p>
 * When used in a map, a GeoSegment might represent part of a street,
 * boundary, or other feature.
 * As an example usage, this map
 * <pre>
 *  Trumpeldor   a
 *  Avenue       |
 *               i--j--k  Hanita
 *               |
 *               z
 * </pre>
 * could be represented by the following GeoSegments:
 * ("Trumpeldor Avenue", a, i), ("Trumpeldor Avenue", z, i),
 * ("Hanita", i, j), and ("Hanita", j, k).
 * </p>
 *
 * </p>
 * A name is given to all GeoSegment objects so that it is possible to
 * differentiate between two GeoSegment objects with identical
 * GeoPoint endpoints. Equality between GeoSegment objects requires
 * that the names be equal String objects and the end points be equal
 * GeoPoint objects.
 * </p>
 *
 * <b>The following fields are used in the specification:</b>
 * <pre>
 *   name : String       // name of the geographic feature identified
 *   p1 : GeoPoint       // first endpoint of the segment
 *   p2 : GeoPoint       // second endpoint of the segment
 *   length : real       // straight-line distance between p1 and p2, in kilometers
 *   heading : angle     // compass heading from p1 to p2, in degrees
 * </pre>
 **/
public class GeoSegment  {

	private String name;
	private homework1.GeoPoint p1;
	private homework1.GeoPoint p2;
	private double length;
	private double angle;

	// TODO Write abstraction function and representation invariant

	// Abstraction function:
	// represents GeoSegment gs:
	// gs.length is a straight-line distance between p1 and p2, in kilometers
	// gs.heading is compass heading from p1 to p2, in degrees

	// Representation invariant:
	// (name != null), (GeoSegment.p1 != null), (GeoSegment.p2 != null), (GeoSegment.p1 != GeoSegment.p2)
	// name is a Geographic Segment's short description


	/**
	 * Constructs a new GeoSegment with the specified name and endpoints.
	 * @requires name != null && p1 != null && p2 != null
	 * @effects constructs a new GeoSegment with the specified name and endpoints.
	 **/
	public GeoSegment(String name, homework1.GeoPoint p1, homework1.GeoPoint p2) {
		// TODO Implement this method
	}


	/**
	 * Returns a new GeoSegment like this one, but with its endpoints reversed.
	 * @return a new GeoSegment gs such that gs.name = this.name
	 *         && gs.p1 = this.p2 && gs.p2 = this.p1
	 **/
	public GeoSegment reverse() {
		// TODO Implement this method
	}


	/**
	 * Returns the name of this GeoSegment.
	 * @return the name of this GeoSegment.
	 */
	public String getName() {
		// TODO Implement this method
	}


	/**
	 * Returns first endpoint of the segment.
	 * @return first endpoint of the segment.
	 */
	public homework1.GeoPoint getP1() {
		// TODO Implement this method
	}


	/**
	 * Returns second endpoint of the segment.
	 * @return second endpoint of the segment.
	 */
	public homework1.GeoPoint getP2() {
		// TODO Implement this method
	}


	/**
	 * Returns the length of the segment.
	 * @return the length of the segment, using the flat-surface, near the
	 *         Technion approximation.
	 */
	public double getLength() {
		Geo
		// TODO Implement this method
	}


	/**
	 * Returns the compass heading from p1 to p2.
	 * @requires this.length != 0
	 * @return the compass heading from p1 to p2, in degrees, using the
	 *         flat-surface, near the Technion approximation.
	 **/
	public double getHeading() {
		// TODO Implement this method
	}


	/**
	 * Compares the specified Object with this GeoSegment for equality.
	 * @return gs != null && (gs instanceof GeoSegment)
	 *         && gs.name = this.name && gs.p1 = this.p1 && gs.p2 = this.p2
	 **/
	public boolean equals(Object gs) {
		// TODO Implement this method
	}


	/**
	 * Returns a hash code value for this.
	 * @return a hash code value for this.
	 **/
	public int hashCode() {
		// This implementation will work, but you may want to modify it
		// for improved performance.

		return 1;
	}


	/**
	 * Returns a string representation of this.
	 * @return a string representation of this.
	 **/
	public String toString() {
		// TODO Implement this method
	}

}

