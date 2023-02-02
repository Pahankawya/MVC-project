package lk.ijse.librarydb.model;

import lk.ijse.librarydb.to.Books;
import lk.ijse.librarydb.to.ReturnCartDetail;
import lk.ijse.librarydb.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class returnBookModel {
    public static boolean updateQtyReturn(ArrayList<ReturnCartDetail> returnCartDetails) throws SQLException, ClassNotFoundException {
        for (ReturnCartDetail returnCartDetail : returnCartDetails) {
            if (!updateQtyReturn(new Books(returnCartDetail.getBook_id(), returnCartDetail.getBook_title(), returnCartDetail.getBook_price(), returnCartDetail.getType(),returnCartDetail.getQTY()))) {
                return false;
            }
        }
        return true;
    }

    private static boolean updateQtyReturn(Books books) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE books SET QTY = QTY + ? WHERE Book_id = ?";
        return CrudUtil.execute(sql, books.getQTY(), books.getBook_id());
    }
}


