package lk.ijse.librarydb.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.librarydb.Db.DBConnection;
import lk.ijse.librarydb.model.SupplierModel;
import lk.ijse.librarydb.to.Supplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class SupplierDetailsController {
    ObservableList<Supplier> supplierObservableList = FXCollections.observableArrayList();
    @FXML
    private AnchorPane pane;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtAge;

    @FXML
    private JFXTextField txtAdd;

    @FXML
    private JFXTextField txtID;

    @FXML
    private TableView<Supplier> tbl;

    @FXML
    private TableColumn<Supplier, String> tblId;

    @FXML
    private TableColumn<Supplier, String> tblName;

    @FXML
    private TableColumn<Supplier, String> tblAge;

    @FXML
    private TableColumn<Supplier, String> tblAddress;

    @FXML
    private TableColumn<Supplier, String> tblPhone;

    @FXML
    private JFXTextField txtPhone;

    @FXML
    private TextField txtIDsearch;
    @FXML
    private JFXButton searchIcon;
    @FXML

    private Label lblUserNameWarning;

    @FXML
    private Label lblUserNameWarning1;

    @FXML
    private Label lblUserNameWarning2;

    @FXML
    private Label lblUserNameWarning3;

    @FXML
    private Label lblUserNameWarning4;
    private Matcher idMatcher;
    private Matcher nameMatcher;
    private Matcher ageMatcher;
    private Matcher addressMatcher;
    private Matcher mobileMatcher;

    public void setPatterns(){
        Pattern idPattern = Pattern.compile("^(SP)[0-9][0-9]$");
        idMatcher = idPattern.matcher(txtID.getText());

        Pattern namePattern = Pattern.compile("^[a-z.\\sA-Z.\\s]{4,}$");
        nameMatcher = namePattern.matcher(txtName.getText());

        Pattern agePattern = Pattern.compile("^(1[89]|[1-9]\\d)$");
        ageMatcher = agePattern.matcher(txtAge.getText());

        Pattern addressPattern = Pattern.compile("^[a-z.\\sA-Z.\\s]{4,}$");
        addressMatcher = addressPattern.matcher(txtAdd.getText());

        Pattern mobilePattern = Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9,10}$");
        mobileMatcher = mobilePattern.matcher(txtPhone.getText());
    }
    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String SupplierId = txtID.getText();
        String name = txtName.getText();
        String age = txtAge.getText();
        String address = txtAdd.getText();
        String phone = txtPhone.getText();
        performPattern();
        Supplier supplier = new Supplier(SupplierId, name, age, address, phone);


        boolean isAdded = SupplierModel.add(supplier);

        if (isAdded) {
            new Alert(Alert.AlertType.CONFIRMATION, "Supplier Added!").show();
            refreshTable();
        } else {
            new Alert(Alert.AlertType.WARNING, "Something Happened!").show();

            //TODO OTHER METHOD!
//        String SQL = "INSERT INTO supplier VALUES (?,?,?,?,?)";
//        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
//
//        stm.setString(1, supplierId);
//        stm.setString(2, name);
//        stm.setString(3, age);
//        stm.setString(4, address);
//        stm.setString(5, phone_no);
//
//
//        int executeUpdate = stm.executeUpdate();
//        if (executeUpdate > 0) {
//            new Alert(Alert.AlertType.CONFIRMATION, "Supplier Added!").show();
//            refreshTable();
//        } else {
//            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
//        }
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (SupplierModel.delete(txtIDsearch.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Data Deleted!").show();
            refreshTable();
            clearTextField();
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "No data").show();

            //TODO OTHER METHOD!
//        String id = txtID.getText();
//
//
//        String SQL = "Delete  From supplier where supplierId=?";
//
//        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
//
//        stm.setObject(1, id);
//
//        int executeUpdate = stm.executeUpdate();
//        if (executeUpdate > 0) {
//            new Alert(Alert.AlertType.CONFIRMATION, "Data Deleted!").show();
//            refreshTable();
//        } else {
//            new Alert(Alert.AlertType.CONFIRMATION, "No data").show();
//        }
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        String SupplierId = txtIDsearch.getText();

        try {
            Supplier supplier = SupplierModel.search(SupplierId);
            if (supplier != null) {
                fillData(supplier);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);

            //TODO OTHER METHOD!
//
//        String SQL = "Select * From supplier where supplierId=?";
//
//
//        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(SQL);
//
//        statement.setObject(1, id);
//
//        ResultSet executeQuery = statement.executeQuery();
//
//        if (executeQuery.next()) {
//            txtID.setText(executeQuery.getString("supplierId"));
//            txtName.setText(executeQuery.getString("name"));
//            txtAge.setText(executeQuery.getString("age"));
//            txtAdd.setText(executeQuery.getString("address"));
//            txtPhone.setText(executeQuery.getString("phone_no"));
//        }
//    }

        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        setPatterns();
        try {
            if (SupplierModel.supplierUpdate(
                    new Supplier(txtID.getText(),
                            txtName.getText(),
                            txtAge.getText(),
                            txtAdd.getText(),
                            txtPhone.getText())
            )) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
                clearTextField();
                refreshTable();

            } else {
                new Alert(Alert.AlertType.ERROR, "Something Happened!").show();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();

            //TODO OTHER METHOD!
//        String id = txtID.getText();
//        String name = txtName.getText();
//        String age = txtAge.getText();
//        String address = txtAdd.getText();
//        String phone = txtPhone.getText();
//
//        String SQL = "Update supplier set name=?, age =?, address=?, phone_no=? where supplierId=?";
//        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
//
//        stm.setString(1, name);
//        stm.setString(2, age);
//        stm.setString(3, address);
//        stm.setString(4, phone);
//        stm.setString(5, id);
//
//
//        int executeUpdate = stm.executeUpdate();
//
//        if (executeUpdate > 0) {
//            new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
//            refreshTable();
//        } else {
//            new Alert(Alert.AlertType.WARNING, "Search first").show();
//        }

        }
    }

    public void initialize() {
        setPatterns();
            loadData();
        Platform.runLater(() -> txtID.requestFocus());
    }
    public void loadData() {

        String SQL = "Select * From supplier";
        try {
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(SQL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                supplierObservableList.add(new Supplier(resultSet.getString("supplierId"), resultSet.getString("name"),
                        resultSet.getString("age"), resultSet.getString("address"),
                        resultSet.getString("phone_no")));
                tbl.setItems(supplierObservableList);

                tbl.setItems(supplierObservableList);
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }

        tblId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        tblName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        tblAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tblPhone.setCellValueFactory(new PropertyValueFactory<>("phone_no"));

    }
    public void refreshTable(){
        supplierObservableList.clear();
        String SQL = "Select * From supplier";
        try {
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(SQL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                supplierObservableList.add(new Supplier(resultSet.getString("supplierId"), resultSet.getString("name"),
                        resultSet.getString("age"), resultSet.getString("address"),
                        resultSet.getString("phone_no")));
                tbl.setItems(supplierObservableList);

                tbl.setItems(supplierObservableList);
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }

        tblId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        tblName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        tblAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tblPhone.setCellValueFactory(new PropertyValueFactory<>("phone_no"));

    }
    public void clearTextField(){
        txtID.setText("");
        txtName.setText("");
        txtAge.setText("");
        txtAdd.setText("");
        txtPhone.setText("");
        txtIDsearch.setText("");
    }
    public void fillData(Supplier supplier){
        txtID.setText(supplier.getSupplierId());
        txtName.setText(supplier.getName());
        txtAge.setText(supplier.getAge());
        txtAdd.setText(supplier.getAddress());
        txtPhone.setText(supplier.getPhone_no());

    }

    public void nameOnAction(ActionEvent actionEvent) {
        txtAge.requestFocus();
    }

    public void ageOnAction(ActionEvent actionEvent) {
        txtAdd.requestFocus();
    }

    public void addressOnAction(ActionEvent actionEvent) {
        txtPhone.requestFocus();
    }

    public void idOnAction(ActionEvent actionEvent) {
        txtName.requestFocus();

    }

    public void contactOnAction(ActionEvent actionEvent) {

    }

    public void searchIdOnAction(ActionEvent actionEvent) {
        searchIcon.fire();
    }
    public void performPattern(){
        if (idMatcher.matches()) {
            if (nameMatcher.matches()) {
                if (ageMatcher.matches()) {
                    if (addressMatcher.matches()) {
                        if (mobileMatcher.matches()) {
                                System.out.println("register action now perform");

                        } else {
                            txtPhone.requestFocus();
                            txtPhone.setFocusColor(Paint.valueOf("Red"));
                            lblUserNameWarning4.setText("invalid");
                        }
                    } else {
                        txtAdd.requestFocus();
                        txtAdd.setFocusColor(Paint.valueOf("Red"));
                        lblUserNameWarning3.setText("invalid");
                    }
                } else {
                    txtAge.requestFocus();
                    txtAge.setFocusColor(Paint.valueOf("Red"));
                    lblUserNameWarning2.setText("invalid");
                }
            } else {
                txtName.requestFocus();
                txtName.setFocusColor(Paint.valueOf("Red"));
                lblUserNameWarning1.setText("invalid");
            }
        } else {
            txtID.requestFocus();
            txtID.setFocusColor(Paint.valueOf("Red"));
            lblUserNameWarning.setText("invalid");
        }
    }

    @FXML
    void keyAddressRleasedOnAction(KeyEvent event) {
        lblUserNameWarning3.setText("");
        txtAdd.setFocusColor(Paint.valueOf("Green"));

        Pattern addressPattern = Pattern.compile("^[a-z.\\sA-Z.\\s]{4,}$");
        addressMatcher = addressPattern.matcher(txtAdd.getText());

        if (!addressMatcher.matches()) {

            txtAdd.requestFocus();
            txtAdd.setFocusColor(Paint.valueOf("Red"));
            lblUserNameWarning3.setText("invalid");
        }
    }

    @FXML
    void keyAgeRleasedOnAction(KeyEvent event) {
        lblUserNameWarning2.setText("");
        txtAge.setFocusColor(Paint.valueOf("Green"));

        Pattern agePattern = Pattern.compile("^(1[89]|[1-9]\\d)$");
        ageMatcher = agePattern.matcher(txtAge.getText());

        if (!ageMatcher.matches()) {

            txtAge.requestFocus();
            txtAge.setFocusColor(Paint.valueOf("Red"));
            lblUserNameWarning2.setText("invalid");
        }
    }

    @FXML
    void keyIdRleasedOnAction(KeyEvent event) {
        lblUserNameWarning.setText("");
        txtID.setFocusColor(Paint.valueOf("Green"));

        Pattern idPattern = Pattern.compile("^(SP)[0-9][0-9]$");
        idMatcher = idPattern.matcher(txtID.getText());

        if (!idMatcher.matches()) {

            txtID.requestFocus();
            txtID.setFocusColor(Paint.valueOf("Red"));
            lblUserNameWarning.setText("invalid");
        }
    }

    @FXML
    void keyNameRleasedOnAction(KeyEvent event) {
        lblUserNameWarning1.setText("");
        txtName.setFocusColor(Paint.valueOf("Green"));

        Pattern namePattern = Pattern.compile("^[a-z.\\sA-Z.\\s]{4,}$");
        nameMatcher = namePattern.matcher(txtName.getText());

        if (!nameMatcher.matches()) {

            txtName.requestFocus();
            txtName.setFocusColor(Paint.valueOf("Red"));
            lblUserNameWarning1.setText("invalid");
        }
    }

    @FXML
    void keyPhoneNoRleasedOnAction(KeyEvent event) {
        lblUserNameWarning4.setText("");
        txtPhone.setFocusColor(Paint.valueOf("Green"));

        Pattern mobilePattern = Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9,10}$");
        mobileMatcher = mobilePattern.matcher(txtPhone.getText());

        if (!mobileMatcher.matches()) {

            txtPhone.requestFocus();
            txtPhone.setFocusColor(Paint.valueOf("Red"));
            lblUserNameWarning4.setText("invalid");
        }
    }
}

