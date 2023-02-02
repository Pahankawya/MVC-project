package lk.ijse.librarydb.model;

import lk.ijse.librarydb.to.CartDetail;
import lk.ijse.librarydb.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class borrowDetailModel {
    public static boolean saveOrderDetails(ArrayList<CartDetail> cartDetails) throws SQLException, ClassNotFoundException {
        for (CartDetail cartDetail : cartDetails) {
            if (!save(cartDetail)) {
                return false;
            }
        }
        return true;
    }

    private static boolean save(CartDetail cartDetail) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO borrowdetail VALUES(?, ?, ?, ?)";
        return CrudUtil.execute(sql, cartDetail.getBrwId(), cartDetail.getBook_id(), cartDetail.getQTY(), cartDetail.getBook_price());
    }
}
