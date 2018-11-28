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
	private double heading;

	// TODO Write abstraction function and representation invariant

	// Abstraction function:
	// represents GeoSegment gs:
	// gs.length is a straight-line distance between p1 and p2, in kilometers
	// gs.heading is compass heading is a nonnegative real number less than 360.
	// gs.name e is given to all GeoSegment objects so that it is possible to
	// differentiate between two GeoSegment objects with identical
	// GeoPoint endpoints.

	// Representation invariant:
	// (name != null), (p1 != null), (p2 != null), (p1 != p2)
	//
	// length is the distance between


	/**
	 * Constructs a new GeoSegment with the specified name and endpoints.
	 * @requires name != null && p1 != null && p2 != null
	 * @effects constructs a new GeoSegment with the specified name and endpoints.
	 **/
	public GeoSegment(String name, homework1.GeoPoint p1, homework1.GeoPoint p2) {
		this.name = name;
		this.p1 = p1;
		this.p2 = p2;
	}


	/**
	 * Returns a new GeoSegment like this one, but with its endpoints reversed.
	 * @return a new GeoSegment gs such that gs.name = this.name
	 *         && gs.p1 = this.p2 && gs.p2 = this.p1
	 **/
	public GeoSegment reverse() {
		GeoSegment sg = new GeoSegment(this.name, this.p2, this.p1);
		return sg;
	}


	/**
	 * Returns the name of this GeoSegment.
	 * @return the name of this GeoSegment.
	 */
	public String getName() {
		return this.name;
	}


	/**
	 * Returns first endpoint of the segment.
	 * @return first endpoint of the segment.
	 */
	public homework1.GeoPoint getP1() {
		return this.p1;
	}


	/**
	 * Returns second endpoint of the segment.
	 * @return second endpoint of the segment.
	 */
	public homework1.GeoPoint getP2() {
		return this.p2;
	}


	/**
	 * Returns the length of the segment.
	 * @return the length of the segment, using the flat-surface, near the
	 *         Technion approximation.
	 */
	public double getLength() {
		return p1.distanceTo(p2);
	}


	/**
	 * Returns the compass heading from p1 to p2.
	 * @requires this.length != 0
	 * @return the compass heading from p1 to p2, in degrees, using the
	 *         flat-surface, near the Technion approximation.
	 **/
	public double getHeading() {
		return p1.headingTo(p2);
	}


	/**
	 * Compares the specified Object with this GeoSegment for equality.
	 * @return gs != null && (gs instanceof GeoSegment)
	 *         && gs.name = this.name && gs.p1 = this.p1 && gs.p2 = this.p2
	 **/
	public boolean equals(Object gs) {
		if (gs != null && (gs instanceof GeoSegment)) {
			GeoSegment gsObj = (GeoSegment) gs;
			if ((gsObj.name).equals(this.name) && (gsObj.p1).equals(this.p1) && gsObj.p2.equals(this.p2))
				return true;
		}
		return false;
	}


	/**
	 * Returns a hash code value for this.
	 * @return a hash code value for this.
	 **/
	public int hashCode() {
		// This implementation will work, but you may want to modify it
		// for improved performance.

		return w`1;
	}


	/**
	 * Returns a string representation of this.
	 * @return a string representation of this.
	 **/
	public String toString() {
		String s = String.format("Geograpic segment: '%s' with length- %s and heading- %f from point Lat: %d Long: %d", this.name, this.length, this.heading, this.heading, this.p1.getLatitude(), this.p1.getLongitude());
		return s;
	}
}

