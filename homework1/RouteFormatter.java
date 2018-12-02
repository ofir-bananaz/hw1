package homework1;


import java.util.Iterator;

/**
 * A RouteFormatter class knows how to create a textual description of
 * directions from one location to another. The class is abstract to
 * support different textual descriptions.
 */
public abstract class RouteFormatter {

    // Abstraction Function:
    // Create a textual description of directions, using the initial heading and the route.

    // Representation invariant for every RouteFormatter (and extensions):
    // (0<=heading<360) && (route!=null)

  	/**
     * Give directions for following this Route, starting at its start point
     * and facing in the specified heading.
     * @requires route != null && 
     * 			0 <= heading < 360
     * @param route the route for which to print directions.
   	 * @param heading the initial heading.
     * @return A newline-terminated directions <tt>String</tt> giving
     * 	       human-readable directions from start to end along this route.
     **/
  	public String computeDirections(Route route, double heading) {

  		// Implementation hint:
		// This method should call computeLine() for each geographic
		// feature in this route and concatenate the results into a single
		// String.

        Iterator<GeoFeature> iterator = route.getGeoFeatures();
        double currentHeading = heading;
        String newDirection="";
        while (iterator.hasNext()) {
            GeoFeature gf = iterator.next();
            newDirection += computeLine(gf, currentHeading);
            currentHeading=gf.getStartHeading();
        }
        return newDirection;
  	}


  	/**
     * Computes a single line of a multi-line directions String that
     * represents the instructions for traversing a single geographic
     * feature.
     * @requires geoFeature != null
     * @param geoFeature the geographical feature to traverse.
   	 * @param origHeading the initial heading.
     * @return A newline-terminated <tt>String</tt> that gives directions
     * 		   on how to traverse this geographic feature.
     */
  	public abstract String computeLine(GeoFeature geoFeature, double origHeading);


  	/**
     * Computes directions to turn based on the heading change.
     * @requires 0 <= oldHeading < 360 &&
     *           0 <= newHeading < 360
     * @param origHeading the start heading.
   	 * @param newHeading the desired new heading.
     * @return English directions to go from the old heading to the new
     * 		   one. Let the angle from the original heading to the new
     * 		   heading be a. The turn should be annotated as:
     * <p>
     * <pre>
     * Continue             if a < 10
     * Turn slight right    if 10 <= a < 60
     * Turn right           if 60 <= a < 120
     * Turn sharp right     if 120 <= a < 179
     * U-turn               if 179 <= a
     * </pre>
     * and likewise for left turns.
     */
  	protected String getTurnString(double origHeading, double newHeading) {
  	    String direction="";
  	    if(newHeading<=180)
  	        direction="right";
  	    else
  	        direction="left";

  		double angle = Math.min((origHeading-newHeading)<0?origHeading-newHeading
                +360:origHeading-newHeading, (newHeading-origHeading)<0?newHeading-origHeading
                +360:newHeading-origHeading);
                //(newHeading -origHeading)%360;
            if(angle<10)
                return "Continue";
            else if(angle<60)
                    return "Turn slight "+direction;
            else if(angle<120)
                return "Turn " +direction;
            else if(angle<179)
                return "Turn sharp "+direction;
            else
                return "U-turn";
        }

  	}

