package lk.ijse.librarydb.to;

import java.time.LocalDate;

public class Return {
     private String cusid;
     private String book_id;
     private LocalDate return_date;

    public Return(String cusid, String book_id, LocalDate return_date) {
        this.setCusid(cusid);
        this.setBook_id(book_id);
        this.setReturn_date(return_date);
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

    public LocalDate getReturn_date() {
        return return_date;
    }

    public void setReturn_date(LocalDate return_date) {
        this.return_date = return_date;
    }

    @Override
    public String toString() {
        return "Return{" +
                "cusid='" + cusid + '\'' +
                ", book_id='" + book_id + '\'' +
                ", return_date=" + return_date +
                '}';
    }
}