package lk.ijse.librarydb.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.librarydb.Db.DBConnection;
import lk.ijse.librarydb.model.AttendanceModel;
import lk.ijse.librarydb.to.Attendance;
import lk.ijse.librarydb.to.Books;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class attendanceManagementController {
    ObservableList<Attendance> attendances = FXCollections.observableArrayList();

    @FXML
    private AnchorPane pane;

    @FXML
    private JFXTextField txtID;

    @FXML
    private TableView<Attendance> tbl;

    @FXML
    private TableColumn<Attendance, String> tblId;

    @FXML
    private TableColumn<Attendance, String> tblDate;

    @FXML
    private TableColumn<Attendance, String>tblInTime;

    @FXML
    private TableColumn<Attendance, String>tblOuttime;

    @FXML
    private JFXComboBox<String> cmbId;

    @FXML
    private JFXDatePicker dteDate;

    @FXML
    private JFXTimePicker cmbTime;

    @FXML
    private JFXTimePicker cmbOutTime;

    public void initialize(){
    loadStaffId();
    loadTable();
    }
    public void loadStaffId(){
        String sql = "Select sid from staff";
        try {
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            ObservableList<String> data = FXCollections.observableArrayList();
            while (resultSet.next()){
                data.add(resultSet.getString(1));
                cmbId.setItems(data);
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String Sid = cmbId.getValue();
        String date = dteDate.getValue().toString();
        String in_time = cmbTime.getValue().toString();
        String out_time = cmbOutTime.getValue().toString();


        Attendance attendance = new Attendance(Sid, date, in_time, out_time);

        try {
            boolean isAdded = AttendanceModel.add(attendance);

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Attendance marked!").show();
                    refreshTable();
                    clearTextField();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something Happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
            //TODO OTHER METHOD!


//
//
//        String SQL = "INSERT INTO attendance VALUES (?,?,?,?)";
//        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
//
//        stm.setString(1, Sid);
//        stm.setString(2, (date));
//        stm.setString(3, (intime));
//        stm.setString(4, (outtime));
//
//
//        int executeUpdate = stm.executeUpdate();
//        if (executeUpdate > 0) {
//            new Alert(Alert.AlertType.CONFIRMATION, "Attendance Marked!").show();
//
//        } else {
//            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
//        }
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        if (AttendanceModel.delete(cmbId.getValue())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Data Deleted!").show();
            refreshTable();
            clearTextField();
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "No data").show();
        }
    }

    public void clearTextField(){
        cmbId.setValue("");
        dteDate.getEditor().clear();
        cmbTime.getEditor().clear();
        cmbOutTime.getEditor().clear();
    }
    public void loadTable(){
        String SQL = "Select * From attendance";
        try {
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(SQL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                attendances.add(new Attendance(resultSet.getString("sid"), resultSet.getString("Date"),
                        resultSet.getString("int_time"), resultSet.getString("out_time")));
                tbl.setItems(attendances);

                tbl.setItems(attendances);
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }

        tblId.setCellValueFactory(new PropertyValueFactory<>("sid"));
        tblDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        tblInTime.setCellValueFactory(new PropertyValueFactory<>("int_time"));
        tblOuttime.setCellValueFactory(new PropertyValueFactory<>("out_time"));

    }
    public void refreshTable(){
        attendances.clear();
        String SQL = "Select * From attendance";
        try {
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(SQL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                attendances.add(new Attendance(resultSet.getString("sid"), resultSet.getString("Date"),
                        resultSet.getString("int_time"), resultSet.getString("out_time")));
                tbl.setItems(attendances);

                tbl.setItems(attendances);
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }

        tblId.setCellValueFactory(new PropertyValueFactory<>("sid"));
        tblDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        tblInTime.setCellValueFactory(new PropertyValueFactory<>("int_time"));
        tblOuttime.setCellValueFactory(new PropertyValueFactory<>("out_time"));

    }


    }


