package com.example.raytracing;

import java.util.ArrayList;

public class HittableList<T extends Hittable> extends Hittable {
    ArrayList<T> objects = new ArrayList<>();
    public HittableList() {}
    public HittableList(T object) {
        add(object);
    }

    public void add(T obj) {
        objects.add(obj);
    }
    public void clear() {
        objects.clear();
    }
    @Override
    public boolean hit(Ray r, double tMin, double tMax, HitRecord rec) {
        HitRecord tempRec = new HitRecord();
        boolean hitAnything = false;
        var closestSoFar = tMax;

        for (T o : objects) {
            if (o.hit(r, tMin, closestSoFar, tempRec)) {
                hitAnything = true;
                closestSoFar = tempRec.t;
                rec.normal = tempRec.normal;
                rec.p = tempRec.p;
                rec.t = tempRec.t;
            }
        }
        return hitAnything;
    }
}
