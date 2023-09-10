package com.ar.visualizzatore;

import com.ar.visualizzatore.config.Config;
import com.ar.visualizzatore.config.ConfigParser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static java.lang.System.exit;

public class VisualizzatoreStatistiche extends Application {

    static Config config;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(VisualizzatoreController.class.getResource("main-view.fxml"));

        try{
            config = ConfigParser.getConfig();
            System.out.println(config.toString());
        } catch (IOException e) {
            System.err.println("Impossibile leggere file config. Abort");
            exit(1);
        }

        Scene scene = new Scene(fxmlLoader.load(), 1360, 768);
//        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);

        stage.setTitle("Statistiche");
        stage.setScene(scene);
//        stage.setFullScreen(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static Config getConfig() {
        return config;
    }
}