module jl.conferenciasapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens jl.conferenciasapp to javafx.fxml;
    exports jl.conferenciasapp;
    exports jl.conferenciasapp.controllers;
    opens jl.conferenciasapp.controllers to javafx.fxml;

    opens jl.conferenciasapp.models;
    exports jl.conferenciasapp.interfaces;
    opens jl.conferenciasapp.interfaces to javafx.fxml;
}