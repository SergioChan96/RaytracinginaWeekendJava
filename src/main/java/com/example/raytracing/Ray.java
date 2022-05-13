package com.example.raytracing;

public class Ray {
    private Point3 orig;
    private Vec3 dir;

    public Ray() {}
    public Ray(Point3 origin, Vec3 direction) {
        orig = origin;
        dir = direction;
    }

    public Point3 origin() {
        return orig;
    }
    public Vec3 direction() {
        return dir;
    }

    public Point3 at(double t) {
        //TODO optimise too many new calls
        Vec3 v = Vec3.add(orig,Vec3.multi(t, dir));
        return new Point3(v.getX(), v.getY(), v.getZ());
    }
}
