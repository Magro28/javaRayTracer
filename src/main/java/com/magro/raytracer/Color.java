package com.magro.raytracer;


import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import static com.magro.raytracer.Utils.clamp;

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

    /*
    public String writeColor(){
        return writeColor(this.colorVector);
    }
    public String writeColor(Vector3D vector3D) {
        return "" + (int) (vector3D.getX()*255.999d) +" "+(int) (vector3D.getY()*255.999d)+" "+(int) (vector3D.getZ()*255.999d)+"\n" ;
    }*/
    public String writeColor(Color pixelColor, int samplesPerPixel){
        double r = pixelColor.colorVector.getX();
        double g = pixelColor.colorVector.getY();
        double b = pixelColor.colorVector.getZ();

        // Divide the color by the number of samples.
        double scale = 1.0 / (double) samplesPerPixel;
        r *= scale;
        g *= scale;
        b *= scale;

        String colorString = "" + (int) (256.0 * clamp(r, 0.0, 0.999)) + " " + (int) (256.0 * clamp(g, 0.0, 0.999)) + " " + (int) (256.0 * clamp(b, 0.0, 0.999)) + "\n";
        return colorString;

    }
}
