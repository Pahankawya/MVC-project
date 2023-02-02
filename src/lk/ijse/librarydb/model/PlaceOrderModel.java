package lk.ijse.librarydb.model;


import lk.ijse.librarydb.Db.DBConnection;
import lk.ijse.librarydb.to.Borrow;
import lk.ijse.librarydb.to.PlaceOrder;

import java.sql.SQLException;
import java.time.LocalDate;

public class PlaceOrderModel {


        public static boolean placeOrder(PlaceOrder placeOrder) throws SQLException, ClassNotFoundException {
            try {
                DBConnection.getInstance().getConnection().setAutoCommit(false);
                boolean isOrderAdded = BorrowModel.save(new Borrow(placeOrder.getBrwId(),  placeOrder.getCusid(),LocalDate.now()));
                if (isOrderAdded) {
                    boolean isUpdated = BookModel.updateQty(placeOrder.getOrderDetails());
                    if (isUpdated) {
                        boolean isOrderDetailAdded = borrowDetailModel.saveOrderDetails(placeOrder.getOrderDetails());
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

