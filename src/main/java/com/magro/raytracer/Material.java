package com.magro.raytracer;

public interface Material {

    boolean scatter(Ray r_in, HitRecord hitRecord, Color color, Ray scattered);

}
