package com.magro.raytracer;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 * A ray of light in time
 *
 * @author Mario Groß
 */
public class Ray {

    public Vector3D origin;
    public Vector3D direction;

    public Ray() {}

    public Ray(Vector3D origin, Vector3D direction){
        this.origin = origin;
        this.direction = direction;
    }

    /**
     * Returns position of Ray at specific time
     * @param t
     * @return
     */
    public Vector3D at(double t){
        return origin.add(direction.scalarMultiply(t));
    }
}
