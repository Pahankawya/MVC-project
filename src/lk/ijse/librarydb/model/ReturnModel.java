package lk.ijse.librarydb.model;


import lk.ijse.librarydb.to.Return;
import lk.ijse.librarydb.util.CrudUtil;

import java.sql.SQLException;

public class ReturnModel {
    public static boolean save(Return returns) throws SQLException, ClassNotFoundException {
        String SQL = "INSERT INTO returnBook VALUES (?,?,?)";
        return CrudUtil.execute(SQL, returns.getCusid(), returns.getBook_id(), returns.getReturn_date());
    }

    public static boolean delete(String text) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM returnbook WHERE cusid=?", text);
    }
}
