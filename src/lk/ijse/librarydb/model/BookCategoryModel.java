package lk.ijse.librarydb.model;


import lk.ijse.librarydb.to.Category;
import lk.ijse.librarydb.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookCategoryModel {
    public static boolean add(Category category) throws SQLException, ClassNotFoundException {

        String SQL = "INSERT INTO books_category VALUES(?,?,?,?)";
        return CrudUtil.execute(SQL, category.getBook_type(), category.getBook_title(), category.getBook_Author(), category.getPublished_date());
    }

    public static Category search(String Book_title) throws SQLException, ClassNotFoundException {
        String SQL = "SELECT *FROM books_category WHERE Book_title = ?";
        ResultSet result = CrudUtil.execute(SQL, Book_title);

        if (result.next()) {
            return new Category(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)

            );
        }
        return null;
    }

    public static boolean delete(String text) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM books_category WHERE Book_title=?", text);

    }

    public static boolean bookUpdate(Category category) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("Update books_category set Book_type=?, Book_Author =?, Published_date=? WHERE Book_title=?",
                category.getBook_type(),
                category.getBook_Author(),
                category.getPublished_date(),
                category.getBook_title()
        );
    }
}