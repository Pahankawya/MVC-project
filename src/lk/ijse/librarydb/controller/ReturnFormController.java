package lk.ijse.librarydb.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
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
import lk.ijse.librarydb.model.ReturnOrderModel;
import lk.ijse.librarydb.model.ReturnModel;
import lk.ijse.librarydb.to.ReturnCartDetail;
import lk.ijse.librarydb.to.ReturnOrder;
import lk.ijse.librarydb.view.tm.ReturnOrderTM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReturnFormController {
    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<ReturnOrderTM> tbl;

    @FXML
    private TableColumn tblId;

    @FXML
    private TableColumn tblBookId;

    @FXML
    private TableColumn tblBorrowDate;

    @FXML
    private TableColumn tblReturnDate;

    @FXML
    private TableColumn tblQuantity;
    @FXML
    private TableColumn tblDeleteButton;

    @FXML
    private JFXComboBox<String> cmbCustomerId;

    @FXML
    private JFXComboBox<String> cmbBookId;

    @FXML
    private JFXDatePicker cmbborrowDte;

    @FXML
    private Label lblUserNameWarning;

    @FXML
    private JFXTextField cmbQty;

    @FXML
    private JFXDatePicker cmbReturnDate;
    private Matcher qtyMatcher;
    private ObservableList<ReturnOrderTM> ctList = FXCollections.observableArrayList();

    public void setPattern() {
        Pattern qtyPattern = Pattern.compile("^(1[89]|[1-9]\\d)$");
        qtyMatcher = qtyPattern.matcher(cmbQty.getText());
    }

    public void initialize() {
        loadCusId();
        loadEmployeeId();
//        loadTable();
        setPattern();
        setCellValueFactory();
    }

    public void loadCusId() {
        String sql = "Select cusid from customer;";
        try {
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            ObservableList<String> data = FXCollections.observableArrayList();
            while (resultSet.next()) {
                data.add(resultSet.getString(1));
                cmbCustomerId.setItems(data);
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    public void loadEmployeeId() {
        String sql = "Select Book_id from books";
        try {
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            ObservableList<String> data = FXCollections.observableArrayList();
            while (resultSet.next()) {
                data.add(resultSet.getString(1));
                cmbBookId.setItems(data);
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

        String CusId = cmbCustomerId.getValue();
        String Book_id = cmbBookId.getValue();
        LocalDate borrow_date = LocalDate.parse(String.valueOf(cmbborrowDte.getValue()));
        LocalDate return_date = LocalDate.parse(String.valueOf(cmbReturnDate.getValue()));
        int Qty = Integer.parseInt(cmbQty.getText());
        Button btnDelete = new Button("Delete");

        cmbQty.setText("");

        if (!ctList.isEmpty()) {
            L1:
            /* check same item has been in table. If so, update that row instead of adding new row to the table */
            for (int i = 0; i < tbl.getItems().size(); i++) {
                if (tblBookId.getCellData(i).equals(CusId)) {
                    Qty += (int) tblQuantity.getCellData(i);

                    ctList.get(i).setQty(Qty);
                    tbl.refresh();
                    return;
                }
            }
        }

        /* set delete button to some action before it put on obList */
        btnDelete.setOnAction((e) -> {
            ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?", ok, no);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.orElse(no) == ok) {
                ReturnOrderTM tm = tbl.getSelectionModel().getSelectedItem();
                /*
                netTot = Double.parseDouble(txtNetTot.getText());
                netTot = netTot - tm.getTotalPrice();
                */

                tbl.getItems().removeAll(tbl.getSelectionModel().getSelectedItem());
//                    ctList.clear();
                refreshTable();
            }
        });
        ctList.add(new ReturnOrderTM(CusId, Book_id, borrow_date, return_date, Qty, btnDelete));
        tbl.setItems(ctList);

    }

//        String CusId = cmbCustomerId.getValue();
//        String Book_id = cmbBookId.getValue();
//        String borrow_date = cmbborrowDte.getValue().toString();
//        String return_date = cmbReturnDate.getValue().toString();
//        String Qty = cmbQty.getText();
//
//
//        Return returns = new Return(CusId, Book_id, borrow_date, return_date, Qty);
//
//        try {
//            boolean isAdded = returnModel.add(returns);
//
//            if (isAdded) {
//                new Alert(Alert.AlertType.CONFIRMATION, "marked!").show();
//                refreshTable();
//                clearTextField();
//            } else {
//                new Alert(Alert.AlertType.WARNING, "Something Happened!").show();
//            }
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//
//        }
//    }
    public void btnReturnOnAction(ActionEvent actionEvent) {
        String cusid = cmbCustomerId.getValue();
        String  Book_id = cmbBookId.getValue();

        ArrayList<ReturnCartDetail> returnCartDetails = new ArrayList<>();

        /* load all cart items' to borrowDetails arrayList */
        for (int i = 0; i < tbl.getItems().size(); i++) {
            /* get each row details to (PlaceOrderTm)tm in each time and add them to the borrowDetails */
            ReturnOrderTM tm = ctList.get(i);
            returnCartDetails.add(new ReturnCartDetail(tm.getCusid(), tm.getBook_id(),  tm.getBorrow_date(), tm.getQty()));
        }

        ReturnOrder returnOrder = new ReturnOrder(cusid, Book_id, returnCartDetails);
        try {
            boolean isPlaced = ReturnOrderModel.returnOrder(returnOrder);
            if (isPlaced) {
                /* to clear table */
                ctList.clear();
//                loadNextOrderId();
                new Alert(Alert.AlertType.CONFIRMATION, "Order Placed!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Order Not Placed!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);

        }
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (ReturnModel.delete(cmbCustomerId.getValue())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Data Deleted!").show();
            refreshTable();
            clearTextField();
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "No data").show();
        }
    }

    public void clearTextField() {
        cmbCustomerId.setValue("");
        cmbBookId.getEditor().clear();
        cmbborrowDte.getEditor().clear();
        cmbReturnDate.getEditor().clear();
        cmbQty.setText("");
    }
//    public void loadTable(){
//        String SQL = "Select * From returnbook";
//        try {
//            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(SQL);
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                returns.add(new Return(resultSet.getString("cusid"), resultSet.getString("book_id"),
//                        resultSet.getString("borrow_date"), resultSet.getString("return_date"), resultSet.getString("Qty")));
//                tbl.setItems(returns);
//
//                tbl.setItems(returns);
//            }
//
//        } catch (ClassNotFoundException | SQLException e) {
//            System.out.println(e);
//        }
//
//        tblId.setCellValueFactory(new PropertyValueFactory<>("cusid"));
//        tblBookId.setCellValueFactory(new PropertyValueFactory<>("book_id"));
//        tblBorrowDate.setCellValueFactory(new PropertyValueFactory<>("borrow_date"));
//        tblReturnDate.setCellValueFactory(new PropertyValueFactory<>("return_date"));
//        tblQuantity.setCellValueFactory(new PropertyValueFactory<>("Qty"));

    public void refreshTable() {
        ctList.clear();
        String SQL = "Select * From returnbook";
        try {
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(SQL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ctList.add(new ReturnOrderTM(resultSet.getString("cusid"), resultSet.getString("book_id"),
                        resultSet.getDate("borrow_date"), resultSet.getString("return_date"), resultSet.getInt("Qty")));
                tbl.setItems(ctList);

                tbl.setItems(ctList);
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }

        tblId.setCellValueFactory(new PropertyValueFactory("cusid"));
        tblBookId.setCellValueFactory(new PropertyValueFactory("Book_id"));
        tblBorrowDate.setCellValueFactory(new PropertyValueFactory("borrow_date"));
        tblReturnDate.setCellValueFactory(new PropertyValueFactory("return_date"));
        tblQuantity.setCellValueFactory(new PropertyValueFactory("Qty"));
        tblDeleteButton.setCellValueFactory(new PropertyValueFactory("delete"));
    }

    private void setCellValueFactory() {
        tblId.setCellValueFactory(new PropertyValueFactory("cusid"));
        tblBookId.setCellValueFactory(new PropertyValueFactory("Book_id"));
        tblBorrowDate.setCellValueFactory(new PropertyValueFactory("borrow_date"));
        tblReturnDate.setCellValueFactory(new PropertyValueFactory("return_date"));
        tblQuantity.setCellValueFactory(new PropertyValueFactory("Qty"));
        tblDeleteButton.setCellValueFactory(new PropertyValueFactory("delete"));
    }

    @FXML
    void patternPerform(KeyEvent event) {
        lblUserNameWarning.setText("");
        cmbQty.setFocusColor(Paint.valueOf("Green"));

        Pattern qtyPattern = Pattern.compile("^(1[89]|[1-9]\\d)$");
        qtyMatcher = qtyPattern.matcher(cmbQty.getText());

        if (!qtyMatcher.matches()) {
            cmbQty.requestFocus();
            cmbQty.setFocusColor(Paint.valueOf("Red"));
            lblUserNameWarning.setText("invalid");

        }
    }
}
