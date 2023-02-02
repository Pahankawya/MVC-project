package lk.ijse.librarydb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Appinitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/librarydb/view/LoginForm.fxml"))));
//      primaryStage.initStyle(StageStyle.UNDECORATED);
//        primaryStage.setMaximized(true);

        primaryStage.show();

    }
}
