package com.example.raytracing;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class HelloApplication extends Application {
    private final double aspectRatio = 16.0/9.0;
    private final int width = 800;
    private final int height = (int)(width/aspectRatio);

    //Camera
    double viewportHeight = 2.0;
    double viewportWidth = aspectRatio * viewportHeight;


    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        root.setPrefSize(width, height);
        Canvas canvas = new Canvas();

        GraphicsContext gc = canvas.getGraphicsContext2D();
        Image image = paintPixels();
        //Image image = paint();

        root.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
            canvas.setWidth(newValue.getWidth());
            canvas.setHeight(newValue.getHeight());
            gc.drawImage(image, 0, 0);
        });

        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Image paintPixels() {
        WritableImage img = new WritableImage(width, height);
        PixelWriter pw  = img.getPixelWriter();
        // World
        HittableList world = new HittableList<>();
        world.add(new Sphere(new Point3(0,0,1), 0.5));
        world.add(new Sphere(new Point3(0,-100.5,-1), 100));
        //camera
        var focalLength = 1.0;

        var origin = new Point3(0, 0, 0);
        var horizontal = new Vec3(viewportWidth, 0, 0);
        var vertical = new Vec3(0, viewportHeight, 0);
        var lowerLeftCorner =
                Vec3.sub(origin, Vec3.divide(horizontal, 2)).addToVec3(Vec3.divide(vertical, 2).negative()).addToVec3(new Vec3(0, 0, focalLength));

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                var u = (double) i / (width - 1);
                var v = (double) (height - j) / (height - 1); // not j because 0, 0 is in the left upper corner
                Ray r = new Ray(origin, Vec3.add(lowerLeftCorner, Vec3.multi(u, horizontal)).addToVec3(Vec3.multi(v, vertical)).addToVec3(origin.negative()));
                vColor c = rayColor(r, world);
                pw.setColor(i, j, vColor.convert(c));
            }
        }
        return img;
    }
    public Image paint() {
        WritableImage img = new WritableImage(width, height);
        PixelWriter pw  = img.getPixelWriter();
        for (int j = 0; j < 50; j++) {
            for (int i = 0; i < 50; i++) {
                pw.setColor(i, j, vColor.convert(new vColor(0,0,0)));
            }
        }
        return img;
    }
    public vColor rayColor(Ray r, Hittable world) {
        HitRecord rec = new HitRecord();
        if (world.hit(r, 0, Double.POSITIVE_INFINITY, rec)) {
            return new vColor(Vec3.multi(0.5, Vec3.add(rec.normal, new vColor(1,1,1))));
        }
        Vec3 unitDirection = Vec3.unitVector(r.direction());
        var t = 0.5 * (unitDirection.getY() + 1.0);
        return new vColor(Vec3.add(Vec3.multi((1.0-t), new vColor(1.0, 1.0, 1.0)), Vec3.multi(t, new vColor(0.5, 0.7, 1.0))));
    }

    public static void main(String[] args) {
        launch();
    }
}