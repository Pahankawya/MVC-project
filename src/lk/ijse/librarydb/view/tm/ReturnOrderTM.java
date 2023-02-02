package lk.ijse.librarydb.view.tm;

import javafx.scene.control.Button;

import java.sql.Date;
import java.time.LocalDate;

public class ReturnOrderTM {
    private String cusid;
    private String book_id;
    private LocalDate borrow_date;
    private LocalDate return_date;
    private int Qty;
    private Button delete;


    public ReturnOrderTM(String cusid, String book_id, LocalDate borrow_date, LocalDate return_date, int qty, Button delete) {
        this.setCusid(cusid);
        this.setBook_id(book_id);
        this.setBorrow_date(borrow_date);
        this.setReturn_date(return_date);
        setQty(qty);
        this.setDelete(delete);
    }

    public ReturnOrderTM(String cusid, String book_id, Date borrow_date, String return_date, int qty ) {
    }

    public String getCusid() {
        return cusid;
    }

    public void setCusid(String cusid) {
        this.cusid = cusid;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public LocalDate getBorrow_date() {
        return borrow_date;
    }

    public void setBorrow_date(LocalDate borrow_date) {
        this.borrow_date = borrow_date;
    }

    public LocalDate getReturn_date() {
        return return_date;
    }

    public void setReturn_date(LocalDate return_date) {
        this.return_date = return_date;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }

    @Override
    public String toString() {
        return "ReturnOrderTM{" +
                "cusid='" + cusid + '\'' +
                ", book_id='" + book_id + '\'' +
                ", borrow_date=" + borrow_date +
                ", return_date=" + return_date +
                ", Qty=" + Qty +
                ", delete=" + delete +
                '}';
    }
}