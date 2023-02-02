package lk.ijse.librarydb.model;

import lk.ijse.librarydb.to.Books;
import lk.ijse.librarydb.to.CartDetail;
import lk.ijse.librarydb.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookModel {
    public static ArrayList<String> loadbookIds() throws SQLException, ClassNotFoundException {
        String sql = "SELECT Book_id FROM Books";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> codeList = new ArrayList<>();

        while (result.next()) {
            codeList.add(result.getString(1));
        }
        return codeList;
    }

    public static boolean add(Books books) throws SQLException, ClassNotFoundException {

        String SQL = "INSERT INTO Books VALUES(?,?,?,?,?)";
        return CrudUtil.execute(SQL, books.getBook_id(), books.getBook_title(), books.getBook_price(), books.getType(), books.getQTY());
    }

    public static Books search(String Book_id) throws SQLException, ClassNotFoundException {
        String SQL = "SELECT *FROM Books WHERE Book_id = ?";
        ResultSet result = CrudUtil.execute(SQL, Book_id);

        if (result.next()) {
            return new Books(
                    result.getString(1),
                    result.getString(2),
                    result.getDouble(3),
                    result.getString(4),
                    result.getInt(5)

            );
        }
        return null;
    }

    public static boolean delete(String text) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Books WHERE Book_id=?", text);

    }

    public static boolean bookUpdate(Books books) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("Update Books set Book_title=?, Book_price =?, type=?, QTY=? WHERE Book_id=?",
                books.getBook_title(),
                books.getBook_price(),
                books.getType(),
                books.getQTY(),
                books.getBook_id()
        );
    }

    public static boolean updateQty(ArrayList<CartDetail> cartDetails) throws SQLException, ClassNotFoundException {
        for (CartDetail cartDetail : cartDetails) {
            if (!updateQty(new Books(cartDetail.getBook_id(), cartDetail.getBook_title(), cartDetail.getBook_price(), cartDetail.getType(), cartDetail.getQTY()))) {
                return false;
            }
        }
        return true;
    }

    private static boolean updateQty(Books books) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE books SET QTY = QTY - ? WHERE Book_id = ?";
        return CrudUtil.execute(sql, books.getQTY(), books.getBook_id());
    }

}
