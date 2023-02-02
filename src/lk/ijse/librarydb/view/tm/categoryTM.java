package lk.ijse.librarydb.view.tm;

public class categoryTM {
    private String type;
    private String title;
    private String author;
    private String date;

    public categoryTM() {
    }

    public categoryTM(String type, String title, String author, String date) {
        this.type = type;
        this.title = title;
        this.author = author;
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String titel) {
        this.title = titel;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "categoryTM{" +
                "type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
