package com.magro.raytracer;

public class Utils {

    // Constants

    public static final double infinity = Double.POSITIVE_INFINITY;

    // Utility Functions

    public static double degreesToRadians(double degrees) {
        return degrees * Math.PI / 180.0;
    }

    public static double clamp(double x, double min, double max) {
        if (x < min) return min;
        if (x > max) return max;
        return x;
    }
}
