package lk.ijse.librarydb.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.librarydb.Db.DBConnection;
import org.controlsfx.control.Notifications;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class LoginFormController {
    public JFXButton btnLogin;
    @FXML
    private AnchorPane pane;

    @FXML
    private JFXTextField txtusername;


    @FXML
    private JFXPasswordField txtpw;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private Label warningmsgLbl;

    @FXML
    void cancelOnAction(ActionEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void loginOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        if (txtusername.getText().isEmpty() == false && txtpw.getText().isEmpty() == false) {
            validateLogin();
            validateLogin2();
        } else {
            warningmsgLbl.setText("Please enter username and password");
            Notifications notificationsBuilder = Notifications.create()
                    .title("Wrong")
                    .text("Username and Password doesn't Match!")
                    .graphic(null)
                    .hideAfter(Duration.seconds(8))
                    .position(Pos.BOTTOM_RIGHT);
            notificationsBuilder.showError();
        }
    }

    public void validateLogin() {


        String SQL = " SELECT count(1) FROM librarian WHERE userName ='" + txtusername.getText() + "' AND password ='" + txtpw.getText() + "'";

        try {
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(SQL);

            ResultSet queryResult = statement.executeQuery(SQL);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
//                    Navigation.navigate(Routes.ADMIN, pane);
                    Stage stage = (Stage) pane.getScene().getWindow();
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/librarydb/view/admin.fxml"))));
                    stage.centerOnScreen();
//                    Image image = new Image("/lk/ijse/librarydb/assests/logo/green-check-mark-icon-tick-symbol-in-green-color-vector-2AA0012-removebg-preview.png");
                    Notifications notificationsBuilder = Notifications.create()
                            .title("Successfull")
                            .text("Successfully Logged in as a Librarian")
                            .graphic(null)
                            .hideAfter(Duration.seconds(8))
                            .position(Pos.BOTTOM_RIGHT);
                            notificationsBuilder.showInformation();


                } else {
                    warningmsgLbl.setText("Invalid login. Please try again ");

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void validateLogin2() {


        String SQL = " SELECT count(1) FROM staff WHERE userName ='" + txtusername.getText() + "' AND password ='" + txtpw.getText() + "'";

        try {
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(SQL);

            ResultSet queryResult = statement.executeQuery(SQL);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    Stage stage = (Stage) pane.getScene().getWindow();
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/librarydb/view/employee2Form.fxml"))));
                    stage.centerOnScreen();
                    Notifications notificationsBuilder = Notifications.create()
                            .title("Successfull")
                            .text("Successfully Logged in as an Employee")
                            .graphic(null)
                            .hideAfter(Duration.seconds(8))
                            .position(Pos.BOTTOM_RIGHT);
                    notificationsBuilder.showInformation();


                } else {
                    warningmsgLbl.setText("Inavalid login ");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //TODO CHECK IT OUT -------------------------------------------------------

    public void initialize() {
        txtusername.requestFocus();
    }

    public void userNameOnAction(ActionEvent actionEvent) {
        txtpw.requestFocus();
    }

    public void passwordOnAction(ActionEvent actionEvent) {
        btnLogin.fire();
    }

    //TODO --------------------------------------------------------------------

}


