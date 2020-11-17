package com.magro.raytracer.materials;

import com.magro.raytracer.Color;
import com.magro.raytracer.HitRecord;
import com.magro.raytracer.Ray;
import com.magro.raytracer.Utils;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import static com.magro.raytracer.Utils.*;

public class Dielectric implements Material {

    public double ir; // Index of Refraction

    public Dielectric(double ir) {
        this.ir = ir;
    }

    @Override
    public Scatter scatter(Ray r_in, HitRecord rec) {
        Color attenuation = new Color(1.0, 1.0, 1.0);
        double refraction_ratio = rec.front_face ? (1.0 / ir) : ir;

        Vector3D unit_direction = Utils.unitVector(r_in.direction);

        double cos_theta = Math.min(unit_direction.scalarMultiply(-1.0).dotProduct(rec.normal), 1.0);
        double sin_theta = Math.sqrt(1.0 - (cos_theta * cos_theta));

        boolean cannot_refract = refraction_ratio * sin_theta > 1.0;
        Vector3D direction;

        if (cannot_refract || reflectance(cos_theta, refraction_ratio) > Utils.rnd.nextDouble()) {
            direction = reflect(unit_direction, rec.normal);
        } else {
            direction = refract(unit_direction, rec.normal, refraction_ratio);
        }

        Scatter scatter = new Scatter();
        scatter.scattered = new Ray(rec.p, direction);
        scatter.color = attenuation;
        scatter.hit = true;
        return scatter;
    }
}
