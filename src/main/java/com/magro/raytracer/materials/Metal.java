package com.magro.raytracer.materials;

import com.magro.raytracer.Color;
import com.magro.raytracer.HitRecord;
import com.magro.raytracer.Ray;
import com.magro.raytracer.Utils;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class Metal implements Material{

    public Color albedo;
    public double fuzz;

    public Metal(Color a, double fuzz){
        this.albedo = a;
        if (fuzz > 1){
            fuzz = 1;
        }
        this.fuzz = fuzz;
    }

    @Override
    public Scatter scatter(Ray r_in, HitRecord rec) {
        Vector3D reflected = Utils.reflect(Utils.unitVector(r_in.direction), rec.normal);
        Scatter scatter = new Scatter();
        scatter.scattered = new Ray(rec.p, reflected.add(Utils.randomInUnitSphere().scalarMultiply(fuzz)));
        scatter.color = albedo;
        if (scatter.scattered.direction.dotProduct(rec.normal) > 0){
            scatter.hit = true;
        }
        return scatter;
    }
}
