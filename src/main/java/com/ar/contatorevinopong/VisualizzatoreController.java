package com.ar.contatorevinopong;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class VisualizzatoreController implements Initializable {

    @FXML
    private AnchorPane rootAnchorPane;

    private static final String FONT_PATH = "/font/ClassicMiniBoldItalic.ttf";
    private static final String FONT_NAME = "ClassicMiniBoldItalic.ttf";
//
//    @FXML
//    private Label lblTitle;
//
//    @FXML
//    private Label lblValue;

//    final int PREF_WIDTH = 1920;
//    final int PREF_HEIGHT = 1080;
    final int PREF_WIDTH = 1360;
    final int PREF_HEIGHT = 768;

    List<GridPane> gridPaneList;

    Configurazione config;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rootAnchorPane.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                config = VisualizzatoreStatistiche.getConfig();
                creaGridPaneList();

                rootAnchorPane.getChildren().addAll(gridPaneList);
            }
        });


    }

    private void creaGridPaneList() {
        List<String> statistiche = config.getStatistiche();
        List<Integer> statisticheFont = config.getStatisticheFont();


        gridPaneList = new ArrayList<>();
        for(int i=0;i<statistiche.size();i++){
            GridPane gp = new GridPane();
            gp.setPrefSize(PREF_WIDTH, PREF_HEIGHT);
            Label lblTitolo = getLabelTitolo(statistiche.get(i), statisticheFont.get(i), i);
            VBox vbTitolo = new VBox(lblTitolo);
            vbTitolo.setAlignment(Pos.CENTER);
            vbTitolo.setId("vbTitolo"+i);
            RowConstraints constraintTitolo = new RowConstraints();
            constraintTitolo.setPercentHeight(40);

            Label lblValore = getLabelValore("02345", i); //TODO cambiare il valore con i dati veri
            VBox vbValore = new VBox(lblValore);
            vbValore.setAlignment(Pos.CENTER);
            vbValore.setId("vbValore"+i);
            RowConstraints constraintValore = new RowConstraints();
            constraintValore.setPercentHeight(60);

            ColumnConstraints columnConstraint = new ColumnConstraints();
            columnConstraint.setPercentWidth(100);
            gp.getRowConstraints().addAll(constraintTitolo, constraintValore);
            gp.getColumnConstraints().addAll(columnConstraint);
            gp.add(vbTitolo, 0,0);
            gp.add(vbValore, 0,1);

            if(i!=4){
                gp.setLayoutX(PREF_WIDTH); //Trasla tutti oltre lo schermo visibile
            }
            gridPaneList.add(gp);
        }
    }

    /*
          <Label fx:id="lblTitle" alignment="CENTER" layoutX="124.0" layoutY="96.0" prefHeight="342.0" prefWidth="1673.0" text="---" AnchorPane.bottomAnchor="572.0" AnchorPane.leftAnchor="0.0" AnchorPane.rightAnchor="0.0" AnchorPane.topAnchor="10.0">
             <font>
                <Font name="System Bold" size="350.0" />
             </font>
          </Label>
    */
    private Label getLabelTitolo(String titolo, Integer fontSize, int i) {
        Label lbl = new Label();
        lbl.setId("lblTitolo"+i);
        lbl.setAlignment(Pos.CENTER);
        lbl.setFont(getFontTitolo(fontSize));
        lbl.setText(titolo);
        return lbl;
    }

    private Label getLabelValore(String valore, int i) {
        Label lbl = new Label();
        lbl.setId("lblValore"+i);
        lbl.setAlignment(Pos.CENTER);
        lbl.setFont(getFontValore());
        lbl.setText(valore);
        return lbl;
    }


    private Font getFontTitolo(int fontSize) {
        Font font = new Font("System Bold", fontSize);
        return font;
    }
    private Font getFontValore() {

        InputStream is = VisualizzatoreController.class.getResourceAsStream(FONT_PATH);
        Font font = Font.loadFont(is, 300);
        if(font == null){
            System.err.println("Font non trovato, carico standard");
            font = new Font("System Bold", 400);
        }
        return font;
    }

    private AnchorPane getAnchorPane(int i) {
        AnchorPane ap = new AnchorPane();
        ap.setPrefSize(PREF_WIDTH,PREF_HEIGHT);
        ap.setId("anchorPane"+i);
        return ap;
    }
}