package com.example.raytracing;

public class HitRecord {
    public Point3 p;
    public Vec3 normal;
    public double t;
    public boolean frontFace;

    public HitRecord(Point3 p, Vec3 normal, double t) {
        this.p = p;
        this.normal = normal;
        this.t = t;
    }
    public HitRecord() {};
    public void setFaceNormal(Ray r, Vec3 outwardNormal) {
        frontFace = Vec3.dot(r.direction(), outwardNormal) < 0;
        normal = frontFace ? outwardNormal : outwardNormal.negative();
    }
}
