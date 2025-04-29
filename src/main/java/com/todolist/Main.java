package com.todolist;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Memuat layout dari file FXML
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/todolist.fxml")));

        //Membuat scene baru dan menambahkan CSS
        Scene scene = new Scene(root, 400, 600);

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());

        // Menyeting title dan scene aplikasi
        primaryStage.setTitle("To-Do-List App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        // Meluncurkan aplikasi javafx
        launch(args);
    }
}