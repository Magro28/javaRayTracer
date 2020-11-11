package com.magro.raytracer;

import org.apache.commons.math3.analysis.function.Sqrt;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Raytracer main class
 *
 * @Mario Groß
 */
public class Main {

    public static void main(String[] args) throws IOException {
        // Image
        final double aspect_ratio = 16.0 / 9.0;
        final int image_width = 400;
        final int image_height = (int) (image_width / aspect_ratio);

        // Camera
        double viewport_height = 2.0;
        double viewport_width = aspect_ratio * viewport_height;
        double focal_length = 1.0;

        Vector3D origin = new Vector3D(0d, 0d, 0d);
        Vector3D horizontal = new Vector3D(viewport_width, 0, 0);
        Vector3D vertical = new Vector3D(0, viewport_height, 0);

        // lower_left_corner = origin - horizontal/2 - vertical/2 - vec3(0, 0, focal_length);
        Vector3D lower_left_corner = origin.subtract(horizontal.scalarMultiply(0.5))
                .subtract(vertical.scalarMultiply(0.5))
                .subtract(new Vector3D(0, 0, focal_length));


        // Render
        String ppmImage = "P3\n" + image_width + " " + image_height + "\n255\n";

        for (int j = image_height - 1; j >= 0; --j) {
            System.out.println("\rScanlines remaining: " + j);
            for (int i = 0; i < image_width; ++i) {
                double u = (double) i / (image_width - 1);
                double v = (double) j / (image_height - 1);
                Ray r = new Ray(origin, lower_left_corner.add(horizontal.scalarMultiply(u).add(vertical.scalarMultiply(v)).subtract(origin)));
                Color pixel_color = rayColor(r);
                ppmImage += pixel_color.writeColor();
            }
        }
        File file = new File("./image.ppm");
        FileWriter fw = new FileWriter(file);
        fw.write(ppmImage);
        fw.flush();
        fw.close();
    }

    private static Color rayColor(Ray r) {
        double t = hitSphere(new Vector3D(0,0,-1), 0.5, r);
        if (t > 0.0) {
            Vector3D N = r.at(t).subtract(new Vector3D(0,0,-1));
            return new Color(new Vector3D(N.getX()+1, N.getY()+1, N.getZ()+1).scalarMultiply(0.5));
        }
        Vector3D unit_direction = r.direction;
        t = 0.5*(unit_direction.getY() + 1.0);
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
