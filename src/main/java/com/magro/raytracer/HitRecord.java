package com.magro.raytracer;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 * Infos about the hit
 *
 * @author Mario Groß
 */
public class HitRecord {
    public Vector3D p;
    public Vector3D normal;
    public double t;
    public boolean front_face;

    public void setFaceNormal(Ray r, Vector3D outward_normal) {
        front_face = (r.direction.dotProduct(outward_normal)) < 0;
        normal = front_face ? outward_normal :outward_normal.scalarMultiply(-1);
    }
}
