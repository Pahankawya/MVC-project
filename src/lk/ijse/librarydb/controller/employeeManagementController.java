package lk.ijse.librarydb.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import lk.ijse.librarydb.Db.DBConnection;
import lk.ijse.librarydb.model.EmployeeModel;
import lk.ijse.librarydb.to.Employee;
import org.controlsfx.control.Notifications;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class employeeManagementController {

    ObservableList<Employee> employeeObservableList = FXCollections.observableArrayList();
    @FXML
    private AnchorPane pane;

    @FXML
    private JFXTextField txtFirstName;

    @FXML
    private JFXTextField txtLastName;

    @FXML
    private JFXTextField txtAge;

    @FXML
    private JFXTextField txtMobile;

    @FXML
    private JFXTextField txtAdd;

    @FXML
    private JFXTextField txtID;

    @FXML
    private TableView<Employee> tbl;

    @FXML
    private TableColumn<Employee, String> tblId;

    @FXML
    private TableColumn<Employee, String> tblfirstName;

    @FXML
    private TableColumn<Employee, String> tblLastName;

    @FXML
    private TableColumn<Employee, String> tblAge;

    @FXML
    private TableColumn<Employee, String> tblNic;

    @FXML
    private TableColumn<Employee, String> tblMobile;

    @FXML
    private TableColumn<Employee, String> tblAdd;

    @FXML
    private JFXButton searchIcon;

    @FXML
    private TableColumn<Employee, String> tblUserName;

    @FXML
    private TableColumn<Employee, String> tblPassword;
    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtNic;
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
    private Label lblUserNameWarning6;

    @FXML
    private Label lblUserNameWarning7;

    @FXML
    private Label lblUserNameWarning8;
    @FXML
    private TextField txtID1;
    private Matcher idMatcher;
    private Matcher firstNameMatcher;
    private Matcher lastNameMatcher;
    private Matcher ageMatcher;
    private Matcher nicMatcher;
    private Matcher mobileMatcher;
    private Matcher addressMatcher;
    private Matcher userNameMatcher;
    private Matcher passwordMatcher;

    public void setPatterns() {
        Pattern idPattern = Pattern.compile("^(S)[0-9][0-9][0-9]$");
        idMatcher = idPattern.matcher(txtID.getText());

        Pattern firstNamePattern = Pattern.compile("^[a-zA-Z]{2,10}$");
        firstNameMatcher = firstNamePattern.matcher(txtFirstName.getText());

        Pattern lastNamePattern = Pattern.compile("^[a-zA-Z]{2,10}$");
        lastNameMatcher = lastNamePattern.matcher(txtLastName.getText());

        Pattern agePattern = Pattern.compile("^(1[89]|[1-9]\\d)$");
        ageMatcher = agePattern.matcher(txtAge.getText());

        Pattern nicPattern = Pattern.compile("^[0-9]{12}$");
        nicMatcher = nicPattern.matcher(txtNic.getText());

        Pattern mobilePattern = Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9,10}$");
        mobileMatcher = mobilePattern.matcher(txtMobile.getText());

        Pattern addressPattern = Pattern.compile("^[a-z.\\sA-Z.\\s]{4,}$");
        addressMatcher = addressPattern.matcher(txtAdd.getText());

        Pattern userNamePattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        userNameMatcher = userNamePattern.matcher(txtUserName.getText());

        Pattern pwPattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
        passwordMatcher = pwPattern.matcher(txtPassword.getText());

    }

    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String Sid = txtID.getText();
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String age = txtAge.getText();
        String nic = txtNic.getText();
        String phone_no = txtMobile.getText();
        String address = txtAdd.getText();
        String userName = txtUserName.getText();
        String password = txtPassword.getText();
        patternPerform();
        Employee employee = new Employee(Sid, firstName, lastName, age, nic, phone_no, address, userName, password);
        try {
            boolean isAdded = EmployeeModel.add(employee);
            if (isAdded) {
                Notifications notificationsBuilder = Notifications.create()
                        .title("Done!")
                        .text("Employee registration successful")
                        .graphic(null)
                        .hideAfter(Duration.seconds(8))
                        .position(Pos.BOTTOM_RIGHT);
                notificationsBuilder.showConfirm();
                refreshTable();
                clearTextField();

            } else {
                Notifications notificationsBuilder = Notifications.create()
                        .title("Wrong!")
                        .text("Employee registration unsuccessful")
                        .graphic(null)
                        .hideAfter(Duration.seconds(8))
                        .position(Pos.BOTTOM_RIGHT);
                notificationsBuilder.showError();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);

            //TODO other method!
//        String SQL = "INSERT INTO staff VALUES (?,?,?,?,?,?,?,?,?)";
//        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
//
//        stm.setString(1, id);
//        stm.setString(2, firstName);
//        stm.setString(3, lastName);
//        stm.setString(4, age);
//        stm.setString(5, nic);
//        stm.setString(6, Mobile);
//        stm.setString(7, address);
//        stm.setString(8, userName);
//        stm.setString(9, password);
//
//        int executeUpdate = stm.executeUpdate();
//        if (executeUpdate > 0) {
//            new Alert(Alert.AlertType.CONFIRMATION, "Staff Registered!").show();
//            refreshTable();
//        } else {
//            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
//        }
//
//    }

        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (EmployeeModel.delete(txtID1.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Data Deleted!").show();
            refreshTable();
            clearTextField();
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "No data").show();

            //TODO OTHER METHOD!
//        String id = txtID.getText();
//
//
//        String SQL = "Delete  From staff where Sid=?";
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
//
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String Sid = txtID1.getText();

        try {
            Employee employee = EmployeeModel.search(Sid);
            if (employee != null) {
                fillData(employee);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);

            //TODO OTHER METHOD!
//        String id = txtID.getText();
//        String firstName = txtFirstName.getText();
//        String lastName = txtLastName.getText();
//        String age = txtAge.getText();
//        String nic = txtNic.getText();
//        String Mobile = txtMobile.getText();
//        String address = txtAdd.getText();
//        String userName = txtUserName.getText();
//        String password = txtPassword.getText();
//
//        String SQL = "Select * From staff where Sid=?";
//
//
//        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(SQL);
//
//        statement.setObject(1, id);
//
//        ResultSet executeQuery = statement.executeQuery();
//
//        if (executeQuery.next()) {
//            txtFirstName.setText(executeQuery.getString("firstname"));
//            txtLastName.setText(executeQuery.getString("lastname"));
//            txtAge.setText(executeQuery.getString("age"));
//            txtNic.setText(executeQuery.getString("nic"));
//            txtMobile.setText(executeQuery.getString("phone_no"));
//            txtAdd.setText(executeQuery.getString("address"));
//            txtUserName.setText(executeQuery.getString("userName"));
//            txtPassword.setText(executeQuery.getString("password"));
//        }
//
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        patternPerform();
        try {
            if (EmployeeModel.staffUpdate(
                    new Employee(txtID.getText(),
                            txtFirstName.getText(),
                            txtLastName.getText(),
                            txtAge.getText(),
                            txtNic.getText(),
                            txtMobile.getText(),
                            txtAdd.getText(),
                            txtUserName.getText(),
                            txtPassword.getText())
            )) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
                clearTextField();
                refreshTable();

            } else {
                new Alert(Alert.AlertType.ERROR, "Something happened").show();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();

            //TODO OTHER METHOD!
//        String id = txtID.getText();
//        String firstName = txtFirstName.getText();
//        String lastName = txtLastName.getText();
//        String age = txtAge.getText();
//        String nic = txtNic.getText();
//        String Mobile = txtMobile.getText();
//        String address = txtAdd.getText();
//        String userName = txtUserName.getText();
//        String password = txtPassword.getText();
//
//        String SQL = "Update staff set firstname=?, lastname =?, age=?, nic=?, phone_no=?, address=?, username=?, password=? where Sid=?";
//        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
//
//        stm.setString(1, firstName);
//        stm.setString(2, lastName);
//        stm.setString(3, age);
//        stm.setString(4, nic);
//        stm.setString(5, Mobile);
//        stm.setString(6, address);
//        stm.setString(7, userName);
//        stm.setString(8, password);
//        stm.setString(9, id);
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
//
        }

    }

    public void initialize() {
        setPatterns();
        loadData();
        Platform.runLater(() -> txtID.requestFocus());
    }

    public void loadData() {

        String SQL = "Select * From staff";
        try {
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(SQL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employeeObservableList.add(new Employee(resultSet.getString("Sid"), resultSet.getString("firstname"),
                        resultSet.getString("lastname"), resultSet.getString("age"), resultSet.getString("nic"),
                        resultSet.getString("phone_no"), resultSet.getString("address"), resultSet.getString("userName"),
                        resultSet.getString("password")));
                tbl.setItems(employeeObservableList);

                tbl.setItems(employeeObservableList);
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }

        tblId.setCellValueFactory(new PropertyValueFactory<>("Sid"));
        tblfirstName.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        tblLastName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        tblAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        tblNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        tblMobile.setCellValueFactory(new PropertyValueFactory<>("phone_no"));
        tblAdd.setCellValueFactory(new PropertyValueFactory<>("address"));
        tblUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        tblPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
    }

    public void refreshTable() {
        employeeObservableList.clear();
        String SQL = "Select * From staff";
        try {
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(SQL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employeeObservableList.add(new Employee(resultSet.getString("Sid"), resultSet.getString("firstname"),
                        resultSet.getString("lastname"), resultSet.getString("age"), resultSet.getString("nic"),
                        resultSet.getString("phone_no"), resultSet.getString("address"), resultSet.getString("userName"),
                        resultSet.getString("password")));
                tbl.setItems(employeeObservableList);

                tbl.setItems(employeeObservableList);
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }

        tblId.setCellValueFactory(new PropertyValueFactory<>("Sid"));
        tblfirstName.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        tblLastName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        tblAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        tblNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        tblMobile.setCellValueFactory(new PropertyValueFactory<>("phone_no"));
        tblAdd.setCellValueFactory(new PropertyValueFactory<>("address"));
        tblUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        tblPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
    }

    public void fillData(Employee employee) {
        txtID.setText(employee.getSid());
        txtFirstName.setText(employee.getFirstname());
        txtLastName.setText(employee.getLastname());
        txtAge.setText(employee.getAge());
        txtNic.setText(employee.getNic());
        txtMobile.setText(employee.getPhone_no());
        txtAdd.setText(employee.getAddress());
        txtUserName.setText(employee.getUserName());
        txtPassword.setText(employee.getPassword());

    }

    public void clearTextField() {
        txtID.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtAge.setText("");
        txtNic.setText("");
        txtMobile.setText("");
        txtAdd.setText("");
        txtUserName.setText("");
        txtPassword.setText("");
        txtID1.setText("");
    }

    public void firstNameOnAction(ActionEvent actionEvent) {
        txtLastName.requestFocus();
    }

    public void lastNameOnAction(ActionEvent actionEvent) {
        txtAge.requestFocus();
    }

    public void ageOnAction(ActionEvent actionEvent) {
        txtNic.requestFocus();
    }

    public void contactOnAction(ActionEvent actionEvent) {
        txtAdd.requestFocus();
    }

    public void addressOnAction(ActionEvent actionEvent) {
        txtUserName.requestFocus();
    }

    public void IdOnAction(ActionEvent actionEvent) {
        txtFirstName.requestFocus();
    }

    public void userNameOnAction(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }

    public void passwordOnAction(ActionEvent actionEvent) {
    }

    public void nicOnAction(ActionEvent actionEvent) {
        txtMobile.requestFocus();
    }

    public void searchIDonAction(ActionEvent actionEvent) {
        searchIcon.fire();
    }

    @FXML
    void txtPasswordKeyTypedOnAction(KeyEvent event) {
        lblUserNameWarning7.setText("");
        txtPassword.setFocusColor(Paint.valueOf("Green"));

        Pattern pwPattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
        passwordMatcher = pwPattern.matcher(txtPassword.getText());

        if (!passwordMatcher.matches()) {

            txtPassword.requestFocus();
            txtPassword.setFocusColor(Paint.valueOf("Red"));
            lblUserNameWarning8.setText("invalid");
        }
    }

    @FXML
    void txtUserAddressKeyTypedOnAction(KeyEvent event) {
        lblUserNameWarning6.setText("");
        txtAdd.setFocusColor(Paint.valueOf("Green"));

        Pattern addressPattern = Pattern.compile("^[a-z.\\sA-Z.\\s]{4,}$");
        addressMatcher = addressPattern.matcher(txtAdd.getText());

        if (!addressMatcher.matches()) {

            txtAdd.requestFocus();
            txtAdd.setFocusColor(Paint.valueOf("Red"));
            lblUserNameWarning6.setText("invalid");
        }
    }

    @FXML
    void txtUserAgeKeyTypedOnAction(KeyEvent event) {
        lblUserNameWarning3.setText("");
        txtAge.setFocusColor(Paint.valueOf("Green"));

        Pattern agePattern = Pattern.compile("^(1[89]|[1-9]\\d)$");
        ageMatcher = agePattern.matcher(txtAge.getText());

        if (!ageMatcher.matches()) {

            txtAge.requestFocus();
            txtAge.setFocusColor(Paint.valueOf("Red"));
            lblUserNameWarning3.setText("invalid");
        }
    }

    @FXML
    void txtUserFirstNameKeyTypedOnAction(KeyEvent event) {
        lblUserNameWarning1.setText("");
        txtFirstName.setFocusColor(Paint.valueOf("Green"));

        Pattern firstNamePattern = Pattern.compile("^[a-zA-Z]{2,10}$");
        firstNameMatcher = firstNamePattern.matcher(txtFirstName.getText());

        if (!firstNameMatcher.matches()) {

            txtFirstName.requestFocus();
            txtFirstName.setFocusColor(Paint.valueOf("Red"));
            lblUserNameWarning1.setText("invalid");
        }
    }

    @FXML
    void txtUserIdKeyTypedOnAction(KeyEvent event) {
        lblUserNameWarning.setText("");
        txtID.setFocusColor(Paint.valueOf("Green"));

        Pattern idPattern = Pattern.compile("^(S)[0-9][0-9][0-9]$");
        idMatcher = idPattern.matcher(txtID.getText());

        if (!idMatcher.matches()) {

            txtID.requestFocus();
            txtID.setFocusColor(Paint.valueOf("Red"));
            lblUserNameWarning.setText("invalid");
        }
    }

    @FXML
    void txtUserLastNameKeyTypedOnAction(KeyEvent event) {
        lblUserNameWarning2.setText("");
        txtLastName.setFocusColor(Paint.valueOf("Green"));

        Pattern lastNamePattern = Pattern.compile("^[a-zA-Z]{2,10}$");
        lastNameMatcher = lastNamePattern.matcher(txtLastName.getText());

        if (!lastNameMatcher.matches()) {

            txtLastName.requestFocus();
            txtLastName.setFocusColor(Paint.valueOf("Red"));
            lblUserNameWarning2.setText("invalid");
        }
    }

    @FXML
    void txtUserMobileKeyTypedOnAction(KeyEvent event) {
        lblUserNameWarning5.setText("");
        txtMobile.setFocusColor(Paint.valueOf("Green"));

        Pattern mobilePattern = Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9,10}$");
        mobileMatcher = mobilePattern.matcher(txtMobile.getText());

        if (!mobileMatcher.matches()) {

            txtMobile.requestFocus();
            txtMobile.setFocusColor(Paint.valueOf("Red"));
            lblUserNameWarning5.setText("invalid");
        }
    }

    @FXML
    void txtUserNicKeyTypedOnAction(KeyEvent event) {
        lblUserNameWarning4.setText("");
        txtNic.setFocusColor(Paint.valueOf("Green"));

        Pattern nicPattern = Pattern.compile("^[0-9]{12}$");
        nicMatcher = nicPattern.matcher(txtNic.getText());

        if (!nicMatcher.matches()) {

            txtNic.requestFocus();
            txtNic.setFocusColor(Paint.valueOf("Red"));
            lblUserNameWarning4.setText("invalid");
        }
    }

    @FXML
    void txtUserUserNameKeyTypedOnAction(KeyEvent event) {
        lblUserNameWarning7.setText("");
        txtUserName.setFocusColor(Paint.valueOf("Green"));

        Pattern userNamePattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        userNameMatcher = userNamePattern.matcher(txtUserName.getText());

        if (!userNameMatcher.matches()) {

            txtUserName.requestFocus();
            txtUserName.setFocusColor(Paint.valueOf("Red"));
            lblUserNameWarning7.setText("invalid");
        }
    }

    public void patternPerform() {
        if (idMatcher.matches()) {
            if (firstNameMatcher.matches()) {
                if (lastNameMatcher.matches()) {
                    if (ageMatcher.matches()) {
                        if (nicMatcher.matches()) {
                            if (mobileMatcher.matches()) {
                                if (addressMatcher.matches()) {
                                    if (userNameMatcher.matches()) {
                                        if (passwordMatcher.matches()) {
                                            System.out.println("register action now perform");
                                        } else {
                                            txtPassword.requestFocus();
                                            txtPassword.setFocusColor(Paint.valueOf("Red"));
                                            lblUserNameWarning8.setText("invalid");
                                        }
                                    } else {
                                        txtUserName.requestFocus();
                                        txtUserName.setFocusColor(Paint.valueOf("Red"));
                                        lblUserNameWarning7.setText("invalid");
                                    }
                                } else {
                                    txtAdd.requestFocus();
                                    txtAdd.setFocusColor(Paint.valueOf("Red"));
                                    lblUserNameWarning6.setText("invalid");
                                }
                            } else {
                                txtMobile.requestFocus();
                                txtMobile.setFocusColor(Paint.valueOf("Red"));
                                lblUserNameWarning5.setText("invalid");
                            }
                        } else {
                            txtNic.requestFocus();
                            txtNic.setFocusColor(Paint.valueOf("Red"));
                            lblUserNameWarning4.setText("invalid");
                        }
                    } else {
                        txtAge.requestFocus();
                        txtAge.setFocusColor(Paint.valueOf("Red"));
                        lblUserNameWarning3.setText("invalid");
                    }
                } else {
                    txtLastName.requestFocus();
                    txtLastName.setFocusColor(Paint.valueOf("Red"));
                    lblUserNameWarning2.setText("invalid");
                }
            } else {
                txtFirstName.requestFocus();
                txtFirstName.setFocusColor(Paint.valueOf("Red"));
                lblUserNameWarning1.setText("invalid");
            }
        } else {
            txtID.requestFocus();
            txtID.setFocusColor(Paint.valueOf("Red"));
            lblUserNameWarning.setText("invalid");

        }
    }
}







