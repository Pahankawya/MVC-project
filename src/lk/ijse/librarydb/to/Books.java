package lk.ijse.librarydb.to;

public class Books {


    private String Book_id;
    private String Book_title;
    private double Book_price;
    private String type;
    private int QTY;


    public Books(String book_id, String book_title, double book_price, String type, int QTY) {
        this.setBook_id(book_id);
        this.setBook_title(book_title);
        this.setBook_price(book_price);
        this.setType(type);
        this.setQTY(QTY);
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

    public double getBook_price() {
        return Book_price;
    }

    public void setBook_price(double book_price) {
        Book_price = book_price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQTY() {
        return QTY;
    }

    public void setQTY(int QTY) {
        this.QTY = QTY;

    }

    @Override
    public String toString() {
        return "Books{" +
                "Book_id='" + Book_id + '\'' +
                ", Book_title='" + Book_title + '\'' +
                ", Book_price=" + Book_price +
                ", type='" + type + '\'' +
                ", QTY=" + QTY +
                '}';
    }
}