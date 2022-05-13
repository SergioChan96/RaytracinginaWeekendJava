module com.example.raytracing {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.raytracing to javafx.fxml;
    exports com.example.raytracing;
}