package edu.nd.cse.ids.project.tests;

import edu.nd.cse.ids.project.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoordCosineTest {
    @Test
    public void testCoords() {
        Main m1 = new Main();
        // Computing using the coordinates of Moscow and Boston
        DestInfo dest1 = new DestInfo(-71.058884, 42.360081, 10000, "Warm");
        DestInfo dest2 = new DestInfo(37.617298, 55.755825, 10000, "Cold");
        double distance = m1.coordCosineDistance(dest1, dest2);
        assertEquals(Math.round(distance * 10000.0) / 10000.0, 1);
    }
}