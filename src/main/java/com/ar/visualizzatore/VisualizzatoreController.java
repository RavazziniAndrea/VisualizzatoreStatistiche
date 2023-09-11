package com.ar.visualizzatore;

import com.ar.visualizzatore.config.Config;
import com.ar.visualizzatore.dati.DatiTotali;
import com.ar.visualizzatore.config.DatiStatistica;
import com.ar.visualizzatore.dati.GestioneDati;
import com.ar.visualizzatore.gui.Colore;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Pair;

import java.io.InputStream;
import java.net.URL;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class VisualizzatoreController implements Initializable {

    @FXML
    private AnchorPane rootAnchorPane;

    private static final String FONT_PATH = "/font/ClassicMiniBoldItalic.ttf";

    private static final String ID_GRIDPANE = "gp";
    private static final String PREFIX_VBOX = "vb";
    private static final String PREFIX_LABEL_TITOLO = "lblTitolo";
    private static final String PREFIX_LABEL_VALORE = "lblValore";

//    final int PREF_WIDTH = 1920;
//    final int PREF_HEIGHT = 1080;
    int PREF_WIDTH;
    int PREF_HEIGHT;

    List<GridPane> gridPaneList;

    Config config;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        rootAnchorPane.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                config = VisualizzatoreStatistiche.getConfig();
                setDimensioni();
                creaGridPaneList();

                rootAnchorPane.getChildren().addAll(gridPaneList);
                avviaCambioSchermate(config.getDurataSchermata().getDurata(), rootAnchorPane.getScene());
                avviaCambioValori(rootAnchorPane.getScene());
            }
        });
    }

    private void avviaCambioValori(Scene scene) {
        DatiTotali datiVisualizzati = new DatiTotali();
        GestioneDati gestoreDati = new GestioneDati();
        gestoreDati.avviaThreadLetturaDati();
        Thread t = new Thread(()->{
            Colore colore = new Colore();
            while(true){
                DatiTotali datiLetti = gestoreDati.getDatiLetti();
                if(datiLetti != null && !datiVisualizzati.equals(datiLetti)){
                    datiVisualizzati.setLitriBevuti(datiLetti.getLitriBevutiInt());
                    datiVisualizzati.setBirreTotali(datiLetti.getBirreTotali());
                    datiVisualizzati.setBottiglieVino(datiLetti.getBottiglieVino());
                    datiVisualizzati.setDrinkTotali(datiLetti.getDrinkTotali());
                    //TODO allora.. per rendere l'idea del countdown deve lampeggiare i : e aggiornarsi al secondo
                    datiVisualizzati.setChiusuraCasse(contaTempoRimanente(datiLetti.getChiusuraCasse()));
//                    contaTempoRimanente(datiLetti.getChiusuraCasse());

                    Map<Integer, Object> valori = datiVisualizzati.getMappaValori();

                    Platform.runLater(()->{
                        List<DatiStatistica> datiStatistiche = config.getDatiStatistiche();
                        colore.cambiaColore();
                        for(int i=0;i<datiStatistiche.size();i++){
                            Label lbl = (Label) scene.lookup("#"+PREFIX_LABEL_VALORE+datiStatistiche.get(i).getId());
                            Label lblTitolo = (Label) scene.lookup("#"+PREFIX_LABEL_TITOLO+datiStatistiche.get(i).getId());
//                            lbl.setText(valori.get(i).getValue().toString());
                            if(valori.get(i) instanceof LocalTime){
                                lbl.setText(((LocalTime) valori.get(i)).format(DateTimeFormatter.ISO_TIME));
                            } else{
                                lbl.setText(valori.get(i).toString());
                            }
                            lbl.setTextFill(Color.rgb(colore.getR(),colore.getG(),colore.getB()));
                            lblTitolo.setTextFill(Color.rgb(colore.getR(),colore.getG(),colore.getB()));
                        }
                    });
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"thread cambio valori");
        t.setDaemon(true);
        t.start();
    }

    private LocalTime contaTempoRimanente(LocalTime chiusuraCasse) { //TODO non si può vedere sto metodo, refactor
        int secondi = (int) (LocalTime.now().until(chiusuraCasse, ChronoUnit.SECONDS) % 60);
        int minuti = (int) (LocalTime.now().until(chiusuraCasse, ChronoUnit.SECONDS) / 60);
        int ore = (int) minuti/60;
        minuti = minuti % 60;
        return LocalTime.of(ore, minuti, secondi);
    }

    private void avviaCambioSchermate(int durata, Scene scene) {
        Thread t = new Thread(()->{
            for(int i=0;;i++){
                if(i>=config.getDatiStatistiche().size()) i=0;
                int id = i;
                try {
                    Thread.sleep(durata);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(()->{
                    GridPane gpHide;
                    GridPane gpShow;
                    if(id == config.getDatiStatistiche().size()-1){
                        gpHide = (GridPane) scene.lookup("#" + ID_GRIDPANE + (config.getDatiStatistiche().size()-1));
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
        List<DatiStatistica> datiStatistiche = config.getDatiStatistiche();

        gridPaneList = new ArrayList<>();
        for(int i=0;i<datiStatistiche.size();i++){
            GridPane gp = new GridPane();
            gp.setId(ID_GRIDPANE+i);
            gp.setPrefSize(PREF_WIDTH, PREF_HEIGHT);
            gp.setStyle("-fx-background-color: #000000");

            Label lblTitolo = getLabelTitolo(datiStatistiche.get(i));
            VBox vbTitolo = new VBox(lblTitolo);
            vbTitolo.setAlignment(Pos.CENTER);
            vbTitolo.setId(PREFIX_VBOX+i);
            RowConstraints constraintTitolo = new RowConstraints();
            constraintTitolo.setPercentHeight(40);

            Label lblValore = getLabelValore(datiStatistiche.get(i));
            VBox vbValore = new VBox(lblValore);
            vbValore.setAlignment(Pos.CENTER);
            vbValore.setId(PREFIX_VBOX+i);
            RowConstraints constraintValore = new RowConstraints();
            constraintValore.setPercentHeight(60);

            ColumnConstraints columnConstraint = new ColumnConstraints();
            columnConstraint.setPercentWidth(100);
            gp.getRowConstraints().addAll(constraintTitolo, constraintValore);
            gp.getColumnConstraints().addAll(columnConstraint);
            gp.add(vbTitolo, 0,0);
            gp.add(vbValore, 0,1);

            if(i!=0){
                gp.setVisible(false);
            }
            gridPaneList.add(gp);
        }
    }

    private Label getLabelTitolo(DatiStatistica datiStatistica) {
        Label lbl = new Label();
        lbl.setId(PREFIX_LABEL_TITOLO+datiStatistica.getId());
        lbl.setAlignment(Pos.CENTER);
        lbl.setFont(getFontTitolo(datiStatistica.getFontSize()));
        lbl.setText(datiStatistica.getTesto());
        return lbl;
    }

    private Label getLabelValore(DatiStatistica datiStatistica) {
        Label lbl = new Label();
        lbl.setId(PREFIX_LABEL_VALORE+datiStatistica.getId());
        lbl.setAlignment(Pos.CENTER);
        lbl.setFont(getFont7Segmenti(datiStatistica.getFontSizeValore()));
        lbl.setText("---");
        return lbl;
    }


    private Font getFontTitolo(int fontSize) {
//        Font font = new Font("System Bold", fontSize);
        Font font = new Font("Monospaced Bold", fontSize);
        return font;
    }
    private Font getFont7Segmenti(int fontSize) {

        InputStream is = VisualizzatoreController.class.getResourceAsStream(FONT_PATH);
        Font font = Font.loadFont(is, fontSize);
        if(font == null){
            System.err.println("Font non trovato, carico standard");
            font = new Font("System Bold", 400);
        }
        return font;
    }

    private void setDimensioni(){
        PREF_WIDTH = config.getDimensioni().get("pref_width");
        PREF_HEIGHT = config.getDimensioni().get("pref_height");
    }

}