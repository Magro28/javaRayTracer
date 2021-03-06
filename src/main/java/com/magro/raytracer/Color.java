package com.magro.raytracer;


import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import static com.magro.raytracer.Utils.clamp;
import static java.lang.Math.sqrt;

/**
 * Color Vector
 *
 * @author Mario Groß
 */
public class Color {

    public Vector3D colorVector;

    public Color(Color color){
        this.colorVector = color.colorVector;
    }

    public Color(Vector3D vector3D){
        this.colorVector = vector3D;
    }

    public Color(double x, double y, double z){
        this.colorVector = new Vector3D(x,y,z);
    }

    public String writeColor(Color pixelColor, int samplesPerPixel){
        double r = pixelColor.colorVector.getX();
        double g = pixelColor.colorVector.getY();
        double b = pixelColor.colorVector.getZ();

        // Divide the color by the number of samples and gamma-correct for gamma=2.0.
        double scale = 1.0 / (double) samplesPerPixel;
        r = sqrt(scale * r);
        g = sqrt(scale * g);
        b = sqrt(scale * b);

        String colorString = "" + (int) (256.0 * clamp(r, 0.0, 0.999)) + " " + (int) (256.0 * clamp(g, 0.0, 0.999)) + " " + (int) (256.0 * clamp(b, 0.0, 0.999)) + "\n";
        return colorString;

    }
}
