package com.magro.raytracer;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class Camera {
    private Vector3D origin;
    private Vector3D lower_left_corner;
    private Vector3D horizontal;
    private Vector3D vertical;

    public Camera() {
        double aspect_ratio = 16.0 / 9.0;
        double viewport_height = 2.0;
        double viewport_width = aspect_ratio * viewport_height;
        double focal_length = 1.0;

        origin = new Vector3D(0, 0, 0);
        horizontal = new Vector3D(viewport_width, 0.0, 0.0);
        vertical = new Vector3D(0.0, viewport_height, 0.0);
        //Calculate origin - horizontal/2 - vertical/2 - vec3(0, 0, focal_length);
        lower_left_corner = origin.subtract(horizontal.scalarMultiply(0.5)).subtract(vertical.scalarMultiply(0.5)).subtract(new Vector3D(0, 0, focal_length));

    }

    public Ray getRay(double u, double v) {
        //Calculate Direction: lower_left_corner + u*horizontal + v*vertical - origin
        return new Ray(origin, lower_left_corner.add(horizontal.scalarMultiply(u)).add(vertical.scalarMultiply(v)).subtract(origin));
    }
}
