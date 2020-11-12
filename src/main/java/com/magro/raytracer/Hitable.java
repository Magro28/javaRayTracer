package com.magro.raytracer;

/**
 * All Objects which are hitable by Rays
 *
 * @author Mario Groß
 */
public interface Hitable {
    HitRecord hit(Ray r, double t_min, double t_max, HitRecord rec);
}
