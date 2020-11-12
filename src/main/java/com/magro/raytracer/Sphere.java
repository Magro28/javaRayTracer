package com.magro.raytracer;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * A hitable sphere
 *
 * @author Mario Groß
 */
public class Sphere implements Hitable{
    public Vector3D center;
    public double radius;

    public Sphere(){}

    public Sphere(Vector3D center, double radius) {
        this.center=center;
        this.radius = radius;
    }

    /**
     * Returns true when hit by a Ray. Also fills the HitRecord info
     * @param r
     * @param t_min
     * @param t_max
     * @param rec
     * @return
     */
    @Override
    public boolean hit(Ray r, double t_min, double t_max, HitRecord rec) {
        Vector3D oc = r.origin.subtract(center);

        double a = lengthSquared(r.direction);
        double half_b = (oc.dotProduct(r.direction));
        double c = lengthSquared(oc) - (radius * radius);
        double discriminant = (half_b * half_b) - (a * c);

        if (discriminant < 0) {
            return false;
        }
        double sqrtd = sqrt(discriminant);
        // Find the nearest root that lies in the acceptable range.
        double root = (-half_b - sqrtd) / a;
        if (root < t_min || t_max < root) {
            root = (-half_b + sqrtd) / a;
            if (root < t_min || t_max < root)
                return false;
        }
        rec.t = root;
        rec.p = r.at(rec.t);
        Vector3D outward_normal = (rec.p.subtract(center)).scalarMultiply(1.0 / radius);
        rec.setFaceNormal(r, outward_normal);
        return true;

    }

    private double length(Vector3D v)  {
        return sqrt(lengthSquared(v));
    }

    private double lengthSquared(Vector3D v)  {
        return pow(v.getX(),2) + pow(v.getY(),2) + pow(v.getZ(),2);
    }
}
