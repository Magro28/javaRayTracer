package com.magro.raytracer;

public interface Hitable {
    boolean hit(Ray r, double t_min, double t_max, HitRecord rec);
}
