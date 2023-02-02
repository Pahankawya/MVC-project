package lk.ijse.librarydb.to;

public class BorrowDetail {
    private String brwId;
    private String book_id;
    private int QTY;
    private double Book_price;

    public BorrowDetail() {
    }

    public BorrowDetail(String brwId, String book_id, int QTY, double book_price) {
        this.setBrwId(brwId);
        this.setBook_id(book_id);
        this.setQTY(QTY);
        setBook_price(book_price);
    }

    public String getBrwId() {
        return brwId;
    }

    public void setBrwId(String brwId) {
        this.brwId = brwId;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
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

    @Override
    public String toString() {
        return "BorrowDetail{" +
                "brwId='" + brwId + '\'' +
                ", book_id='" + book_id + '\'' +
                ", QTY=" + QTY +
                ", Book_price=" + Book_price +
                '}';
    }
}
