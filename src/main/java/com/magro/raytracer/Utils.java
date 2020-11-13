package com.magro.raytracer;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import java.util.Random;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Utils {

    private static Random rnd = new Random();

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

    public static Vector3D unitVector(Vector3D v) {
        return v.scalarMultiply(1 / length(v));
    }


    public static Vector3D randomVector() {
        return new Vector3D(rnd.nextDouble(), rnd.nextDouble(), rnd.nextDouble());
    }

    public static Vector3D randomVector(double min, double max) {
        return new Vector3D(min + (max - min) * rnd.nextDouble(), min + (max - min) * rnd.nextDouble(), min + (max - min) * rnd.nextDouble());
    }

    public static Vector3D randomInUnitSphere() {
        while (true) {
            Vector3D p = randomVector(-1, 1);
            if (lengthSquared(p) >= 1) continue;
            return p;
        }
    }

    public static Vector3D randomUnitVector() {
        return unitVector(randomInUnitSphere());
    }

    public static Vector3D randomInHemisphere(Vector3D normal) {
        Vector3D inUnitSphere = randomInUnitSphere();
        if ((inUnitSphere.dotProduct(normal)) > 0.0) // In the same hemisphere as the normal
            return inUnitSphere;
        else
            return inUnitSphere.scalarMultiply(-1);
    }

    public static double length(Vector3D v) {
        return sqrt(lengthSquared(v));
    }

    public static double lengthSquared(Vector3D v) {
        return pow(v.getX(), 2) + pow(v.getY(), 2) + pow(v.getZ(), 2);
    }
}
