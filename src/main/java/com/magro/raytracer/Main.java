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

        final int image_width = 256;
        final int image_height = 256;



        // Render
        String ppmImage = "P3\n"+image_width + " "+ image_height+ "\n255\n";

        for (int j = image_height-1; j >= 0; --j) {
           System.out.println("\rScanlines remaining: "+j);
            for (int i = 0; i < image_width; ++i) {
            /*    double r = (double) i / (double) (image_width - 1);
                double g = (double) j / (double) (image_height - 1);
                double b = 0.25;

                int ir = (int)(255.999 * r);
                int ig = (int)(255.999 * g);
                int ib = (int)(255.999 * b);

                ppmImage += ir +" "+ig+" "+ib + "\n";*/

                Color pixel_color = new Color((double)(i)/(image_width-1), (double)(j)/(image_height-1), 0.25);
                ppmImage += pixel_color.writeColor();
            }
        }
        File file = new File("./image.ppm");
        FileWriter fw = new FileWriter(file);
        fw.write(ppmImage);
        fw.flush();
        fw.close();

    }
}
