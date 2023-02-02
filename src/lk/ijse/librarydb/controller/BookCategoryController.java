package lk.ijse.librarydb.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.librarydb.Db.DBConnection;
import lk.ijse.librarydb.to.Category;
import lk.ijse.librarydb.util.Navigation;
import lk.ijse.librarydb.view.tm.categoryTM;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookCategoryController implements Initializable {
    public JFXTextField search;
    ObservableList<categoryTM> category = FXCollections.observableArrayList();
    @FXML
    private AnchorPane pane;
    @FXML
    private TableView<categoryTM> tbl;
    @FXML
    private TableColumn<Category, String> tblType;

    @FXML
    private TableColumn<Category, String> tblTitle;

    @FXML
    private TableColumn<Category, String> tblAuthor;

    @FXML
    private TableColumn<Category, String> tblPubDate;

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws IOException {
        Navigation.swatchNavigation("/lk/ijse/librarydb/view/categoryUpdate.fxml", event);
    }

//    public void initialize() {
//        loadTable();
//    }
//
    public void loadTable() {
        String SQL = "SELECT *FROM books_category";
        try {
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(SQL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                category.add(new categoryTM(resultSet.getString("Book_type"),
                        resultSet.getString("Book_title"), resultSet.getString("Book_Author"), resultSet.getString("Published_date")));
                tbl.setItems(category);

                tbl.setItems(category);
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }

        tblType.setCellValueFactory(new PropertyValueFactory<>("Book_type"));
        tblTitle.setCellValueFactory(new PropertyValueFactory<>("Book_title"));
        tblAuthor.setCellValueFactory(new PropertyValueFactory<>("Book_Author"));
        tblPubDate.setCellValueFactory(new PropertyValueFactory<>("Published_date"));
    }
//
    public void refreshTable() {
        category.clear();
        String SQL = "SELECT *FROM books_category";

            try {
                PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(SQL);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    category.add(new categoryTM(resultSet.getString("Book_type"),
                            resultSet.getString("Book_title"), resultSet.getString("Book_Author"), resultSet.getString("Published_date")));
                    tbl.setItems(category);

                    tbl.setItems(category);
                }

            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e);
            }

            tblType.setCellValueFactory(new PropertyValueFactory<>("Book_type"));
            tblTitle.setCellValueFactory(new PropertyValueFactory<>("Book_title"));
            tblAuthor.setCellValueFactory(new PropertyValueFactory<>("Book_Author"));
            tblPubDate.setCellValueFactory(new PropertyValueFactory<>("Published_date"));

   }
    ArrayList<String> ids = new ArrayList<>();

    public void searchOnKeyReleased(KeyEvent keyEvent) {
        ArrayList<categoryTM> categoryTMS = filterCategory(search.getText());


        tbl.getItems().clear();
        for (categoryTM tms:
             categoryTMS) {
            tbl.getItems().add(tms);
        }

//        if (search.getText().isEmpty()) {
//
//        } else {
//            tbl.getItems().clear();
//            try {
//                ResultSet set = BookCataqgory.idSearch(search.getText());
//                while (set.next()) {
////                    if (ids.isEmpty()) {
////                        ids.add(set.getString(1));
////                    } else {
////                        for (int i = 0; i < ids.size(); i++) {
////                            if (!ids.get(i).equals(search.getText())) {
////                                ids.add(set.getString(1));
////
////                            }
////                        }
////                    }
//                    ids.add(set.getString(1));
//                }
//                setIdData();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//
//        tbl.refresh();
    }

//    private void setIdData() {
//        try {
//            for (int i = 0; i < ids.size(); i++) {
//                ResultSet set = BookCataqgory.getDetails(ids.get(i));
//                if (set.next()) {
//                    setDataTable(set.getString(2), set.getString(3), set.getString(4), set.getString(5));
//                }
//            }
//
//        } catch (SQLException | ClassNotFoundException throwables) {
//            throwables.printStackTrace();
//        }
//
//    }
//
//    private void setDataTable(String type, String title, String author, String date) {
//        category.add(new categoryTM(type, title, author, date));
//        tbl.refresh();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshTable();

        tblType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tblTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tblAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        tblPubDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tbl.setItems(category);
    }

    private ArrayList<categoryTM> filterCategory(String text){
        ArrayList<categoryTM> filteredCategories = new ArrayList<>();
        String SQL = "SELECT * FROM books_category where Book_title like '"+text+"%'";
        try {
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(SQL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                filteredCategories.add(new categoryTM(resultSet.getString("Book_type"),
                        resultSet.getString("Book_title"), resultSet.getString("Book_Author"), resultSet.getString("Published_date")));
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return filteredCategories;
    }
}


