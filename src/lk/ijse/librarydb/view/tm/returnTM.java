package lk.ijse.librarydb.view.tm;

public class returnTM {
    private String cusid;
    private String book_id;
    private String borrow_date;
    private String return_date;


    public returnTM(String cusid, String book_id, String borrow_date, String return_date) {
        this.setCusid(cusid);
        this.setBook_id(book_id);
        this.setBorrow_date(borrow_date);
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

    public String getBorrow_date() {
        return borrow_date;
    }

    public void setBorrow_date(String borrow_date) {
        this.borrow_date = borrow_date;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }

    @Override
    public String toString() {
        return "Return{" +
                "cusid='" + cusid + '\'' +
                ", book_id='" + book_id + '\'' +
                ", borrow_date='" + borrow_date + '\'' +
                ", return_date='" + return_date + '\'' +
                '}';
    }
}
