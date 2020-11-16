package com.magro.raytracer.materials;

import com.magro.raytracer.Color;
import com.magro.raytracer.HitRecord;
import com.magro.raytracer.Ray;

public interface Material {

    Scatter scatter(Ray r_in, HitRecord rec);

}
