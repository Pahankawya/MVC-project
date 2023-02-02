package lk.ijse.librarydb.util;



import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static AnchorPane pane;

    public static void navigate(Routes route, AnchorPane pane) throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage) Navigation.pane.getScene().getWindow();

        switch (route) {
            case ADMIN:
                window.setTitle("Admin Dashboard");
                initUI("admin.fxml");
                break;
            case LOGIN:
                window.setTitle("Login Form");
                initUI("LoginForm.fxml");
                break;
            case EMPLOYEE:
                window.setTitle("Employee Dashboard");
                initUI("employee2Form.fxml");
                break;
            case CATEGORY:
                window.setTitle("Category Form");
                initUI("categoryUpdate.fxml");
                break;
            default:
                new Alert(Alert.AlertType.ERROR, "Not suitable UI found!").show();

        }
    }

    private static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class
                .getResource("/lk/ijse/librarydb/view/" + location)));
    }

    public static void swatchNavigation(String link, ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("/lk/ijse/librarydb/view/categoryUpdate.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Category Window");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void swatchNavigation2(String link, ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("/lk/ijse/librarydb/view/addNewCustomerForm.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Category Window");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}