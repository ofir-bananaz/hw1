package homework1;

import java.util.Iterator;

class GeoFeatureTest {
    private static final double tolerance = 0.01;

    private GeoPoint gpZivSquare;
    private GeoPoint gpWest;			// 1 km west to gpZivSquare
    private GeoPoint gpEast;			// 1 km east to gpZivSquare 
    private GeoPoint gpNorth;			// 1 km north to gpZivSquare

    private GeoSegment gsEast;
    private GeoSegment gsWest;
    private GeoSegment gsNorth;
    private GeoSegment gsEast2;
    private GeoSegment gsWest2;

    private GeoSegment gsDiag;

    private GeoFeature gf1;
    private GeoFeature gfadd1;
    private GeoFeature gfadd2;
    private GeoFeature gf2add2;
    private GeoFeature gf3add2diff;



    public GeoFeatureTest() {
        gpZivSquare = new GeoPoint(32783098,35014528);
        gpWest = new GeoPoint(32783098,35003854);
        gpEast = new GeoPoint(32783098,35025202);
        gpNorth = new GeoPoint(32792115,35014528);

        gsEast = new GeoSegment("East", gpZivSquare, gpEast);
        gsWest = new GeoSegment("West", gpZivSquare, gpWest);
        gsNorth = new GeoSegment("North", gpZivSquare, gpNorth);
        gsEast2 = new GeoSegment("East", gpWest, gpZivSquare);
        gsWest2 = new GeoSegment("West", gpWest, gpZivSquare);
        gsDiag = new GeoSegment("NE", gpWest, gpNorth);

        gf1 = new GeoFeature(gsEast.reverse());
    }

    boolean same(double x, double y) {

        // System.out.printf("x: %f ", x);
        // System.out.printf("y: %f\n", y);
        return ((y >= x-tolerance) && (y <= x+tolerance));
    }


    public void show(String str) {
        System.out.println();
        System.out.println("***** " + str + " *****");
    }

    public void show(String str, boolean ok) {
        if (ok)
            System.out.print("v ");
        else
            System.out.print("x ");
        System.out.println(str);
    }

    public void test()
    {
        //inidividual;
        show(gf1.getName());
        show(gf1.getStart().toString());
        show(gf1.getEnd().toString());
        show(String.valueOf(gf1.getStartHeading()));
        show(String.valueOf(gf1.getEndHeading()));
        show(String.valueOf(gf1.getLength()));


        //addone:
        gfadd1 = gf1.addSegment(gsNorth);
        show("Added Segment");
        show(gfadd1.getName());
        show(gfadd1.getStart().toString());
        show(gfadd1.getEnd().toString());
        show(String.valueOf(gfadd1.getStartHeading()));
        show(String.valueOf(gfadd1.getEndHeading()));
        show(String.valueOf(gfadd1.getLength()));


        //3 segments:

        gfadd2 = gfadd1.addSegment(gsDiag.reverse());
        show("Added Segment");
        show(gfadd2.getName());
        show(gfadd2.getStart().toString());
        show(gfadd2.getEnd().toString());
        show(String.valueOf(gfadd2.getStartHeading()));
        show(String.valueOf(gfadd2.getEndHeading()));
        show(String.valueOf(gfadd2.getLength()));

//        Iterator<GeoSegment> itr1 = gfadd2.getGeoSegments();
//        Iterator<GeoSegment> itr2 = gfadd1.getGeoSegments();
//        while(itr1.hasNext() && itr2.hasNext()) {
//            GeoSegment curr1 = itr1.next();
//            GeoSegment curr2 = itr2.next();
//            show(curr1.toString());
//            show(curr2.toString());
//        }


        //add the same
        gf2add2 = gfadd1.addSegment(gsDiag.reverse());
        show("Are Both are the same", gfadd2.equals(gf2add2));


        gf3add2diff = gfadd1.addSegment(gsEast2);
        show("Are differene:", !gfadd2.equals(gf3add2diff));
    }

    public static void main(String[] args) {
        GeoFeatureTest featureTest = new GeoFeatureTest();
        featureTest.test();

    }
}