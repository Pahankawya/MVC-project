package lk.ijse.librarydb.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.librarydb.Db.DBConnection;
import lk.ijse.librarydb.model.BookCategoryModel;
import lk.ijse.librarydb.to.Category;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookCategoryUpdateController implements Initializable {

    @FXML
    private AnchorPane pane;

    @FXML
    private JFXTextField txtTitle;

    @FXML
    private JFXTextField txtAuthor;

    @FXML
    private JFXButton FXIDnAddonactionId;

    @FXML
    private JFXButton FXIDupdate;

    @FXML
    private JFXButton FXIDdelete;

    @FXML
    private JFXTextField txtType;

    @FXML
    private JFXButton btnsearch;

    @FXML
    private TextField txtID1;

    @FXML
    private Label lblUserNameWarning;

    @FXML
    private Label lblUserNameWarning1;

    @FXML
    private Label lblUserNameWarning2;

    @FXML
    private JFXDatePicker txtDate;
         private Matcher titleMatcher;
        private Matcher typeMatcher;
        private Matcher authorMatcher;
        private Matcher pbDateMatcher;

        public void setPatterns() {
            Pattern titlePattern = Pattern.compile("^[a-z.\\sA-Z.\\s]{4,}$");
            titleMatcher = titlePattern.matcher(txtTitle.getText());

            Pattern typePattern = Pattern.compile("^[a-z.\\sA-Z.\\s]{4,}$");
            typeMatcher = typePattern.matcher(txtType.getText());

            Pattern authorPattern = Pattern.compile("^[a-z.\\sA-Z.\\s]{4,}$");
            authorMatcher = authorPattern.matcher(txtAuthor.getText());

        }

    @FXML
    void btnAddOnAction(ActionEvent event) {
            patternPerfom();
        String title = txtTitle.getText();
            String type = txtType.getText();
            String Author = txtAuthor.getText();
            String pbDate = txtDate.getValue().toString();
            Category category = new Category(title, type, Author, pbDate);

            try {
                boolean isAdded = BookCategoryModel.add(category);
                if (isAdded) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Category Updated!").show();

                    clearTextField();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Something Happened!").show();
                }
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }

        }


    @FXML
    void btnAuthorOnActionTextField(ActionEvent event) {
        txtDate.requestFocus();
    }

    @FXML
    void btnDateOnActionTextField(ActionEvent event){}

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (BookCategoryModel.delete(txtID1.getText())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Data Deleted!").show();
                clearTextField();
            } else {
                new Alert(Alert.AlertType.WARNING, "No data").show();

            }
        }


    @FXML
    void btnSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String Book_title = txtID1.getText();
            String SQL = "Select * From books_category where Book_title=?";


            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(SQL);

            statement.setObject(1, Book_title);

            ResultSet executeQuery = statement.executeQuery();

            if (executeQuery.next()) {
                txtTitle.setText(executeQuery.getString("Book_type"));
                txtType.setText(executeQuery.getString("Book_title"));
                txtAuthor.setText(executeQuery.getString("Book_Author"));
                txtDate.setValue(LocalDate.parse(executeQuery.getString("Published_date")));

            }
        }


    @FXML
    void btnTitleOnActionTextField(ActionEvent event) {
        txtType.requestFocus();
    }

    @FXML
    void btnTypeOnActionTextField(ActionEvent event) {
        txtAuthor.requestFocus();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        patternPerfom();
        try {
                if (BookCategoryModel.bookUpdate(
                        new Category(txtTitle.getText(),
                                txtType.getText(),
                                txtAuthor.getText(),
                                txtDate.getValue().toString())

                )) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
                    clearTextField();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Something Happened").show();
                }

            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }


    @FXML
    void searchOnAction(ActionEvent event) {
        btnsearch.fire();
    }

    @FXML
    void txtIdOnAction(KeyEvent event) {
        lblUserNameWarning1.setText("");
            txtType.setFocusColor(Paint.valueOf("Green"));

            Pattern titlePattern = Pattern.compile("^[a-z.\\sA-Z.\\s]{4,}$");
            typeMatcher = titlePattern.matcher(txtType.getText());

            if (!typeMatcher.matches()) {
                txtType.requestFocus();
                txtType.setFocusColor(Paint.valueOf("Red"));
                lblUserNameWarning1.setText("invalid");

            }
    }

    @FXML
    void txtPriceOnAction(KeyEvent event) {
        lblUserNameWarning2.setText("");
            txtAuthor.setFocusColor(Paint.valueOf("Green"));

            Pattern authorPattern = Pattern.compile("^[a-z.\\sA-Z.\\s]{4,}$");
            authorMatcher = authorPattern.matcher(txtAuthor.getText());

            if (!authorMatcher.matches()) {
                txtAuthor.requestFocus();
                txtAuthor.setFocusColor(Paint.valueOf("Red"));
                lblUserNameWarning2.setText("invalid");

            }
    }

    @FXML
    void txtTypeOnAction(KeyEvent event) {

    }

    @FXML
    void txttitleOnAction(KeyEvent event) {
        lblUserNameWarning.setText("");
            txtTitle.setFocusColor(Paint.valueOf("Green"));

            Pattern titlePattern = Pattern.compile("^[a-z.\\sA-Z.\\s]{4,}$");
            titleMatcher = titlePattern.matcher(txtTitle.getText());

            if (!titleMatcher.matches()) {
                txtTitle.requestFocus();
                txtTitle.setFocusColor(Paint.valueOf("Red"));
                lblUserNameWarning.setText("invalid");

            }
    }
    public void clearTextField() {
            txtTitle.setText("");
            txtType.setText("");
            txtAuthor.setText("");
            txtDate.setValue(null);
            txtID1.setText("");

        }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setPatterns();
        Platform.runLater(() -> txtTitle.requestFocus());
    }
    public void patternPerfom() {
            if (titleMatcher.matches()) {
                if (typeMatcher.matches()) {
                    if (authorMatcher.matches()) {

                    } else {
                        txtAuthor.requestFocus();
                        txtAuthor.setFocusColor(Paint.valueOf("Red"));
                        lblUserNameWarning2.setText("invalid");
                    }
                } else {
                    txtType.requestFocus();
                    txtType.setFocusColor(Paint.valueOf("Red"));
                    lblUserNameWarning1.setText("invalid");
                }
            } else {
                txtTitle.requestFocus();
                txtTitle.setFocusColor(Paint.valueOf("Red"));
                lblUserNameWarning.setText("invalid");
            }

        }
}
