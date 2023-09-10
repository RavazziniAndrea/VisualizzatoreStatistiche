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

    private static final String ID_GRIDPANE = "gridPane";
    private static final String ID_VBOX_TITOLO = "vbTitolo";
    private static final String ID_VBOX_VALORE = "vbValore";
    private static final String ID_LABEL_TITOLO = "lblTitolo";
    private static final String ID_LABEL_VALORE = "lblValore";

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
                avviaCambioSchermate(config.getDurataSlide(), rootAnchorPane.getScene());
            }
        });
    }

    private void avviaCambioSchermate(int durata, Scene scene) {
        Thread t = new Thread(()->{
            for(int i=0;;i++){
                if(i>=config.getStatistiche().size()) i=0;
                int id = i;
                try {
                    Thread.sleep(durata);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(()->{
                    GridPane gpHide;
                    GridPane gpShow;
                    if(id == config.getStatistiche().size()-1){
                        gpHide = (GridPane) scene.lookup("#" + ID_GRIDPANE + (config.getStatistiche().size()-1));
                        gpShow = (GridPane) scene.lookup("#" + ID_GRIDPANE + 0);
                    } else {
                        gpHide = (GridPane) scene.lookup("#" + ID_GRIDPANE + id);
                        gpShow = (GridPane) scene.lookup("#" + ID_GRIDPANE + (id+1));
                    }
                    gpHide.setVisible(false);
                    gpShow.setVisible(true);
                });
            }
        },"thread cambio schermate");
        t.setDaemon(true);
        t.start();
    }

    private void creaGridPaneList() {
        List<String> statistiche = config.getStatistiche();
        List<Integer> statisticheFont = config.getStatisticheFont();


        gridPaneList = new ArrayList<>();
        for(int i=0;i<statistiche.size();i++){
            GridPane gp = new GridPane();
            gp.setId(ID_GRIDPANE+i);
            gp.setPrefSize(PREF_WIDTH, PREF_HEIGHT);
            Label lblTitolo = getLabelTitolo(statistiche.get(i), statisticheFont.get(i), i);
            VBox vbTitolo = new VBox(lblTitolo);
            vbTitolo.setAlignment(Pos.CENTER);
            vbTitolo.setId(ID_VBOX_TITOLO+i);
            RowConstraints constraintTitolo = new RowConstraints();
            constraintTitolo.setPercentHeight(40);

            Label lblValore = getLabelValore("---", i); //TODO cambiare il valore con i dati veri
            VBox vbValore = new VBox(lblValore);
            vbValore.setAlignment(Pos.CENTER);
            vbValore.setId(ID_VBOX_VALORE+i);
            RowConstraints constraintValore = new RowConstraints();
            constraintValore.setPercentHeight(60);

            ColumnConstraints columnConstraint = new ColumnConstraints();
            columnConstraint.setPercentWidth(100);
            gp.getRowConstraints().addAll(constraintTitolo, constraintValore);
            gp.getColumnConstraints().addAll(columnConstraint);
            gp.add(vbTitolo, 0,0);
            gp.add(vbValore, 0,1);

            if(i!=0){
//                gp.setLayoutX(PREF_WIDTH); //Trasla tutti oltre lo schermo visibile
                gp.setVisible(false);
            }
            gridPaneList.add(gp);
        }
    }

    private Label getLabelTitolo(String titolo, Integer fontSize, int i) {
        Label lbl = new Label();
        lbl.setId(ID_LABEL_TITOLO+i);
        lbl.setAlignment(Pos.CENTER);
        lbl.setFont(getFontTitolo(fontSize));
        lbl.setText(titolo);
        return lbl;
    }

    private Label getLabelValore(String valore, int i) {
        Label lbl = new Label();
        lbl.setId(ID_LABEL_VALORE+i);
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

}