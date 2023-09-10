package com.ar.contatorevinopong;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class VisualizzatoreController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblValue;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TranslateTransition tt = new TranslateTransition();
        tt.setNode(anchorPane);
        tt.setDuration(Duration.millis(500));
        tt.setByX(1000);

        tt.play();
    }
}