package com.magro.raytracer.materials;

import com.magro.raytracer.Color;
import com.magro.raytracer.Ray;

public class Scatter {

    public  Scatter(){

    }

    public Scatter(boolean hit, Color color, Ray scattered) {
        this.hit = hit;
        this.color = color;
        this.scattered = scattered;
    }

    public boolean hit;
    public Color color;
    public Ray scattered;
}
