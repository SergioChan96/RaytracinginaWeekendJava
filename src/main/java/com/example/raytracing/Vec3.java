package com.example.raytracing;

public class Vec3 {
    private double e[] = new double[3];

    public Vec3() {
        e[0] = 0;
        e[1] = 0;
        e[2] = 0;
    }

    public Vec3(double e0, double e1, double e2) {
        e[0] = e0;
        e[1] = e1;
        e[2] = e2;
    }

    public double getX() {
        return e[0];
    }
    public double getY() {
        return e[1];
    }
    public double getZ() {
        return e[2];
    }

    public Vec3 negative() {
        return new Vec3(-e[0], -e[1], e[2]);
    }
    public double get(int i) {
        return e[i];
    }

    public Vec3 addToVec3(Vec3 v) {
       e[0] += v.get(0);
       e[1] += v.get(1);
       e[2] += v.get(2);
       return this;
    }
    public Vec3 multiToVec3(double t) {
        e[0] = e[0] * t;
        e[1] = e[1] * t;
        e[2] = e[2] * t;
        return this;
    }
    public Vec3 divideVec3(double t) {
        return multiToVec3(1/t);
    }
    public double length() {
        return Math.sqrt(lengthSquared());
    }
    public double lengthSquared() {
        return e[0] * e[0] + e[1] * e[1] + e[2] * e[2];
    }

    // operator overloading
    public static Vec3 add(Vec3 u, Vec3 v) {
        return new Vec3(u.e[0] + v.get(0),
               u.e[1] + v.get(1),
               u.e[2] + v.get(2));
    }
    public static Vec3 sub(Vec3 u, Vec3 v) {
        return new Vec3(u.e[0] - v.get(0), u.e[1] - v.get(1), u.e[2] - v.get(2));
    }
    public static Vec3 multi(Vec3 u, Vec3 v) {
        return new Vec3(u.e[0] * v.get(0), u.e[1] * v.get(1), u.e[2] * v.get(2));
    }
    public static Vec3 multi(double t, Vec3 v) {
        return new Vec3(t * v.e[0], t * v.e[1], t * v.e[2]);
    }
    public static Vec3 divide(Vec3 v, double t) {
        return Vec3.multi( 1/t, v);
    }
    public static double dot(Vec3 u, Vec3 v) {
        return u.e[0] * v.get(0) + u.e[1] * v.get(1) + u.e[2] * v.get(2);
    }
    public static Vec3 cross(Vec3 u, Vec3 v) {
        return new Vec3(u.e[1] * v.get(2) - u.e[2] * v.get(1),
                u.e[2] * v.get(0) - u.e[0] * v.get(2),
                u.e[0] * v.get(1) - u.e[1] * v.get(0));
    }
    public static Vec3 unitVector(Vec3 v) {
        return Vec3.divide(v, v.length());
    }

    @Override
    public String toString() {
        return e[0] + " " + e[1] + " " + e[3];
    }
}
