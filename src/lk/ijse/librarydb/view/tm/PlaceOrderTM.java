package lk.ijse.librarydb.view.tm;

import javafx.scene.control.Button;

public class PlaceOrderTM {
    private String Book_id;
    private String Book_title;
    private int QTY;
    private double Book_price;
    private double Total;
    private Button delete;


    public PlaceOrderTM(String book_id, String book_title, int QTY, double book_price, double total, Button delete) {
        this.setBook_id(book_id);
        this.setBook_title(book_title);
        this.setQTY(QTY);
        this.setBook_price(book_price);
        this.setTotal(total);
        this.setDelete(delete);
    }

    public PlaceOrderTM(String book_id, String book_title, int qty, double book_price, double total) {
    }

    public String getBook_id() {
        return Book_id;
    }

    public void setBook_id(String book_id) {
        Book_id = book_id;
    }

    public String getBook_title() {
        return Book_title;
    }

    public void setBook_title(String book_title) {
        Book_title = book_title;
    }

    public int getQTY() {
        return QTY;
    }

    public void setQTY(int QTY) {
        this.QTY = QTY;
    }

    public double getBook_price() {
        return Book_price;
    }

    public void setBook_price(double book_price) {
        Book_price = book_price;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }

    @Override
    public String toString() {
        return "borrowTM{" +
                "Book_id='" + Book_id + '\'' +
                ", Book_title='" + Book_title + '\'' +
                ", QTY=" + QTY +
                ", Book_price=" + Book_price +
                ", Total=" + Total +
                ", delete=" + delete +
                '}';
    }
}
