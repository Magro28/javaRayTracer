package com.magro.raytracer;

import com.magro.raytracer.materials.Dielectric;
import com.magro.raytracer.materials.Lambertian;
import com.magro.raytracer.materials.Material;
import com.magro.raytracer.materials.Metal;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import static com.magro.raytracer.Utils.*;

public class WorldGenerator {


    public WorldGenerator(){
    }

    public HitableList generateRandomWorld() {
        HitableList world = new HitableList();

        //create static ground sphere
        Material ground_material = new Lambertian(new Color(0.5, 0.5, 0.5));
        world.add(new Sphere(new Vector3D(0, -1000, 0), 1000, ground_material));

        //create small random spheres
        for (int a = -11; a < 11; a++) {
            for (int b = -11; b < 11; b++) {
                double choose_mat = rnd.nextDouble();
                Vector3D center = new Vector3D(a + 0.9 * rnd.nextDouble(), 0.2, b + 0.9 * rnd.nextDouble());

                if (Utils.length(center.subtract(new Vector3D(4, 0.2, 0))) > 0.9) {
                    Material sphere_material;

                    if (choose_mat < 0.8) {
                        // diffuse
                        Color albedo = new Color(Utils.vectorMultiply(Utils.randomVector(),Utils.randomVector()));
                        sphere_material = new Lambertian(albedo);
                        world.add(new Sphere(center, 0.2, sphere_material));
                    } else if (choose_mat < 0.95) {
                        // metal
                        Color albedo = new Color(randomVector(0.5, 1));
                        double fuzz = randomRange(0, 0.5);
                        sphere_material = new Metal(albedo, fuzz);
                        world.add(new Sphere(center, 0.2, sphere_material));
                    } else {
                        // glass
                        sphere_material = new Dielectric(1.5);
                        world.add(new Sphere(center, 0.2, sphere_material));
                    }
                }

            }
        }

        //Big Spheres in the center

        Material material1 = new Dielectric(1.5);
        world.add(new Sphere(new Vector3D(0, 1, 0), 1.0, material1));

        Material material2 = new Lambertian(new Color(1, 0.3, 0.3));
        world.add(new Sphere(new Vector3D(-4, 1, 0), 1.0, material2));

        Material material3 = new Metal(new Color(0.7, 0.6, 0.5), 0.0);
        world.add(new Sphere(new Vector3D(4, 1, 0), 1.0, material3));

        return world;
    }
}
