package com.magro.raytracer;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Raytracer main class
 * @Mario Groß
 */
public class Main {

    public static void main(String[] args) throws IOException {
        // Image
        final double aspect_ratio = 16.0 / 9.0;
        final int image_width = 400;
        final int image_height = (int)(image_width / aspect_ratio);

        // Camera
        double viewport_height = 2.0;
        double viewport_width = aspect_ratio * viewport_height;
        double focal_length = 1.0;

        Vector3D origin = new Vector3D(0d, 0d, 0d);
        Vector3D horizontal = new Vector3D(viewport_width, 0, 0);
        Vector3D vertical = new Vector3D(0, viewport_height, 0);
        Vector3D lower_left_corner = origin.subtract(horizontal.scalarMultiply(1/2).subtract(vertical.scalarMultiply(1/2)).subtract(new Vector3D(0, 0, focal_length)));


        // Render
        String ppmImage = "P3\n"+image_width + " "+ image_height+ "\n255\n";

        for (int j = image_height-1; j >= 0; --j) {
           System.out.println("\rScanlines remaining: "+j);
            for (int i = 0; i < image_width; ++i) {
                double u = (double)i / (image_width-1);
                double v = (double) j / (image_height-1);
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
            Vector3D unit_direction = r.direction;
            double t = 0.5*(unit_direction.getY() + 1.0);
            Color color1 = new Color(1.0, 1.0, 1.0);
            Color color2 = new Color(0.5, 0.7, 1.0);
            return new Color(color1.colorVector.scalarMultiply(1.0-t).add(color2.colorVector.scalarMultiply(t)));

    }
}
