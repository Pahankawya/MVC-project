package lk.ijse.librarydb.to;

public class Category {
    private String Book_type;
    private String book_title;
    private String book_Author;
    private String Published_date;

    public Category(String book_type, String book_title, String book_Author, String published_date) {
        setBook_type(book_type);
        this.setBook_title(book_title);
        this.setBook_Author(book_Author);
        setPublished_date(published_date);
    }

    public String getBook_type() {
        return Book_type;
    }

    public void setBook_type(String book_type) {
        Book_type = book_type;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getBook_Author() {
        return book_Author;
    }

    public void setBook_Author(String book_Author) {
        this.book_Author = book_Author;
    }

    public String getPublished_date() {
        return Published_date;
    }
    public void setPublished_date(String published_date) {
        Published_date = published_date;
    }
    @Override
    public String toString() {
        return "Category{" +
                "Book_type='" + Book_type + '\'' +
                ", book_title='" + book_title + '\'' +
                ", book_Author='" + book_Author + '\'' +
                ", Published_date='" + Published_date + '\'' +
                '}';
    }


    }
