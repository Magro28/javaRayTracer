package com.magro.raytracer;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class Camera {

    private Vector3D origin;
    private Vector3D lower_left_corner;
    private Vector3D horizontal;
    private Vector3D vertical;
    private double lens_radius;
    private Vector3D w;
    private Vector3D u;
    private Vector3D v;


    public Camera(Vector3D lookFrom, Vector3D lookAt, Vector3D vup, double vfov, double aspect_ratio, double aperture, double focus_dist) {

        double theta = Utils.degreesToRadians(vfov);
        double h = Math.tan(theta / 2.0);
        double viewport_height = 2.0 * h;
        double viewport_width = aspect_ratio * viewport_height;

        this.w = Utils.unitVector(lookFrom.subtract(lookAt));
        this.u = Utils.unitVector(vup.crossProduct(w));
        this.v = w.crossProduct(u);

        this.origin = lookFrom;
        this.horizontal = u.scalarMultiply(viewport_width).scalarMultiply(focus_dist);
        this.vertical = v.scalarMultiply(viewport_height).scalarMultiply(focus_dist);
        //origin - horizontal/2 - vertical/2 - w*focus_dist
        this.lower_left_corner = origin.subtract(horizontal.scalarMultiply(0.5)).subtract(vertical.scalarMultiply(0.5)).subtract(w.scalarMultiply(focus_dist));
        this.lens_radius = aperture / 2;
    }

    public Ray getRay(double s, double t) {
        Vector3D rd = Utils.randomInUnitDisk().scalarMultiply(lens_radius);
        Vector3D offset = u.scalarMultiply(rd.getX()).add(v.scalarMultiply(rd.getY()));
        //lower_left_corner + s*horizontal + t*vertical - origin - offset
        return new Ray(origin.add(offset), lower_left_corner.add(horizontal.scalarMultiply(s)).add(vertical.scalarMultiply(t)).subtract(origin).subtract(offset));
    }
}
