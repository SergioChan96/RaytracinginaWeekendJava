package com.example.raytracing;

public abstract class Hittable {

    public abstract boolean hit(Ray r, double tMin, double tMax, HitRecord rec);
}
