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
    public Scatter scatter(Ray r_in, HitRecord rec, Color attenuation, Ray scattered) {
        Vector3D scatterDirection = rec.normal.add(Utils.randomUnitVector());

        // Catch degenerate scatter direction
        if (nearZero(scatterDirection))
            scatterDirection = rec.normal;

        scattered = new Ray(rec.p, scatterDirection);
        attenuation = albedo;
        Scatter scatter = new Scatter(true, attenuation, scattered);
        return scatter;
    }
}
