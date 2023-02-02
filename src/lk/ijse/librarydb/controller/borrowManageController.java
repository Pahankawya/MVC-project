package lk.ijse.librarydb.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.librarydb.Db.DBConnection;
import lk.ijse.librarydb.model.BookModel;
import lk.ijse.librarydb.model.BorrowModel;
import lk.ijse.librarydb.model.CustomerModel;
import lk.ijse.librarydb.model.PlaceOrderModel;
import lk.ijse.librarydb.to.Books;
import lk.ijse.librarydb.to.CartDetail;
import lk.ijse.librarydb.to.Customer;
import lk.ijse.librarydb.to.PlaceOrder;
import lk.ijse.librarydb.util.Navigation;
import lk.ijse.librarydb.view.tm.PlaceOrderTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class borrowManageController implements Initializable {


    @FXML
    private AnchorPane pane;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblTime;


    @FXML
    private Label TotalLbl;

    @FXML
    private JFXComboBox cmbCustomerId;

    @FXML
    private Label lblCustomerName;

    @FXML
    private JFXComboBox cmbItemCode;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblBookPrice;

    @FXML
    private TextField txtQty;

    @FXML
    private TableView<PlaceOrderTM> tblOrderCart;

    @FXML
    private TableColumn colItemCode;

    @FXML
    private TableColumn colDescription;

    @FXML
    private TableColumn colQty;

    @FXML
    private TableColumn colUnitPrice;

    @FXML
    private TableColumn colTotal;

    @FXML
    private TableColumn colAction;

    @FXML
    private Label lblQtyOnHand;
    private ObservableList<PlaceOrderTM> obList = FXCollections.observableArrayList();

    //    ObservableList<CartDetail> tmList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadbookids();
        loadCuIds();
        loadOrderDate();
        loadNextOrderId();
        setCellValueFactory();
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String code = String.valueOf(cmbItemCode.getValue());
        int qty = Integer.parseInt(txtQty.getText());
        String desc = lblDescription.getText();
        double unitPrice = Double.parseDouble(lblBookPrice.getText());
        double total = unitPrice * qty;
        Button btnDelete = new Button("Delete");

        txtQty.setText("");

        if (!obList.isEmpty()) {
            L1:
            /* check same item has been in table. If so, update that row instead of adding new row to the table */
            for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
                if (colItemCode.getCellData(i).equals(code)) {
                    qty += (int) colQty.getCellData(i);
                    total = unitPrice * qty;

                    obList.get(i).setQTY(qty);
                    obList.get(i).setTotal(total);
                    tblOrderCart.refresh();
                    calculateTotal();
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
                PlaceOrderTM tm = tblOrderCart.getSelectionModel().getSelectedItem();
                /*
                netTot = Double.parseDouble(txtNetTot.getText());
                netTot = netTot - tm.getTotalPrice();
                */

                tblOrderCart.getItems().removeAll(tblOrderCart.getSelectionModel().getSelectedItem());

                tblOrderCart.refresh();
                refreshTable();
//                obList.clear();
            }
        });
        obList.add(new PlaceOrderTM(code, desc, qty, unitPrice, total, btnDelete));
        tblOrderCart.setItems(obList);
    }


    @FXML
    void btnNewCustomerOnAction(ActionEvent event) throws IOException {
        Navigation.swatchNavigation2("/lk/ijse/librarydb/view/addNewCustomerForm.fxml", event);
    }


    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        String brwId = lblOrderId.getText();
        String cusid = String.valueOf(cmbCustomerId.getValue());

        ArrayList<CartDetail> cartDetails = new ArrayList<>();

        /* load all cart items' to borrowDetails arrayList */
        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            /* get each row details to (PlaceOrderTm)tm in each time and add them to the borrowDetails */
            PlaceOrderTM tm = obList.get(i);
            cartDetails.add(new CartDetail(brwId, tm.getBook_id(), tm.getBook_title(), tm.getQTY(), tm.getBook_price()));
        }

        PlaceOrder placeOrder = new PlaceOrder(cusid, brwId, cartDetails);
        try {
            boolean isPlaced = PlaceOrderModel.placeOrder(placeOrder);
            if (isPlaced) {
                /* to clear table */
                obList.clear();
                loadNextOrderId();
                new Alert(Alert.AlertType.CONFIRMATION, "Order Placed!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Order Not Placed!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);

        }
    }

    // TODO TRIED METHOD ALSO WORKS!


//    @FXML
//    void btnPlaceeOrderOnAction(ActionEvent event){
//        ArrayList<BorrowDetail> borrowDetails = new ArrayList();
//        for (CartDetail tm : tmList
//        ) {
//            borrowDetails.add(
//                    new BorrowDetail(
//                            tm.getBrwId(),
//                            tm.getBook_id(),
//                            tm.getQTY(),
//                            tm.getBook_price()
//
//
//                    )
//            );
//            tm.getBook_id();
//
//        }
//        Borrow borrow = new Borrow(
//                lblOrderId.getText(),
//                cmbCustomerId.getValue(),
//                LocalDate.parse(lblOrderDate.getText())
//
//        );
//
//        Connection connection = null;
//        try {
//            connection = DBConnection.getInstance().getConnection();
//            connection.setAutoCommit(false);
//            boolean isOrderSaved = new OrderCrudModel().savetestOrder(borrow);
//            if (isOrderSaved) {
//                boolean isDetailsSaved = new OrderCrudModel().savetestOrderDetails(borrowDetails);
//                if (isDetailsSaved) {
//                    connection.commit();
////                    NotificationController.SuccessfulTableNotification("Order Place", "Order");
//                    System.out.println("Order placed");
//                } else {
//                    connection.rollback();
////                    NotificationController.UnSuccessfulTableNotification("Order Place", "Order");
//                    System.out.println("NO");
//                }
//            } else {
//                connection.rollback();
////                NotificationController.UnSuccessfulTableNotification("Order Place", "Order");
//                System.out.println("NOO");
//            }
//        } catch (SQLException | ClassNotFoundException ignored) {
//            //System.out.println(e);
//        } finally {
//            try {
//                connection.setAutoCommit(true);
//            } catch (SQLException ignored) {
//            }
//        }
//    }


    @FXML
    void cmbBookItemOnAction(ActionEvent event) {
        String books_id = String.valueOf(cmbItemCode.getValue());
        try {
            Books books = BookModel.search(books_id);
            fillItemFields(books);
            txtQty.requestFocus();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillItemFields(Books books) {
        lblDescription.setText(books.getBook_title());
        lblBookPrice.setText(String.valueOf(books.getBook_price()));
        lblQtyOnHand.setText(String.valueOf(books.getQTY()));
    }

    private void loadbookids() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> bookids = BookModel.loadbookIds();

            for (String book_id : bookids) {
                observableList.add(book_id);
            }
            cmbItemCode.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbCusIDOnAction(ActionEvent event) {
        String Cusid = String.valueOf(cmbCustomerId.getValue());
        try {
            Customer customer = CustomerModel.search(Cusid);
            fillCusfields(customer);
            txtQty.requestFocus();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void fillCusfields(Customer customer) {
        lblCustomerName.setText(customer.getName());

    }

    private void loadCuIds() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> customerIds = CustomerModel.loadCusIds();

            for (String Cusid : customerIds) {
                observableList.add(Cusid);
            }
            cmbCustomerId.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void txtQtyOnAction(ActionEvent actionEvent) {
        btnAddToCartOnAction(actionEvent);
    }

    private void loadOrderDate() {
        lblOrderDate.setText(String.valueOf(LocalDate.now()));
    }

    private void loadNextOrderId() {
        try {
            String brwId = BorrowModel.generateNextOrderId();
            lblOrderId.setText(brwId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colItemCode.setCellValueFactory(new PropertyValueFactory("Book_id"));
        colDescription.setCellValueFactory(new PropertyValueFactory("Book_title"));
        colQty.setCellValueFactory(new PropertyValueFactory("QTY"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory("Book_price"));
        colTotal.setCellValueFactory(new PropertyValueFactory("Total"));
        colAction.setCellValueFactory(new PropertyValueFactory("delete"));
    }

    public void refreshTable() {
        obList.clear();
        String SQL = "Select * From returnbook";
        try {
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(SQL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                obList.add(new PlaceOrderTM(resultSet.getString("Book_id"), resultSet.getString("Book_title"),
                        resultSet.getInt("QTY"), resultSet.getDouble("Book_price"), resultSet.getDouble("Total")));
                tblOrderCart.setItems(obList);

                tblOrderCart.setItems(obList);
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }

        colItemCode.setCellValueFactory(new PropertyValueFactory("Book_id"));
        colDescription.setCellValueFactory(new PropertyValueFactory("Book_title"));
        colQty.setCellValueFactory(new PropertyValueFactory("QTY"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory("Book_price"));
        colTotal.setCellValueFactory(new PropertyValueFactory("Total"));
        colAction.setCellValueFactory(new PropertyValueFactory("delete"));


    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        obList.clear();
        tblOrderCart.refresh();
        TotalLbl.setText("0.00/=");
    }

    private void calculateTotal() {
        double total = 0;
        for (PlaceOrderTM tm : obList
        ) {
            total += tm.getTotal();
        }
        TotalLbl.setText(String.valueOf(total));
    }

    @FXML
    void btnPrinOnAction(ActionEvent event) {
        InputStream resource = getClass().getResourceAsStream("/lk/ijse/librarydb/Reports/billtest5.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (SQLException | ClassNotFoundException | JRException e) {
            throw new RuntimeException(e);
        }
    }

}