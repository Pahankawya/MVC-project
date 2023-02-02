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
import javafx.stage.Stage;
import lk.ijse.librarydb.Db.DBConnection;
import lk.ijse.librarydb.model.CustomerModel;
import lk.ijse.librarydb.to.Customer;
import lk.ijse.librarydb.view.tm.customerTM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddNewCustomerFormController {

    ObservableList<Customer> customersList = FXCollections.observableArrayList();

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<Customer> tbl;

    @FXML
    private TableColumn<Customer, String> tblId;

    @FXML
    private TableColumn<Customer, String> tblName;

    @FXML
    private TableColumn<Customer, String> tblAge;

    @FXML
    private TableColumn<Customer, String> tblPhone;

    @FXML
    private TableColumn<Customer, String> tblAdd;

    @FXML
    private TableColumn<Customer, String> tblMail;

    @FXML
    private JFXButton searchIcon;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtAge;

    @FXML
    private JFXTextField txtPhone;

    @FXML
    private JFXTextField txtAdd;

    @FXML
    private JFXTextField txtMail;

    @FXML
    private JFXTextField txtID;

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

    @FXML
    private Label lblUserNameWarning5;

    @FXML
    private TextField txtIdSearch;
    private Matcher idMatcher;
    private Matcher nameMatcher;
    private Matcher ageMatcher;
    private Matcher mobileMatcher;
    private Matcher addressMatcher;
    private Matcher mailMatcher;

    public void setPatterns() {
        Pattern idPattern = Pattern.compile("^(C)[0-9][0-9][0-9]$");
        idMatcher = idPattern.matcher(txtID.getText());

        Pattern namePattern = Pattern.compile("^[a-z.\\sA-Z.\\s]{4,}$");
        nameMatcher = namePattern.matcher(txtName.getText());

        Pattern agePattern = Pattern.compile("^(1[89]|[1-9]\\d)$");
        ageMatcher = agePattern.matcher(txtAge.getText());

        Pattern mobilePattern = Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9,10}$");
        mobileMatcher = mobilePattern.matcher(txtPhone.getText());

        Pattern addressPattern = Pattern.compile("^[a-z.\\sA-Z.\\s]{4,}$");
        addressMatcher = addressPattern.matcher(txtAdd.getText());

        Pattern mailPattern = Pattern.compile("^([a-z0-9]{2,})([@])([a-z]{2,9})([.])([a-z]{2,})$");
        mailMatcher = mailPattern.matcher(txtMail.getText());

    }

    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        String id = txtID.getText();
        String name = txtName.getText();
        String age = txtAge.getText();
        String phone = txtPhone.getText();
        String address = txtPhone.getText();
        String email = txtMail.getText();
        patternPerfom();
        Customer customer = new Customer(id, name, age, phone, address, email);
        try {
            boolean isAdded = CustomerModel.add(customer);

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Added!").show();
                refreshTable();
                clearTextField();
//            if (!isAdded == Boolean.parseBoolean(null)) {
//                System.out.println("Error here");
//            }
            } else {
                System.out.println("error");
                new Alert(Alert.AlertType.WARNING, "Something Happened!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);

            //TODO OTHER METHOD!


//        String SQL = "INSERT INTO customer VALUES (?,?,?,?,?,?)";
//        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
//
//        stm.setString(1, id);
//        stm.setString(2, name);
//        stm.setString(3, age);
//        stm.setString(4, phone);
//        stm.setString(5, address);
//        stm.setString(6, email);
//
//        int executeUpdate = stm.executeUpdate();
//        if (executeUpdate > 0) {
//            new Alert(Alert.AlertType.CONFIRMATION, "Customer Added!").show();
//            refreshTable();
//        } else {
//            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
//        }
//    }
        }
        patternPerfom();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String CusId = txtIdSearch.getText();

        try {
            Customer customer = CustomerModel.search(CusId);
            if (customer != null) {
                fillData(customer);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);


            //TODO OTHER METHOD!
//
//
//        String SQL = "Select * From customer where Cusid=?";
//
//
//        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(SQL);
//
//        statement.setObject(1, id);
//
//        ResultSet executeQuery = statement.executeQuery();
//
//        if (executeQuery.next()) {
//            txtName.setText(executeQuery.getString("name"));
//            txtAge.setText(executeQuery.getString("age"));
//            txtPhone.setText(executeQuery.getString("phone_no"));
//            txtAdd.setText(executeQuery.getString("address"));
//            txtMail.setText(executeQuery.getString("email"));
//        }
//    }
        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (CustomerModel.delete(txtID.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Data Deleted!").show();
            refreshTable();
            clearTextField();
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "No data").show();

            //TODO other method!
//        String id = txtID.getText();
//
//
//        String SQL = "Delete  From Customer where Cusid=?";
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
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        patternPerfom();
        try {
            if (CustomerModel.customerUpdate(

                    new Customer(txtID.getText(),
                            txtName.getText(),
                            txtAge.getText(),
                            txtPhone.getText(),
                            txtAdd.getText(),
                            txtMail.getText())
            ))

            {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
                clearTextField();
                refreshTable();

            } else {
                new Alert(Alert.AlertType.ERROR, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();

            //TODO OTHER METHOD!
//        String id = txtID.getText();
//        String name = txtName.getText();
//        String age = txtAge.getText();
//        String phone = txtPhone.getText();
//        String address = txtPhone.getText();
//        String email = txtMail.getText();
//
//        String SQL = "Update customer set name=?,age =?, phone_no=?, address=?, email=? where Cusid=?";
//        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
//
//        stm.setString(1, name);
//        stm.setString(2, age);
//        stm.setString(3, phone);
//        stm.setString(4, address);
//        stm.setString(5, email);
//        stm.setString(6, id);
//
//
//
//        int executeUpdate = stm.executeUpdate();
//
//        if (executeUpdate > 0) {
//            new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
//        }else {
//            new Alert(Alert.AlertType.WARNING, "Search first").show();
        }
    }

    @FXML
    void txtUserSearchKeyTypedOnAction(KeyEvent event) {

    }

    public void initialize() {
        setPatterns();
        loadData();
        Platform.runLater(() -> txtID.requestFocus());
    }

    public void loadData() {

        String SQL = "Select * From Customer";
        try {
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(SQL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                customersList.add(new Customer(resultSet.getString("Cusid"), resultSet.getString("name"),
                        resultSet.getString("age"), resultSet.getString("phone_no"), resultSet.getString("address"),
                        resultSet.getString("email")));
                tbl.setItems(customersList);

                tbl.setItems(customersList);
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }

        tblId.setCellValueFactory(new PropertyValueFactory<>("Cusid"));
        tblName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        tblPhone.setCellValueFactory(new PropertyValueFactory<>("phone_no"));
        tblAdd.setCellValueFactory(new PropertyValueFactory<>("address"));
        tblMail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    public void refreshTable() {
        customersList.clear();
        String SQL = "Select * From Customer";
        try {
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(SQL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                customersList.add(new Customer(resultSet.getString("Cusid"), resultSet.getString("name"),
                        resultSet.getString("age"), resultSet.getString("phone_no"), resultSet.getString("address"),
                        resultSet.getString("email")));
                tbl.setItems(customersList);

                tbl.setItems(customersList);
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }

        tblId.setCellValueFactory(new PropertyValueFactory<>("Cusid"));
        tblName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        tblPhone.setCellValueFactory(new PropertyValueFactory<>("phone_no"));
        tblAdd.setCellValueFactory(new PropertyValueFactory<>("address"));
        tblMail.setCellValueFactory(new PropertyValueFactory<>("email"));

    }

    public void fillData(Customer customer) {
        txtID.setText(customer.getCusid());
        txtName.setText(customer.getName());
        txtAge.setText(customer.getAge());
        txtPhone.setText(customer.getPhone_no());
        txtAdd.setText(customer.getAddress());
        txtMail.setText(customer.getEmail());

    }

    public void clearTextField() {
        txtID.setText("");
        txtName.setText("");
        txtAge.setText("");
        txtPhone.setText("");
        txtAdd.setText("");
        txtMail.setText("");
        txtIdSearch.setText("");
    }

    public void NameOnAction(ActionEvent actionEvent) {
        txtAge.requestFocus();
    }

    public void AgeOnAction(ActionEvent actionEvent) {
        txtPhone.requestFocus();
    }

    public void contactOnAction(ActionEvent actionEvent) {
        txtAdd.requestFocus();
    }

    public void AddressOnAction(ActionEvent actionEvent) {
        txtMail.requestFocus();
    }

    public void idOnAction(ActionEvent actionEvent) {
        txtName.requestFocus();
    }

    public void searchIdOnAction(ActionEvent actionEvent) {
        searchIcon.fire();
    }

    public void patternPerfom() {
        if (idMatcher.matches()) {
            if (nameMatcher.matches()) {
                if (ageMatcher.matches()) {
                    if (mobileMatcher.matches()) {
                        if (addressMatcher.matches()) {
                            if (mailMatcher.matches()) {
                                System.out.println("register action now perform");
                            } else {
                                txtMail.requestFocus();
                                txtMail.setFocusColor(Paint.valueOf("Red"));
                                lblUserNameWarning5.setText("invalid");
                            }
                        } else {
                            txtAdd.requestFocus();
                            txtAdd.setFocusColor(Paint.valueOf("Red"));
                            lblUserNameWarning4.setText("invalid");
                        }
                    } else {
                        txtPhone.requestFocus();
                        txtPhone.setFocusColor(Paint.valueOf("Red"));
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
    void txtUserAddressKeyTypedOnAction(KeyEvent event) {
        lblUserNameWarning4.setText("");
        txtAdd.setFocusColor(Paint.valueOf("Green"));

        Pattern addressPattern = Pattern.compile("^[a-z.\\sA-Z.\\s]{4,}$");
        addressMatcher = addressPattern.matcher(txtAdd.getText());

        if (!addressMatcher.matches()) {

            txtAdd.requestFocus();
            txtAdd.setFocusColor(Paint.valueOf("Red"));
            lblUserNameWarning4.setText("invalid");
        }
    }

    @FXML
    void txtUserAgeKeyTypedOnAction(KeyEvent event) {
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
    void txtUserContactKeyTypedOnAction(KeyEvent event) {
        lblUserNameWarning3.setText("");
        txtPhone.setFocusColor(Paint.valueOf("Green"));

        Pattern mobilePattern = Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9,10}$");
        mobileMatcher = mobilePattern.matcher(txtPhone.getText());

        if (!mobileMatcher.matches()) {

            txtPhone.requestFocus();
            txtPhone.setFocusColor(Paint.valueOf("Red"));
            lblUserNameWarning3.setText("invalid");
        }
    }

    @FXML
    void txtUserIdKeyTypedOnAction(KeyEvent event) {
        lblUserNameWarning.setText("");
        txtID.setFocusColor(Paint.valueOf("Green"));

        Pattern idPattern = Pattern.compile("^(C)[0-9][0-9][0-9]$");
        idMatcher = idPattern.matcher(txtID.getText());

        if (!idMatcher.matches()) {
//            System.out.println(txtUserName.getText());
            txtID.requestFocus();
            txtID.setFocusColor(Paint.valueOf("Red"));
            lblUserNameWarning.setText("invalid");

        }
    }

    @FXML
    void txtUserMailKeyTypedOnAction(KeyEvent event) {
        lblUserNameWarning5.setText("");
        txtMail.setFocusColor(Paint.valueOf("Green"));

        Pattern mailPattern = Pattern.compile("^([a-z0-9]{2,})([@])([a-z]{2,9})([.])([a-z]{2,})$");
        mailMatcher = mailPattern.matcher(txtMail.getText());

        if (!mailMatcher.matches()) {

            txtMail.requestFocus();
            txtMail.setFocusColor(Paint.valueOf("Red"));
            lblUserNameWarning5.setText("invalid");

        }
    }

    @FXML
    void txtUserNameKeyTypedOnAction(KeyEvent event) {
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

    public void btnCancelOnAction(ActionEvent actionEvent) {
        pathToUI("admin");
    }
    public void pathToUI(String UI){
        Stage window = (Stage)  pane.getScene().getWindow();
        window.close();
    }
}




