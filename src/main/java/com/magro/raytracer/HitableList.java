package com.magro.raytracer;

import java.util.LinkedList;

public class HitableList implements Hitable {

    public LinkedList<Hitable> objects;

    public HitableList() {
        this.objects = new LinkedList<>();
    }

    public HitableList(Hitable object) {
        this.objects = new LinkedList<>();
        this.objects.add(object);
    }

    public void add(Hitable object) {
        this.objects.add(object);
    }

    public void clear() {
        this.objects.clear();
    }

    /*
   bool hittable_list::hit(const ray& r, double t_min, double t_max, hit_record& rec) const {
    hit_record temp_rec;
    bool hit_anything = false;
    auto closest_so_far = t_max;

    for (const auto& object : objects) {
        if (object->hit(r, t_min, closest_so_far, temp_rec)) {
            hit_anything = true;
            closest_so_far = temp_rec.t;
            rec = temp_rec;
        }
    }

    return hit_anything;
}
}
     */
    @Override
    public HitRecord hit(Ray r, double t_min, double t_max, HitRecord rec) {
        HitRecord tempRec = new HitRecord();
        double closestSoFar = t_max;
        boolean hitAnything = false;

        for (Hitable object : objects) {
            HitRecord hit = object.hit(r, t_min, closestSoFar, tempRec);
            if (hit.hit) {
                hitAnything = true;
                if (hit.t < closestSoFar) {
                    closestSoFar = hit.t;
                    rec = hit;
                }
            }
        }
        rec.hit = hitAnything;
        return rec;
    }
}
