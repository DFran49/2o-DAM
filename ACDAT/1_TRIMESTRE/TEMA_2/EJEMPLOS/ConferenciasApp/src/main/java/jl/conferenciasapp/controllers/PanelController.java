package jl.conferenciasapp.controllers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class PanelController extends BaseController {
    @FXML
    private Button btnAsistentes;

    @FXML
    private Button btnAsistir;

    @FXML
    private Button btnConferencias;

    @FXML
    private Button btnParticipar;

    @FXML
    private Button btnPonentes;

    @FXML
    private Button btnSalas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image imageSalas = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/salas.png")));
        ImageView imageViewSalas = new ImageView(imageSalas);
        imageViewSalas.setFitWidth(150);
        imageViewSalas.setFitHeight(150);
        btnSalas.setGraphic(imageViewSalas);

        Image imageAsistentes = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/asistentes.png")));
        ImageView imageViewAsistentes = new ImageView(imageAsistentes);
        imageViewAsistentes.setFitWidth(150);
        imageViewAsistentes.setFitHeight(150);
        btnAsistentes.setGraphic(imageViewAsistentes);

        Image imagePonentes = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/ponentes.png")));
        ImageView imageViewPonentes = new ImageView(imagePonentes);
        imageViewPonentes.setFitWidth(150);
        imageViewPonentes.setFitHeight(150);
        btnPonentes.setGraphic(imageViewPonentes);

        Image imageConferencias = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/conferencias.png")));
        ImageView imageViewConferencias = new ImageView(imageConferencias);
        imageViewConferencias.setFitWidth(150);
        imageViewConferencias.setFitHeight(150);
        btnConferencias.setGraphic(imageViewConferencias);

        Image imageAsistir = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/asistir.png")));
        ImageView imageViewAsistir = new ImageView(imageAsistir);
        imageViewAsistir.setFitWidth(150);
        imageViewAsistir.setFitHeight(150);
        btnAsistir.setGraphic(imageViewAsistir);

        Image imageParticipar = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/participar.png")));
        ImageView imageViewParticipar = new ImageView(imageParticipar);
        imageViewParticipar.setFitWidth(150);
        imageViewParticipar.setFitHeight(150);
        btnParticipar.setGraphic(imageViewParticipar);
    }

    @FXML
    void openAsistentes() throws IOException {
        mostrarVentana("/jl/conferenciasapp/asistentes-view.fxml", "Gestión de asistentes");
    }

    @FXML
    void openAsistir() throws IOException {
        mostrarVentana("/jl/conferenciasapp/asistir-view.fxml", "Gestión de asistentes a una conferencia");
    }

    @FXML
    void openConferencias() throws IOException {
        mostrarVentana("/jl/conferenciasapp/conferencias-view.fxml", "Gestión de conferencias");
    }

    @FXML
    void openParticipar() throws IOException {
        mostrarVentana("/jl/conferenciasapp/participar-view.fxml", "Gestión de ponentes de una conferencia");
    }

    @FXML
    void openPonentes() throws IOException {
        mostrarVentana("/jl/conferenciasapp/ponentes-view.fxml", "Gestión de ponentes");
    }

    @FXML
    void openSalas() throws IOException {
        mostrarVentana("/jl/conferenciasapp/salas-view.fxml", "Gestión de salas");
    }
}
