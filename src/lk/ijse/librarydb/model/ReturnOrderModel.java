package lk.ijse.librarydb.model;
import lk.ijse.librarydb.Db.DBConnection;
import lk.ijse.librarydb.to.Return;
import lk.ijse.librarydb.to.ReturnOrder;

import java.sql.SQLException;
import java.time.LocalDate;

public class ReturnOrderModel {
    public static boolean returnOrder(ReturnOrder returnOrder) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean isOrderAdded = ReturnModel.save(new Return(returnOrder.getCusId(), returnOrder.getBook_id(), LocalDate.now()));
            if (isOrderAdded) {
                boolean isUpdated = returnBookModel.updateQtyReturn(returnOrder.getReturnCartDetails());
                if (isUpdated) {
                    boolean isOrderDetailAdded = ReturnDetailModel.saveReturnOrderDetail(returnOrder.getReturnCartDetails());
                    if (isOrderDetailAdded) {
                        DBConnection.getInstance().getConnection().commit();
                        return true;
                    }
                }
            }
            DBConnection.getInstance().getConnection().rollback();
            return false;
        } finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }
}
