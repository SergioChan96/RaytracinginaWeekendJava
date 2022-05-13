package com.example.raytracing;

public class Sphere extends Hittable{
    private Point3 center;
    private double radius;

    public Sphere(){}
    public Sphere(Point3 cen, double r) {
        center = cen;
        radius = r;
    }

    @Override
    public boolean hit(Ray r, double tMin, double tMax, HitRecord rec) {
        Vec3 oc = Vec3.sub(r.origin(), center);
        var a = r.direction().lengthSquared();
        var bHalf =  Vec3.dot(oc, r.direction());
        var c = oc.lengthSquared() - radius * radius;

        var discriminant = bHalf * bHalf - a * c;
        if (discriminant < 0) {
            return false;
        }
        var sqrtd = Math.sqrt(discriminant);

        var root = (-bHalf - sqrtd) / a;
        if (root < tMin || tMax < root) {
            root = (-bHalf + sqrtd) / a;
            if (root < tMin || tMax < root) {
                return false;
            }
        }
        rec.t = root;
        rec.p = r.at(rec.t);
        Vec3 outwardNormal = Vec3.divide(Vec3.sub(rec.p, center), radius);
        rec.setFaceNormal(r, outwardNormal);
        return true;
    }
}
