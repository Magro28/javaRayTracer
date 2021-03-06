package com.magro.raytracer;

import com.magro.raytracer.materials.*;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import static com.magro.raytracer.Utils.*;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Raytracer main class
 *
 * @author Mario Groß
 */
public class Main {

    public static void main(String[] args) throws IOException {
        // Image
        final double aspect_ratio = 3.0 / 2.0;
        final int image_width = 600;
        final int image_height = (int) (image_width / aspect_ratio);
        final int samplesPerPixel = 50; //antialiasing
        final int maxDepth=50; //recursive depth of child rays

        //Testing world
//        //Materials
//        Material materialGround = new Lambertian(new Color(0.8, 0.8, 0.0));
//        Material lambertian1 = new Lambertian(new Color(0.1, 0.2, 0.8));
//        Material metal1  = new Metal(new Color(1, 1, 1), 0.0);
//        Material dielectric1   = new Dielectric(3);
//
//        // World
//        HitableList world = new HitableList();
//        world.add(new Sphere(new Vector3D( 0.0, -100.5, -1.0), 100.0, materialGround));
//        world.add(new Sphere(new Vector3D( 0.0,    0.0, -1.0),   0.5, lambertian1));
//        world.add(new Sphere(new Vector3D(-1.0,    0.0, -1.0),   0.5, dielectric1));
//        world.add(new Sphere(new Vector3D(-1.0,    0.0, -1.0),   -0.45, dielectric1));
//        world.add(new Sphere(new Vector3D( 1.0,    0.0, -1.0),   0.5, metal1));

        HitableList world = new WorldGenerator().generateRandomWorld();

        // Camera lookFrom, lookAt, vup, fov (field of view and zooming), aspect_ratio, aperture, dist_to_focus
        Vector3D lookfrom = new Vector3D(13,2,3);
        Vector3D lookat = new Vector3D(0,0,0);
        Vector3D vup= new Vector3D(0,1,0);
        double dist_to_focus = 10.0; //Utils.length(lookfrom.subtract(lookat));
        double aperture = 0.1;
        double fov = 20.0;

        Camera cam = new Camera(lookfrom, lookat, vup, fov, aspect_ratio,aperture,dist_to_focus);

        // Render
        String ppmImage = "P3\n" + image_width + " " + image_height + "\n255\n";
        Random rnd = new Random();
        rnd.nextDouble();
        for (int j = image_height - 1; j >= 0; --j) {
            System.out.println("\rScanlines remaining: " + j);
            for (int i = 0; i < image_width; ++i) {
                Vector3D colorVector = new Vector3D(0, 0, 0);
                //antialiasing comes here
                for (int s = 0; s < samplesPerPixel; ++s) {
                    double u = ((double)i + rnd.nextDouble()) / (image_width-1);
                    double v = ((double)j + rnd.nextDouble()) / (image_height-1);
                    Ray r = cam.getRay(u, v);
                    Color rayCol = rayColor(r, world, maxDepth);
                    colorVector = colorVector.add(rayCol.colorVector);
                }
                Color color = new Color(colorVector);
                ppmImage += color.writeColor(color,samplesPerPixel);
            }
        }
        File file = new File("./image.ppm");
        FileWriter fw = new FileWriter(file);
        fw.write(ppmImage);
        fw.flush();
        fw.close();
    }

    private static Color rayColor(Ray r, Hitable world, int depth) {
        if (depth <= 0){
            return new Color(0,0,0);
        }

        HitRecord rec = world.hit(r, 0.001, infinity, new HitRecord());
        if (rec.hit) {
            Scatter scatter = rec.material.scatter(r, rec);
            if(scatter.hit){
                return new Color(Utils.vectorMultiply(scatter.color.colorVector, rayColor(scatter.scattered, world, depth-1).colorVector));
            }
            else {
                return new Color(0,0,0);
            }
        }
        Vector3D unitDirection = r.direction;
        double t = 0.5*(unitDirection.getY() + 1.0);
        Color color1 = new Color(1.0, 1.0, 1.0);
        Color color2 = new Color(0.5, 0.7, 1.0);
        return new Color(color1.colorVector.scalarMultiply(1.0 - t).add(color2.colorVector.scalarMultiply(t)));
    }

    public static double hitSphere(final Vector3D center, double radius, final Ray r) {
        Vector3D oc = r.origin.subtract(center);

        double a = lengthSquared(r.direction);
        double half_b = (oc.dotProduct(r.direction));
        double c = lengthSquared(oc) - (radius * radius);
        double discriminant = (half_b * half_b) - (a * c);

        if (discriminant < 0) {
            return -1.0;
        } else {
            return (-half_b - sqrt(discriminant) ) / a;
        }
    }

    public static double length(Vector3D v)  {
        return sqrt(lengthSquared(v));
    }

    public static double lengthSquared(Vector3D v)  {
        return pow(v.getX(),2) + pow(v.getY(),2) + pow(v.getZ(),2);
    }


}
