package lk.ijse.librarydb.to;

import java.time.LocalDate;

public class ReturnCartDetail {
    private String CusId;
    private String book_id;
    private String book_title;
    private LocalDate borrow_date;
    private double book_price;
    private int QTY;
    private String type;

    public ReturnCartDetail(String cusId, String book_id, LocalDate borrow_date, double book_price) {
        setCusId(cusId);
        this.setBook_id(book_id);
        this.setBook_title(book_title);
        this.setBorrow_date(borrow_date);
        this.setBook_price(book_price);
        this.setQTY(QTY);
        this.setType(type);
    }

    public String getCusId() {
        return CusId;
    }

    public void setCusId(String cusId) {
        CusId = cusId;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public LocalDate getBorrow_date() {
        return borrow_date;
    }

    public void setBorrow_date(LocalDate borrow_date) {
        this.borrow_date = borrow_date;
    }

    public double getBook_price() {
        return book_price;
    }

    public void setBook_price(double book_price) {
        this.book_price = book_price;
    }

    public int getQTY() {
        return QTY;
    }

    public void setQTY(int QTY) {
        this.QTY = QTY;
    }

    public String getType() {
        return type;
    }



    public void setType(String type) {
        this.type = type;

    }
    @Override
    public String toString() {
        return "ReturnCartDetail{" +
                "CusId='" + CusId + '\'' +
                ", book_id='" + book_id + '\'' +
                ", book_title='" + book_title + '\'' +
                ", borrow_date=" + borrow_date +
                ", book_price=" + book_price +
                ", QTY=" + QTY +
                ", type='" + type + '\'' +
                '}';
    }
}