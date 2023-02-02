package lk.ijse.librarydb.to;


import java.time.LocalDate;

public class Borrow {
    private String brwId;
    private String cusid;
    private LocalDate borrow_date;

    public Borrow() {
    }

    public Borrow(String brwId, String cusid, LocalDate borrow_date) {
        this.setBrwId(brwId);
        this.setCusid(cusid);
        this.setBorrow_date(borrow_date);
    }

    public String getBrwId() {
        return brwId;
    }

    public void setBrwId(String brwId) {
        this.brwId = brwId;
    }

    public String getCusid() {
        return cusid;
    }

    public void setCusid(String cusid) {
        this.cusid = cusid;
    }

    public LocalDate getBorrow_date() {
        return borrow_date;
    }

    public void setBorrow_date(LocalDate borrow_date) {
        this.borrow_date = borrow_date;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "brwId='" + brwId + '\'' +
                ", cusid='" + cusid + '\'' +
                ", borrow_date=" + borrow_date +
                '}';
    }
}

