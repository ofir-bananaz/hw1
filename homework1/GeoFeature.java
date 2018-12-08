package homework1;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A GeoFeature represents a route from one location to another along a
 * single geographic feature. GeoFeatures are immutable.
 * <p>
 * GeoFeature abstracts over a geoSegmentArrayList of GeoSegments, all of which have
 * the same name, thus providing a representation for nonlinear or nonatomic
 * geographic features. As an example, a GeoFeature might represent the
 * course of a winding river, or travel along a road through intersections
 * but remaining on the same road.
 * <p>
 * GeoFeatures are immutable. New GeoFeatures can be constructed by adding
 * a segment to the end of a GeoFeature. An added segment must be properly
 * oriented; that is, its p1 field must correspond to the end of the original
 * GeoFeature, and its p2 field corresponds to the end of the new GeoFeature,
 * and the name of the GeoSegment being added must match the name of the
 * existing GeoFeature.
 * <p>
 * Because a GeoFeature is not necessarily straight, its length - the
 * distance traveled by following the path from start to end - is not
 * necessarily the same as the distance along a straight line between
 * its endpoints.
 * <p>
 * <b>The following fields are used in the specification:</b>
 * <pre>
 *   start : GeoPoint       // location of the start of the geographic feature
 *   end : GeoPoint         // location of the end of the geographic feature
 *   startHeading : angle   // direction of travel at the start of the geographic feature, in degrees
 *   endHeading : angle     // direction of travel at the end of the geographic feature, in degrees
 *   geoSegments : geoSegmentArrayList	// a geoSegmentArrayList of segments that make up this geographic feature
 *   name : String          // name of geographic feature
 *   length : real          // total length of the geographic feature, in kilometers
 * </pre>
 **/
public class GeoFeature {

	// Implementation hint:
	// When asked to return an Iterator, consider using the iterator() method
	// in the List interface. Two nice classes that implement the List
	// interface are ArrayList and LinkedList. If comparing two Lists for
	// equality is needed, consider using the equals() method of List. More
	// info can be found at:
	//   http://docs.oracle.com/javase/8/docs/api/java/util/List.html

	private GeoPoint start;                              // location of the start of the geographic feature
    private GeoPoint end;                                // location of the end of the geographic feature
 	private double startHeading;                         // direction of travel at the start of the geographic feature, in degrees
    private double endHeading;                           // direction of travel at the end of the geographic feature, in degrees
    private ArrayList<GeoSegment> geoSegmentArrayList;   // a geoSegmentArrayList of segments that make up this geographic feature
    String name;                                         // name of geographic feature
    private double length;                               // total length of the geographic feature, in kilometers


    // Abstraction function:
    // represents GeoFeature gf:
    // gf.length is the total length of the geographic feature, in kilometers
    // gf.heading is the irection of travel at the start of the geographic feature, in degrees
    // gf.heading is the irection of travel at the start of the geographic feature, in degrees
    // gs.name is the name of the GeoFeature
    // gf.start is the location of the start of the geographic feature
    // gf.end is the location of the end of the geographic feature
    // GeoPoint endpoints.

    // Representation invariant:
    // (name != null), (start != null), (end != null), (start != end), (startHeading != null), (endHeading != null)




    /**
     * Constructs a new GeoFeature.
     * @requires gs != null
     * @effects Constructs a new GeoFeature, r, such that
     *	        r.name = gs.name &&
     *          r.startHeading = gs.heading &&
     *          r.endHeading = gs.heading &&
     *          r.start = gs.p1 &&
     *          r.end = gs.p2
     **/
  	public GeoFeature(GeoSegment gs) {
		this.name = gs.getName();
		this.startHeading = gs.getHeading();
		this.endHeading = gs.getHeading();
		this.start = gs.getP1();
		this.end = gs.getP2();
		this.length = gs.getLength();
		this.geoSegmentArrayList= new ArrayList<>();
		this.geoSegmentArrayList.add(gs);
		checkRep();
  	}

	/**
	 * Constructs a new GeoFeature using a list of GeoSegments w
	 * @requires gs != null, dupList != null, all of which have
	 *  the same name, for each element in the list the next element must correspond to the end point of the element.
	 * @effects Constructs a new GeoFeature, r, such that
	 *	        r.name = gs.name &&
	 *          r.startHeading = gs.heading &&
	 *          r.endHeading = gs.heading &&
	 *          r.start = gs.p1 &&
	 *          r.end = gs.p2
	 **/
	private GeoFeature(GeoSegment gsNew, ArrayList<GeoSegment> dupList) {
		double oldLength =0.0;
		for(GeoSegment itrGs : dupList) {
			oldLength += itrGs.getLength();
		}
		GeoSegment gsFirst = dupList.get(0);
		this.name = gsFirst.getName();
		this.startHeading = gsFirst.getHeading();
		this.endHeading = gsNew.getHeading();
		this.start = gsFirst.getP1();
		this.end = gsNew.getP2();
		this.length = oldLength + gsNew.getLength();
		this.geoSegmentArrayList = new ArrayList<>(dupList);
		this.geoSegmentArrayList.add(gsNew);
		checkRep();
	}

	/**
	 * checks if rep.invariant is being violated.
	 * @throws AssertionError if representation invariant is violated.
	 */
	private void checkRep(){
		assert(this.name!=null):
				"illegal null name input!";
		assert(this.start!=null):
				"illegal null starting point input!";
		assert(this.end!=null):
				"illegal null ending point input!";
		assert(this.geoSegmentArrayList.get(geoSegmentArrayList.size()-1)!=null):
				"illegal null segment input!";
		//double type variables can't be null so no there is no assert for startHeading and endHeading.
	}

	/**
	 * Returns name of geographic feature.
	 * @return name of geographic feature
	 */
	private ArrayList<GeoSegment> getGeoSegment() {
		return this.geoSegmentArrayList;
	}

 	/**
 	  * Returns name of geographic feature.
      * @return name of geographic feature
      */
  	public String getName() {
		return this.name;
  	}


  	/**
  	 * Returns location of the start of the geographic feature.
     * @return location of the start of the geographic feature.
     */
  	public GeoPoint getStart() {
  		return this.start;
  	}


  	/**
  	 * Returns location of the end of the geographic feature.
     * @return location of the end of the geographic feature.
     */
  	public GeoPoint getEnd() {
  		return this.end;
  	}


  	/**
  	 * Returns direction of travel at the start of the geographic feature.
     * @return direction (in standard heading) of travel at the start of the geographic feature,
	 *         in degrees OR -1.0 if the length of the first segment within geoSegmentArrayList is zero
     */
  	public double getStartHeading() {
  		return this.startHeading;
  	}


  	/**
  	 * Returns direction of travel at the end of the geographic feature.
     * @return direction (in standard heading) of travel at the end of the geographic feature,
	 *         in degrees OR -1.0 if the length of the last segment within geoSegmentArrayList is zero
     */
  	public double getEndHeading() {
		return this.endHeading;
  	}


  	/**
  	 * Returns total length of the geographic feature, in kilometers.
     * @return total length of the geographic feature, in kilometers.
     *         NOTE: this is NOT as-the-crow-flies, but rather the total
     *         distance required to traverse the geographic feature. These
     *         values are not necessarily equal.
     */
  	public double getLength() {
  		return this.length;
  	}


  	/**
   	 * Creates a new GeoFeature that is equal to this GeoFeature with gs
   	 * appended to its end.
     * @requires gs != null && gs.p1 = this.end && gs.name = this.name.
     * @return a new GeoFeature r such that
     *         r.end = gs.p2 &&
     *         r.endHeading = gs.heading &&
     *    	   r.length = this.length + gs.length
     **/
  	public GeoFeature addSegment(GeoSegment gs) {
		GeoFeature newGf = new GeoFeature(gs,this.geoSegmentArrayList);
		return newGf;
  	}


  	/**
     * Returns an Iterator of GeoSegment objects. The concatenation of the
     * GeoSegments, in order, is equivalent to this GeoFeature. All the
     * GeoSegments have the same name.
     * @return an Iterator of GeoSegments such that
     * <pre>
     *      this.start        = a[0].p1 &&
     *      this.startHeading = a[0].heading &&
     *      this.end          = a[a.length - 1].p2 &&
     *      this.endHeading   = a[a.length - 1].heading &&
     *      this.length       = sum(0 <= i < a.length) . a[i].length &&
     *      for all integers i
     *          (0 <= i < a.length-1 => (a[i].name == a[i+1].name &&
     *                                   a[i].p2d  == a[i+1].p1))
     * </pre>
     * where <code>a[n]</code> denotes the nth element of the Iterator.
     * @see homework1.GeoSegment
     */
  	public Iterator<GeoSegment> getGeoSegments() {
  		return geoSegmentArrayList.iterator();
  	}


  	/**
     * Compares the argument with this GeoFeature for equality.
     * @return o != null && (o instanceof GeoFeature) &&
     *         (o.geoSegments and this.geoSegments contain
     *          the same elements in the same order).
     **/
  	public boolean equals(Object o) {
		if (o != null && (o instanceof GeoFeature)) {
			GeoFeature gfObj = GeoFeature.class.cast(o);
			Iterator<GeoSegment> itr1 = this.getGeoSegments();
			Iterator<GeoSegment> itr2 = gfObj.getGeoSegments();
			while(itr1.hasNext() && itr2.hasNext()) {
				GeoSegment curr1 = itr1.next();
				GeoSegment curr2 = itr2.next();
				if (!curr1.equals(curr2)) {
					return false;
				}
			}
			if (!itr1.hasNext()  && !itr2.hasNext()){ // both are false -> both has no more elements
				return true;
			}
		}
		return false;
  	}


  	/**
     * Returns a hash code for this.
     * @return a hash code for this.
     **/
  	public int hashCode() {
    	// This implementation will work, but you may want to modify it
    	// improved performance.

    	return 1;
  	}


  	/**
  	 * Returns a string representation of this.
   	 * @return a string representation of this.
     **/
  	public String toString() {
		String s = String.format("Geographic Feature: '%s' with Total length of - %s. Start point-'%s' End Point-'%s', , Start Heading-'%f', End Heading-'%f'", this.name, this.length, this.start.toString(), this.end.toString(),this.startHeading, this.endHeading);
		return s;
  	}
}
