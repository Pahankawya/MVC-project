package lk.ijse.librarydb.model;

import lk.ijse.librarydb.to.Borrow;
import lk.ijse.librarydb.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BorrowModel {
    public static boolean save(Borrow borrow) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO borrow VALUES(?, ?, ? )";
        return CrudUtil.execute(sql, borrow.getBrwId(), borrow.getCusid(), borrow.getBorrow_date());
    }

    public static String generateNextOrderId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT brwId FROM borrow ORDER BY brwId DESC LIMIT 1";
        ResultSet result = CrudUtil.execute(sql);

        if (result.next()) {
            return generateNextOrderId(result.getString(1));
        }
        return generateNextOrderId(result.getString(null));
    }

    private static String generateNextOrderId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("D0");
            int brwId = Integer.parseInt(split[1]);
            brwId += 1;
            return "D0" + brwId;
        }
        return "O001";

    }
}
