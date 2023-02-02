package lk.ijse.librarydb.model;

import lk.ijse.librarydb.to.Borrow;
import lk.ijse.librarydb.to.BorrowDetail;
import lk.ijse.librarydb.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

// TODO FOR THE OTHER METHOD!

public class OrderCrudModel {
        public boolean savetestOrder(Borrow borrow) throws SQLException, ClassNotFoundException {
            return CrudUtil.execute("INSERT INTO `borrow` VALUES(?,?,?)",
                    borrow.getBrwId(), borrow.getCusid(), borrow.getBorrow_date());
        }

        public boolean savetestOrderDetails(ArrayList<BorrowDetail> borrowDetails) throws SQLException, ClassNotFoundException {
            for (BorrowDetail borrow : borrowDetails
            ) {

                boolean isDetailsSaved = CrudUtil.execute("INSERT INTO `borrowdetail` VALUES(?,?,?,?)",
                        borrow.getBrwId(), borrow.getBook_id(),  borrow.getBook_price());
                if (isDetailsSaved) {
                    if (!updatetestQty(borrow.getBook_id(), (int) borrow.getQTY())) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
            return true;
        }

        private boolean updatetestQty(String Book_id, int QTY) throws SQLException, ClassNotFoundException {
            return CrudUtil.execute("UPDATE Books SET QTY = QTY - ? WHERE Book_id = ?", QTY, Book_id);
        }

}