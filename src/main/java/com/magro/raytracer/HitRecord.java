package com.magro.raytracer;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 * Infos about the hit
 *
 * @author Mario Groß
 */
public class HitRecord {
    public boolean hit = false;
    public Vector3D p;
    public Vector3D normal;
    public double t;
    public boolean front_face;
    public Material material;

    public void setFaceNormal(Ray r, Vector3D outward_normal) {
        this.front_face = (r.direction.dotProduct(outward_normal)) < 0;
        this.normal = this.front_face ? outward_normal : outward_normal.scalarMultiply(-1);
    }
}
