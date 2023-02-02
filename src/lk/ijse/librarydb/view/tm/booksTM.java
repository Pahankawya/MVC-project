package lk.ijse.librarydb.view.tm;

public class booksTM {
    private String Book_id;
    private String Book_title;
    private String Book_price;
    private String type;
    private String QTY;

    public booksTM(String book_id, String book_title, String book_price, String type, String QTY) {
        setBook_id(book_id);
        setBook_title(book_title);
        setBook_price(book_price);
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

    public String getBook_price() {
        return Book_price;
    }

    public void setBook_price(String book_price) {
        Book_price = book_price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQTY() {
        return QTY;
    }

    public void setQTY(String QTY) {
        this.QTY = QTY;
    }

    @Override
    public String toString() {
        return "Books{" +
                "Book_id='" + Book_id + '\'' +
                ", Book_title='" + Book_title + '\'' +
                ", Book_price='" + Book_price + '\'' +
                ", type='" + type + '\'' +
                ", QTY='" + QTY + '\'' +
                '}';
    }
}
