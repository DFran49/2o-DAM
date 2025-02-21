module com.javafx.miscvs {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires javafx.graphics;
    requires javafx.base;
    
    exports cvs;
    
    opens cvs to javafx.fxml;
}