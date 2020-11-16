package com.magro.raytracer.materials;

import com.magro.raytracer.Color;
import com.magro.raytracer.HitRecord;
import com.magro.raytracer.Ray;
import com.magro.raytracer.Utils;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import static com.magro.raytracer.Utils.nearZero;

public class Lambertian implements Material {

    public Color albedo;

    public Lambertian(Color a){
        this.albedo = a;
    }


    @Override
    public Scatter scatter(Ray r_in, HitRecord rec) {
        Vector3D scatterDirection = rec.normal.add(Utils.randomUnitVector());

        // Catch degenerate scatter direction
        if (nearZero(scatterDirection)) {
            scatterDirection = rec.normal;
        }

        Ray scattered = new Ray(rec.p, scatterDirection);
        Scatter scatter = new Scatter(true, albedo, scattered);
        return scatter;
    }
}
