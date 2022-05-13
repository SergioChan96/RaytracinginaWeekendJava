package com.example.raytracing;

import javafx.scene.paint.Color;

public class vColor extends Vec3 {
    public vColor(double e0, double e1, double e2) {
        super(e0, e1, e2);
    }
    public vColor(Vec3 v) {
        this(v.get(0), v.get(1), v.get(2));
    }
    public String writeColor(vColor pixelColor) {
        return (int)(255.999 * pixelColor.getX()) + " "
                + (int)(255.999 * pixelColor.getY()) + " "
                + (int)(255.999 * pixelColor.getZ());
    }
    @Override
    public String toString() {
        return (int)(255.999 * getX()) + " "
                + (int)(255.999 * getY()) + " "
                + (int)(255.999 * getZ());
    }
    public static Color convert(vColor pixelColor) {
        return Color.rgb((int)(255.999 * pixelColor.getX()),
                (int)(255.999 * pixelColor.getY()),
                (int)(255.999 * pixelColor.getZ()));
    }
}
