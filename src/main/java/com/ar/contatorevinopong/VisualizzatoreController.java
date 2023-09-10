package com.ar.contatorevinopong;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class VisualizzatoreController implements Initializable {

    @FXML
    private AnchorPane rootAnchorPane;
//
//    @FXML
//    private Label lblTitle;
//
//    @FXML
//    private Label lblValue;

    final int PREF_WIDTH = 1920;
    final int PREF_HEIGHT = 1080;

    List<AnchorPane> anchorPaneList;

    Configurazione config;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rootAnchorPane.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                config = VisualizzatoreStatistiche.getConfig();
                creaAnchorPaneList(config.getStatistiche());

                rootAnchorPane.getChildren().addAll(anchorPaneList);
            }
        });


    }

    private void creaAnchorPaneList(List<String> statistiche) {
        anchorPaneList = new ArrayList<>();
        for(int i=0;i<statistiche.size();i++){
            AnchorPane ap = getAnchorPane(i);
            Label titolo = getLabelTitolo(statistiche.get(i), i);
            ap.getChildren().add(titolo);
            AnchorPane.setTopAnchor(titolo, 10.0);
            AnchorPane.setBottomAnchor(titolo, 572.0);
            AnchorPane.setLeftAnchor(titolo, 0.0);
            AnchorPane.setRightAnchor(titolo, 0.0);
            if(i!=0){
                ap.setLayoutX(5000);
            }
            anchorPaneList.add(ap);
        }

    }

/*
      <Label fx:id="lblTitle" alignment="CENTER" layoutX="124.0" layoutY="96.0" prefHeight="342.0" prefWidth="1673.0" text="---" AnchorPane.bottomAnchor="572.0" AnchorPane.leftAnchor="0.0" AnchorPane.rightAnchor="0.0" AnchorPane.topAnchor="10.0">
         <font>
            <Font name="System Bold" size="350.0" />
         </font>
      </Label>
*/
    private Label getLabelTitolo(String titolo, int i) {
        Label lbl = new Label();
        lbl.setId("lblTitolo"+i);
        lbl.setAlignment(Pos.CENTER);
//        lbl.setPrefSize(PREF_WIDTH, 720);//Non serve perchè l'anchor lo tira come vuole
        lbl.setFont(getFontTitolo());
        lbl.setText(titolo);
        return lbl;
    }

    private Font getFontTitolo() {
        Font font = new Font("System Bold", 50);
        return font;
    }
    private Font getFontValore() {
        Font font = new Font("System Bold", 500);//TODO Cambiare con 7segmenti
        return font;
    }

    private AnchorPane getAnchorPane(int i) {
        AnchorPane ap = new AnchorPane();
        ap.setPrefSize(PREF_WIDTH,PREF_HEIGHT);
        ap.setId("anchorPane"+i);
        return ap;
    }
}