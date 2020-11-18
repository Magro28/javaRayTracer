package com.magro.raytracer;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class Camera {

    private Vector3D origin;
    private Vector3D lower_left_corner;
    private Vector3D horizontal;
    private Vector3D vertical;

    public Camera(Vector3D lookFrom, Vector3D lookAt, Vector3D vup, double vfov, double aspect_ratio) {

        double theta = Utils.degreesToRadians(vfov);
        double h = Math.tan(theta / 2.0);
        double viewport_height = 2.0 * h;
        double viewport_width = aspect_ratio * viewport_height;
        double focal_length = 1.0;

        Vector3D w = Utils.unitVector(lookFrom.subtract(lookAt));
        Vector3D u = Utils.unitVector(vup.crossProduct(w));
        Vector3D v = w.crossProduct(u);

        origin = lookFrom;
        horizontal = u.scalarMultiply(viewport_width);
        vertical = v.scalarMultiply(viewport_height);
        //origin - horizontal/2 - vertical/2 - w
        lower_left_corner = origin.subtract(horizontal.scalarMultiply(0.5)).subtract(vertical.scalarMultiply(0.5)).subtract(w);
    }

    public Ray getRay(double s, double t) {
        return new Ray(origin, lower_left_corner.add(horizontal.scalarMultiply(s)).add(vertical.scalarMultiply(t)).subtract(origin));
    }
}
