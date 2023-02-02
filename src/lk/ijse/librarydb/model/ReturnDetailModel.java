package lk.ijse.librarydb.model;

import lk.ijse.librarydb.to.ReturnCartDetail;
import lk.ijse.librarydb.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReturnDetailModel {
    public static boolean saveReturnOrderDetail(ArrayList<ReturnCartDetail> returnCartDetails) throws SQLException, ClassNotFoundException {
        for (ReturnCartDetail returnCartDetail : returnCartDetails) {
            if (!save(returnCartDetail)) {
                return false;
            }
        }
        return true;
    }

    private static boolean save(ReturnCartDetail returnCartDetail) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO returnBookDetail VALUES(?, ?, ?, ?)";
        return CrudUtil.execute(sql, returnCartDetail.getCusId(), returnCartDetail.getBook_id(), returnCartDetail.getBorrow_date(), returnCartDetail.getQTY());
    }
}


