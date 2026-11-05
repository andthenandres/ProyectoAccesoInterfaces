module com.example.proyectoprogramadores {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.proyectoprogramadores to javafx.fxml;
    exports com.example.proyectoprogramadores;
}