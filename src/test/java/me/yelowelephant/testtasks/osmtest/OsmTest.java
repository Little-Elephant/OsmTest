package me.yelowelephant.testtasks.osmtest;

import me.yelowelephant.testtasks.osmtest.entities.Node;
import me.yelowelephant.testtasks.osmtest.steps.OsmTestSteps;
import org.testng.annotations.Test;
import org.assertj.core.api.Assertions;

import java.io.IOException;
import java.util.List;

public class OsmTest {

    public static final double LAT = 59.93823555;
    public static final double LON = 30.2668659740719;
    public static final int DISTANCE = 400;
    public static final int CAFES_QTY = 2;
    public static final int BARS_QTY = 1;
    public static final int TRANSPORT_QTY = 1;

    public static final String BAR_TAG = "amenity=bar";
    public static final String CAFE_TAG = "amenity=cafe";
    public static final String TRANSPORT_TAG = "public_transport=platform";


    private OsmTestSteps osm = new OsmTestSteps();

    @Test(testName = "check for Bar")
    public void checkForBars() throws IOException {
        List<Node> bars = osm.getListByTypeAndRange(BAR_TAG, LAT, LON, DISTANCE);
        Assertions.assertThat(bars.size()).as("Quantity of bars near given location").isGreaterThanOrEqualTo(BARS_QTY);
    }

    @Test(testName = "check for Cafes")
    public void checkForCafes() throws IOException {
        List<Node> bars = osm.getListByTypeAndRange(CAFE_TAG, LAT, LON, DISTANCE);
        Assertions.assertThat(bars.size()).as("Quantity of cafes near given location").isGreaterThanOrEqualTo(CAFES_QTY);
    }

    @Test(testName = "check for transport")
    public void checkForTransport() throws IOException {
        List<Node> bars = osm.getListByTypeAndRange(TRANSPORT_TAG, LAT, LON, DISTANCE);
        Assertions.assertThat(bars.size()).as("Quantity of cafes near given location").isGreaterThanOrEqualTo(TRANSPORT_QTY);
    }

}
