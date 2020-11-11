package com.magro.raytracer;


import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

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

    public String writeColor(){
        return writeColor(this.colorVector);
    }
    public String writeColor(Vector3D vector3D) {
        return "" + (int) (vector3D.getX()*255.999d) +" "+(int) (vector3D.getY()*255.999d)+" "+(int) (vector3D.getZ()*255.999d)+"\n" ;
    }
}
