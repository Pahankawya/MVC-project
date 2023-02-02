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
import lk.ijse.librarydb.model.BookModel;
import lk.ijse.librarydb.to.Books;


import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CategoryUpdateController {
    ObservableList<Books> Books = FXCollections.observableArrayList();
    @FXML
    private AnchorPane pane;

    @FXML
    private JFXTextField txtTitle;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtType;

    @FXML
    private JFXTextField txtID;

    @FXML
    private TableView<Books> tbl;

    @FXML
    private JFXButton btnsearch;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXButton btnAddonactionId;

    @FXML
    private TableColumn<Books, String> tblId;

    @FXML
    private TableColumn<Books, String> tblTitle;

    @FXML
    private TableColumn<Books, String> tblPrice;

    @FXML
    private TableColumn<Books, String> tblType;

    @FXML
    private TableColumn<Books, String> tblQty;

    @FXML
    private Label lblUserNameWarning;

    @FXML
    private Label lblUserNameWarning1;

    @FXML
    private Label lblUserNameWarning2;

    @FXML
    private Label lblUserNameWarning3;

    @FXML
    private Label lblWarning4;

    @FXML
    private JFXButton btnCancel;


    @FXML
    private TextField txtID1;
    private Matcher idMatcher;
    private Matcher titleMatcher;
    private Matcher priceMatcher;
    private Matcher typeMatcher;
    private Matcher qtyMatcher;

    public void setPatterns() {
        Pattern idPattern = Pattern.compile("^(B)[0-9][0-9][0-9]$");
        idMatcher = idPattern.matcher(txtID.getText());

        Pattern titlePattern = Pattern.compile("^[a-z.\\sA-Z.\\s]{4,}$");
        titleMatcher = titlePattern.matcher(txtTitle.getText());

        Pattern pricePattern = Pattern.compile("^[0-9]{0,5}$");
        priceMatcher = pricePattern.matcher(txtPrice.getText());

        Pattern typePattern = Pattern.compile("^(B)[0-9][0-9][0-9]$");
        typeMatcher = typePattern.matcher(txtType.getText());

        Pattern qtyPattern = Pattern.compile("^(1[89]|[1-9]\\d)$");
        qtyMatcher = qtyPattern.matcher(txtQty.getText());
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String id = txtID.getText();
        String title = txtTitle.getText();
        double price = Double.parseDouble(txtPrice.getText());
        String type = txtType.getText();
        int qty = Integer.parseInt(txtQty.getText());
        patternPerfom();
        Books books = new Books(id, title, price, type, qty);

        try {
            boolean isAdded = BookModel.add(books);
            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Book Added!").show();
                loadData();
                refreshTable();
                clearTextField();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something Happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //TODO other method!
//        String SQL = "INSERT INTO books VALUES (?,?,?,?)";
//        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
//
//        stm.setString(1, id);
//        stm.setString(2, title);
//        stm.setString(3, price);
//        stm.setString(4, type);
//
//
//        int executeUpdate = stm.executeUpdate();
//        if (executeUpdate > 0) {
//            new Alert(Alert.AlertType.CONFIRMATION, "Customer Added!").show();
//            clearTextField();
//            refreshTable();
//        } else {
//            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
//            clearTextField();
//        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (BookModel.delete(txtID1.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Data Deleted!").show();
            refreshTable();
            clearTextField();
        } else {
            new Alert(Alert.AlertType.WARNING, "No data").show();

        }
        //TODO other method here!


//        String id = txtID.getText();
//
//
//        String SQL = "Delete  From books where book_id=?";
//
//        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
//
//        stm.setObject(1, id);
//
//        int executeUpdate = stm.executeUpdate();
//        if (executeUpdate > 0) {
//            new Alert(Alert.AlertType.CONFIRMATION, "Data Deleted!").show();
//            clearTextField();
//            refreshTable();
//        } else {
//            new Alert(Alert.AlertType.CONFIRMATION, "No data").show();
//            clearTextField();
//        }

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String Book_id = txtID1.getText();

        try {
            Books books = BookModel.search(Book_id);
            if (books != null) {
                fillData(books);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //TODO another Method!

//        String SQL = "Select * From books where book_id=?";
//
//
//        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(SQL);
//
//        statement.setObject(1, id);
//
//        ResultSet executeQuery = statement.executeQuery();
//
//        if (executeQuery.next()) {
//            txtTitle.setText(executeQuery.getString("Book_title"));
//            txtPrice.setText(executeQuery.getString("Book_price"));
//            txtType.setText(executeQuery.getString("type"));
//

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        patternPerfom();
        try {
            if (BookModel.bookUpdate(
                    new Books(txtID.getText(),
                            txtTitle.getText(),
                            Double.parseDouble(txtPrice.getText()),
                            txtType.getText(),
                            Integer.parseInt(txtQty.getText()))
            )) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
                clearTextField();
                refreshTable();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something Happened").show();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();

            //TODO Other method!

//
//        String id = txtID.getText();
//        String title = txtTitle.getText();
//        String price = txtPrice.getText();
//        String type = txtType.getText();
//
//
//        String SQL = "Update books set Book_title=?,Book_price =?, type=? where Book_id=?";
//        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
//
//        stm.setString(1, title);
//        stm.setString(2, price);
//        stm.setString(3, type);
//        stm.setString(4, id);
//
//
//        int executeUpdate = stm.executeUpdate();
//
//        if (executeUpdate > 0) {
//            new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
//            refreshTable();
//            clearTextField();
//        } else {
//            new Alert(Alert.AlertType.WARNING, "Search first").show();
//            clearTextField();
        }
    }

    public void initialize() {
        setPatterns();
        loadData();
        Platform.runLater(() -> txtID.requestFocus());

    }

    public void loadData() {

        String SQL = "Select * From books";
        try {
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(SQL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Books.add(new Books(resultSet.getString("Book_id"), resultSet.getString("Book_title"),
                        resultSet.getDouble("Book_price"), resultSet.getString("type"), resultSet.getInt("QTY")));
                tbl.setItems(Books);

                tbl.setItems(Books);
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }

        tblId.setCellValueFactory(new PropertyValueFactory<>("Book_id"));
        tblTitle.setCellValueFactory(new PropertyValueFactory<>("Book_title"));
        tblPrice.setCellValueFactory(new PropertyValueFactory<>("Book_price"));
        tblType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tblQty.setCellValueFactory(new PropertyValueFactory<>("QTY"));

    }

    public void refreshTable() {
        Books.clear();
        String SQL = "Select * From books";
        try {
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(SQL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Books.add(new Books(resultSet.getString("Book_id"), resultSet.getString("Book_title"),
                        resultSet.getDouble("Book_price"), resultSet.getString("type"), resultSet.getInt("QTY")));
                tbl.setItems(Books);

                tbl.setItems(Books);
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }

        tblId.setCellValueFactory(new PropertyValueFactory<>("Book_id"));
        tblTitle.setCellValueFactory(new PropertyValueFactory<>("Book_title"));
        tblPrice.setCellValueFactory(new PropertyValueFactory<>("Book_price"));
        tblType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tblQty.setCellValueFactory(new PropertyValueFactory<>("QTY"));

    }

    public void clearTextField() {
        txtID.setText("");
        txtTitle.setText("");
        txtPrice.setText("");
        txtType.setText("");
        txtQty.setText("");
        txtID1.setText("");

    }

    private void fillData(Books books) {
        txtID.setText(books.getBook_id());
        txtTitle.setText(books.getBook_title());
        txtPrice.setText(String.valueOf(books.getBook_price()));
        txtType.setText(books.getBook_title());
        txtQty.setText(String.valueOf(books.getQTY()));

    }

    public void searchOnAction(ActionEvent actionEvent) {
        btnsearch.fire();
    }

    public void btnTitleOnAction(ActionEvent actionEvent) {
        txtPrice.requestFocus();
    }

    public void btnPriceOnAction(ActionEvent actionEvent) {
        txtType.requestFocus();
    }

    public void btnTypeOnAction(ActionEvent actionEvent) {
        txtQty.requestFocus();
    }

    public void btnIdOnAction(ActionEvent actionEvent) {
        txtTitle.requestFocus();

    }

    @FXML
    void btnQtyOnAction(ActionEvent event) {
        btnAddonactionId.fire();

    }

    @FXML
    void txtIdOnAction(KeyEvent event) {
        lblUserNameWarning.setText("");
        txtID.setFocusColor(Paint.valueOf("Green"));

        Pattern idPattern = Pattern.compile("^(B)[0-9][0-9][0-9]$");
        idMatcher = idPattern.matcher(txtID.getText());

        if (!idMatcher.matches()) {
            txtID.requestFocus();
            txtID.setFocusColor(Paint.valueOf("Red"));
            lblUserNameWarning.setText("invalid");

        }
    }

    @FXML
    void txtPriceOnAction(KeyEvent event) {
        lblUserNameWarning2.setText("");
        txtPrice.setFocusColor(Paint.valueOf("Green"));

        Pattern idPattern = Pattern.compile("^[0-9]{0,5}$");
        priceMatcher = idPattern.matcher(txtPrice.getText());

        if (!priceMatcher.matches()) {
            txtPrice.requestFocus();
            txtPrice.setFocusColor(Paint.valueOf("Red"));
            lblUserNameWarning2.setText("invalid");

        }
    }

    @FXML
    void txtTypeOnAction(KeyEvent event) {
        lblUserNameWarning3.setText("");
        txtType.setFocusColor(Paint.valueOf("Green"));

        Pattern typePattern = Pattern.compile("^[a-zA-Z]{2,10}$");
        typeMatcher = typePattern.matcher(txtType.getText());

        if (!typeMatcher.matches()) {
            txtType.requestFocus();
            txtType.setFocusColor(Paint.valueOf("Red"));
            lblUserNameWarning3.setText("invalid");
        }
    }

    @FXML
    void txttitleOnAction(KeyEvent event) {
        lblUserNameWarning1.setText("");
        txtTitle.setFocusColor(Paint.valueOf("Green"));

        Pattern idPattern = Pattern.compile("^[a-z.\\sA-Z.\\s]{4,}$");
        titleMatcher = idPattern.matcher(txtTitle.getText());

        if (!titleMatcher.matches()) {
            txtTitle.requestFocus();
            txtTitle.setFocusColor(Paint.valueOf("Red"));
            lblUserNameWarning1.setText("invalid");

        }
    }

    @FXML
    void txtQtyOnReleased(KeyEvent event) {
        lblWarning4.setText("");
        txtQty.setFocusColor(Paint.valueOf("Green"));

        Pattern qtyPattern = Pattern.compile("^(1[89]|[1-9]\\d)$");
        qtyMatcher = qtyPattern.matcher(txtQty.getText());

        if (!qtyMatcher.matches()) {
            txtQty.requestFocus();
            txtQty.setFocusColor(Paint.valueOf("Red"));
            lblWarning4.setText("invalid");

        }
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) throws IOException {
        pathToSameUI("admin");
    }

    public void patternPerfom() {
        if (idMatcher.matches()) {
            if (titleMatcher.matches()) {
                if (priceMatcher.matches()) {
                    if (typeMatcher.matches()) {
                        if (qtyMatcher.matches()) {
                            System.out.println("register action now perform");
                        } else {
                            txtQty.requestFocus();
                            txtQty.setFocusColor(Paint.valueOf("Red"));
                            lblWarning4.setText("invalid");
                        }
                    } else {
                        txtType.requestFocus();
                        txtType.setFocusColor(Paint.valueOf("Red"));
                        lblUserNameWarning3.setText("invalid");
                    }
                } else {
                    txtPrice.requestFocus();
                    txtPrice.setFocusColor(Paint.valueOf("Red"));
                    lblUserNameWarning2.setText("invalid");
                }
            } else {
                txtTitle.requestFocus();
                txtTitle.setFocusColor(Paint.valueOf("Red"));
                lblUserNameWarning1.setText("invalid");
            }
        } else {
            txtID.requestFocus();
            txtID.setFocusColor(Paint.valueOf("Red"));
            lblUserNameWarning.setText("invalid");
        }
    }

    public void pathToSameUI(String pathToUI) throws IOException {
//        pane.getChildren().clear();
//        pane.getChildren().clear();

        Stage window = (Stage)  pane.getScene().getWindow();
//        window.setScene(new Scene(FXMLLoader.load(Navigation.class.getResource("/lk/ijse/librarydb/view/" +pathToUI+".fxml"))));
        window.close();
        }
}






