package lk.ijse.librarydb.to;

public class CartDetail {

    private String brwId;
    private String book_id;
    private String book_title;
    private int QTY;
    private double book_price;
    private String type;

    public CartDetail(String brwId, String book_id, String book_title, int QTY, double book_price) {
        this.setBrwId(brwId);
        this.setBook_id(book_id);
        this.setBook_title(book_title);
        this.setQTY(QTY);
        this.setBook_price(book_price);
    }

    public CartDetail(String type) {
        this.setType(type);
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

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public int getQTY() {
        return QTY;
    }

    public void setQTY(int QTY) {
        this.QTY = QTY;
    }

    public double getBook_price() {
        return book_price;
    }

    public void setBook_price(double book_price) {
        this.book_price = book_price;
    }

    @Override
    public String toString() {
        return "CartDetail{" +
                "brwId='" + brwId + '\'' +
                ", book_id='" + book_id + '\'' +
                ", book_title='" + book_title + '\'' +
                ", QTY=" + QTY +
                ", book_price=" + book_price +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}