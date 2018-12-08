package homework1;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A Route is a path that traverses arbitrary GeoSegments, regardless
 * of their names.
 * <p>
 * Routes are immutable. New Routes can be constructed by adding a segment 
 * to the end of a Route. An added segment must be properly oriented; that 
 * is, its p1 field must correspond to the end of the original Route, and
 * its p2 field corresponds to the end of the new Route.
 * <p>
 * Because a Route is not necessarily straight, its length - the distance
 * traveled by following the path from start to end - is not necessarily
 * the same as the distance along a straight line between its endpoints.
 * <p>
 * Lastly, a Route may be viewed as a sequence of geographical features,
 * using the <tt>getGeoFeatures()</tt> method which returns an Iterator of
 * GeoFeature objects.
 * <p>
 * <b>The following fields are used in the specification:</b>
 * <pre>
 *   start : GeoPoint            // location of the start of the route
 *   end : GeoPoint              // location of the end of the route
 *   startHeading : angle        // direction of travel at the start of the route, in degrees
 *   endHeading : angle          // direction of travel at the end of the route, in degrees
 *   geoFeatures : sequence      // a sequence of geographic features that make up this Route
 *   geoSegments : sequence      // a sequence of segments that make up this Route
 *   length : real               // total length of the route, in kilometers
 *   endingGeoSegment : GeoSegment  // last GeoSegment of the route
 * </pre>
 **/
public class Route {

    private final GeoPoint start;       // location of the start of the geographic feature
    private final GeoPoint end;         // location of the end of the geographic feature
    private final double startHeading;  // direction of travel at the start of the geographic feature, in degrees
    private final double endHeading;    // direction of travel at the end of the geographic feature, in degrees
    private final ArrayList<GeoSegment> geoSegmentsSequence;      // a sequence of segments that make up this geographic feature
    private final ArrayList<GeoFeature> geoFeatureSequence;      // a sequence of segments that make up this geographic feature
    private final double length;          // total length of the geographic feature, in kilometers
    private final GeoSegment endingGeoSegment;

    // Abstraction Function:
    // Represent a Route rt with GeoSegment gs:
    //<rt.startHeading,rt.endHeading> = <gs.getHeading(),gs.getHeading()>
    //<rt.start,rt.end> = <gs.getP1(),gs.getP2()>
    //rt.length = gs.getLength()

    // Representation invariant for every Route rt:
    // gs != NULL


    /**
  	 * Constructs a new Route.
     * @requires gs != null
     * @effects Constructs a new Route, r, such that
     *	        r.startHeading = gs.heading &&
     *          r.endHeading = gs.heading &&
     *          r.start = gs.p1 &&
     *          r.end = gs.p2
     **/
  	public Route(GeoSegment gs) {
        this.endingGeoSegment = gs;
        this.startHeading = gs.getHeading();
        this.endHeading = gs.getHeading();
        this.start = gs.getP1();
        this.end = gs.getP2();
        this.geoSegmentsSequence = new ArrayList<>();
        this.geoFeatureSequence = new ArrayList<>();
        this.geoSegmentsSequence.add(gs);
        this.length = gs.getLength();
        GeoFeature gf = new GeoFeature(gs);
        this.geoFeatureSequence.add(gf);
        checkRep();
    }

    /**
     * Constructs a new Route with a new GeoSegment ("gsNew") by adding it as a last element to a provided Route.
     * @requires oldRoute != null, gsNew != null
     * @effects Constructs a new Route, r, such that
     *	        r.startHeading = gs.heading &&
     *          r.endHeading = gs.heading &&
     *          r.start = gs.p1 &&
     *          r.end = gs.p2
     **/
    private Route(Route oldRoute, GeoSegment gsNew) {
        GeoSegment gsFirst = oldRoute.getGeoSegmentSequence().get(0);
        double oldLength = oldRoute.getLength();
        this.endingGeoSegment = gsNew;
  	    this.startHeading = gsFirst.getHeading();
        this.endHeading = gsNew.getHeading();
        this.start = gsFirst.getP1();
        this.end = gsNew.getP2();
        this.length = oldLength + gsNew.getLength();
        this.geoSegmentsSequence = new ArrayList<>(oldRoute.getGeoSegmentSequence());
        this.geoSegmentsSequence.add(gsNew);

        // Check if a new feature needed
        this.geoFeatureSequence = new ArrayList<>(oldRoute.getGeoFeatureSequence());

        //fetch old GeoSegment Name
        int lastIndexGf = this.geoFeatureSequence.size() -1;
        GeoFeature gfOld = oldRoute.getGeoFeatureSequence().get(lastIndexGf);
        String endGeoFeatureName = gfOld.getName();

        GeoFeature gfNew;
        if (endGeoFeatureName.equals(gsNew.getName())){
            gfNew = (oldRoute.getGeoFeatureSequence().get(lastIndexGf)).addSegment(gsNew); // adding gsNew to the last feature list.
            this.geoFeatureSequence.remove(lastIndexGf);
        }
        else { //not the same GeoSegment name - new GF to list
            gfNew = new GeoFeature(gsNew);
        }
        this.geoFeatureSequence.add(gfNew);
        checkRep();

    }
    /**
     * checks if rep.invariant is being violated.
     * @throws AssertionError if representation invariant is violated.
     */
    private void checkRep(){
        assert(this.endingGeoSegment!=null):
                "illegal null geoSegment input!";
    }

    /**
     * Returns the arrayList of the GeoSegmentSequence.
     * @return arrayList of the GeoSegmentSequence.
     **/
    private ArrayList<GeoSegment> getGeoSegmentSequence() {
        return this.geoSegmentsSequence;
    }

    /**
     * Returns the arrayList of the geoFeatureSequence.
     * @return the arrayList of the geoFeatureSequence.
     **/
    private ArrayList<GeoFeature> getGeoFeatureSequence() {
        return this.geoFeatureSequence;
    }

    /**
     * Returns location of the start of the route.
     * @return location of the start of the route.
     **/
  	public GeoPoint getStart() {
        return this.start;
  	}


  	/**
  	 * Returns location of the end of the route.
     * @return location of the end of the route.
     **/
  	public GeoPoint getEnd() {
        return this.end;
  	}


  	/**
  	 * Returns direction of travel at the start of the route, in degrees.
   	 * @return direction (in compass heading) of travel at the start of the
   	 *         route, in degrees OR -1.0 if the length of the first segment within geoSegmentArrayList is zero
   	 **/
  	public double getStartHeading() {
        return this.startHeading;
  	}


  	/**
  	 * Returns direction of travel at the end of the route, in degrees.
     * @return direction (in compass heading) of travel at the end of the
     *         route, in degrees OR -1.0 if the length of the last segment within geoSegmentArrayList is zero
     **/
  	public double getEndHeading() {
        return this.endHeading;
  	}


  	/**
  	 * Returns total length of the route.
     * @return total length of the route, in kilometers.  NOTE: this is NOT
     *         as-the-crow-flies, but rather the total distance required to
     *         traverse the route. These values are not necessarily equal.
   	 **/
  	public double getLength() {
        return this.length;
  	}


  	/**
     * Creates a new route that is equal to this route with gs appended to
     * its end.
   	 * @requires gs != null && gs.p1 == this.end
     * @return a new Route r such that
     *         r.end = gs.p2 &&
     *         r.endHeading = gs.heading &&
     *         r.length = this.length + gs.length
     **/
  	public Route addSegment(GeoSegment gs) {
        return new Route(this, gs);
  	}


    /**
     * Returns an Iterator of GeoFeature objects. The concatenation
     * of the GeoFeatures, in order, is equivalent to this route. No two
     * consecutive GeoFeature objects have the same name.
     * @return an Iterator of GeoFeatures such that
     * <pre>
     *      this.start        = a[0].start &&
     *      this.startHeading = a[0].startHeading &&
     *      this.end          = a[a.length - 1].end &&
     *      this.endHeading   = a[a.length - 1].endHeading &&
     *      this.length       = sum(0 <= i < a.length) . a[i].length &&
     *      for all integers i
     *          (0 <= i < a.length - 1 => (a[i].name != a[i+1].name &&
     *                                     a[i].end  == a[i+1].start))
     * </pre>
     * where <code>a[n]</code> denotes the nth element of the Iterator.
     * @see homework1.GeoFeature
     **/
  	public Iterator<GeoFeature> getGeoFeatures() {
        return geoFeatureSequence.iterator();
  	}


  	/**
     * Returns an Iterator of GeoSegment objects. The concatenation of the
     * GeoSegments, in order, is equivalent to this route.
     * @return an Iterator of GeoSegments such that
     * <pre>
     *      this.start        = a[0].p1 &&
     *      this.startHeading = a[0].heading &&
     *      this.end          = a[a.length - 1].p2 &&
     *      this.endHeading   = a[a.length - 1].heading &&
     *      this.length       = sum (0 <= i < a.length) . a[i].length
     * </pre>
     * where <code>a[n]</code> denotes the nth element of the Iterator.
     * @see homework1.GeoSegment
     **/
  	public Iterator<GeoSegment> getGeoSegments() {
  		return geoSegmentsSequence.iterator();
  	}


  	/**
     * Compares the specified Object with this Route for equality.
     * @return true iff (o instanceof Route) &&
     *         (o.geoFeatures and this.geoFeatures contain
     *          the same elements in the same order).
     **/
  	public boolean equals(Object o) {
        if (o != null && (o instanceof Route)) {
            Route RouteObj = Route.class.cast(o);
            Iterator<GeoFeature> itr1 = this.getGeoFeatures();
            Iterator<GeoFeature> itr2 = RouteObj.getGeoFeatures();
            while(itr1.hasNext() && itr2.hasNext()) {
                GeoFeature curr1 = itr1.next();
                GeoFeature curr2 = itr2.next();
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
    	// for improved performance.

    	return 1;
  	}


    /**
     * Returns a string representation of this.
     * @return a string representation of this.
     **/
  	public String toString() {
  		return String.format("Route: Total length-'%s'. Start point-'%s' End Point-'%s', Start Heading-'%f', End Heading-'%f'", this.length, this.start.toString(), this.end.toString(), this.startHeading, this.endHeading);
  	}

}
