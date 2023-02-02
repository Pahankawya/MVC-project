package lk.ijse.librarydb.controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    private JFXButton btnBooks;

    @FXML
    private JFXButton btnCategory;

    @FXML
    private JFXButton btnCustomer;
    @FXML
    private Label lblDate;

    @FXML
    private JFXButton btnStaff;
    @FXML
    private JFXButton btnReturn;

    @FXML
    private JFXButton btnBorrow;

    @FXML
    private JFXButton btnAttendance;

    @FXML
    private JFXButton btnSupp;

    @FXML
    private JFXButton btnReports;
    @FXML
    private Label lblTime;

    @FXML
    private AnchorPane pane;
    boolean bookManagementCL = true;
    boolean bookCategoryCL = true;
    boolean customerManagementCL = true;
    boolean employeeManageCL = true;
    boolean borrowCL = true;
    boolean attendanceCL = true;
    boolean supplierCL = true;
    boolean reportsCL = true;
    boolean returnCL = true;

    private volatile boolean stop = false;

    @FXML
    void btnGo1OnAction(ActionEvent event) throws IOException {
        setUi("/lk/ijse/librarydb/view/bookManagement");
        loadCL();
        if (bookManagementCL) {
            btnBooks.setStyle("-fx-background-color: #1620A1");
            bookManagementCL = false;
        }
        bookManagementCL = true;
    }

    @FXML
    void btnGo2OnAction(ActionEvent event) throws IOException {
        setUi("/lk/ijse/librarydb/view/bookCategory");
        loadCL();
        if (bookCategoryCL) {
            btnCategory.setStyle("-fx-background-color: #1620A1");
            bookCategoryCL = false;
        }
        bookCategoryCL = true;
    }

    @FXML
    void btnGo3OnAction(ActionEvent event) throws IOException {
        setUi("/lk/ijse/librarydb/view/customerManagement");
        loadCL();
        if (customerManagementCL) {
            btnCustomer.setStyle("-fx-background-color: #1620A1");
            customerManagementCL = false;
        }
        customerManagementCL = true;
    }


    @FXML
    void btnGo4OnActions(ActionEvent event) throws IOException {
        setUi("/lk/ijse/librarydb/view/employeeManagement");
        loadCL();
        if (employeeManageCL) {
            btnStaff.setStyle("-fx-background-color: #1620A1");
            employeeManageCL = false;
        }
        employeeManageCL = true;
    }


    @FXML
    void btnGo5OnAction(ActionEvent event) throws IOException {
        setUi("/lk/ijse/librarydb/view/borrowReturnManage");
        loadCL();
        if (borrowCL) {
            btnBorrow.setStyle("-fx-background-color: #1620A1");
            borrowCL = false;
        }
        borrowCL = true;
    }


    @FXML
    void btnGo6OnAction(ActionEvent event) throws IOException {
        setUi("/lk/ijse/librarydb/view/attendanceManagement");
        loadCL();
        if (attendanceCL) {
            btnAttendance.setStyle("-fx-background-color: #1620A1");
            attendanceCL = false;
        }
        attendanceCL = true;

    }

    @FXML
    void btnGo7OnAction(ActionEvent event) throws IOException {
        setUi("/lk/ijse/librarydb/view/supplierDetails");
        loadCL();
        if (supplierCL) {
            btnSupp.setStyle("-fx-background-color: #1620A1");
            supplierCL = false;
        }
        supplierCL = true;
    }

    @FXML
    void btngo8OnAction(ActionEvent event) throws IOException {
        setUi("/lk/ijse/librarydb/view/reportForm");
        loadCL();
        if (reportsCL) {
            btnReports.setStyle("-fx-background-color: #1620A1");
            reportsCL = false;
        }
        reportsCL = true;

    }

    @FXML
    void btnGo9OnAction(ActionEvent event) throws IOException {
        setUi("/lk/ijse/librarydb/view/returnForm");
        loadCL();
        if (returnCL) {
            btnReturn.setStyle("-fx-background-color: #1620A1");
            returnCL = false;
        }
        returnCL = true;
    }

        @FXML
    void goBackOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/librarydb/view/LoginForm.fxml"))));
        stage.centerOnScreen();
            Notifications notificationsBuilder = Notifications.create()
                    .title("Logging out!")
                    .text("Successfully Logged out!!")
                    .graphic(null)
                    .hideAfter(Duration.seconds(8))
                    .position(Pos.BOTTOM_RIGHT);
            notificationsBuilder.showError();

    }
    private void setUi(String ui) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource(ui +".fxml"));
        pane.getChildren().clear();
        pane.getChildren().add(load);
    }

    public void loadCL(){
        btnBooks.setStyle("-fx-background-color: #05071F");
        bookManagementCL=true;
        btnCategory.setStyle("-fx-background-color: #05071F");
        bookCategoryCL=true;
        btnCustomer.setStyle("-fx-background-color: #05071F");
        customerManagementCL = true;
        btnStaff.setStyle("-fx-background-color: #05071F");
        employeeManageCL = true;
        btnStaff.setStyle("-fx-background-color: #05071F");
        employeeManageCL = true;
        btnBorrow.setStyle("-fx-background-color: #05071F");
        borrowCL = true;
        btnAttendance.setStyle("-fx-background-color: #05071F");
        attendanceCL= true;
        btnSupp.setStyle("-fx-background-color: #05071F");
        supplierCL= true;
        btnReports.setStyle("-fx-background-color: #05071F");
        reportsCL= true;
        btnReturn.setStyle("-fx-background-color: #05071F");
        returnCL= true;
    }

    private void Timenow(){
        Thread thread = new Thread(() -> {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            while(!stop){
                try{
                    Thread.sleep(1000);
                }catch(Exception e){
                    System.out.println(e);
                }
                final String timenow = sdf.format(new Date());
                Platform.runLater(() -> {
                    lblTime.setText(timenow);
                });
            }
        });
        thread.start();
    }

    private void Date(){
        SimpleDateFormat sdf = new SimpleDateFormat("'Today is 'MMMM dd, yyyy");
        String datenow = sdf.format(new Date());
        lblDate.setText(datenow);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Timenow();
        Date();
    }
}
