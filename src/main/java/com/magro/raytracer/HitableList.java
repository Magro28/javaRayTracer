package com.magro.raytracer;

import java.util.LinkedList;

public class HitableList implements Hitable{

    public LinkedList<Hitable> objects;

    public HitableList(){
        this.objects=new LinkedList<>();
    }

    public HitableList(Hitable object){
        this.objects=new LinkedList<>();
        this.objects.add(object);
    }

    public void add(Hitable object){
        this.objects.add(object);
    }

    public void clear(){
        this.objects.clear();
    }

    @Override
    public boolean hit(Ray r, double t_min, double t_max, HitRecord rec) {
        HitRecord tempRec = new HitRecord();
        boolean hitAnything = false;
        double closestSoFar = t_max;

        for (Hitable object : objects){
            if (object.hit(r,t_min,t_max,tempRec)){
                hitAnything = true;
                closestSoFar = tempRec.t;
                rec = tempRec;
            }
        }

        return hitAnything;
    }
}
