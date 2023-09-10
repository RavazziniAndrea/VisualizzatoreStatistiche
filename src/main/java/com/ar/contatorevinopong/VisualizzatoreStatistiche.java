package com.ar.contatorevinopong;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class VisualizzatoreStatistiche extends Application {



    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(VisualizzatoreController.class.getResource("main-view.fxml"));

        try{
            Configurazione config = Configurazione.leggiConfigFile();
            System.out.println(config.toString());
        } catch (IOException e) {
            System.err.println("Impossibile leggere file config. Abort");
            System.exit(1);
        }

//        Scene scene = new Scene(fxmlLoader.load(), 1360, 768);
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);

        stage.setTitle("Statistiche");
        stage.setScene(scene);
        //stage.setFullScreen(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}