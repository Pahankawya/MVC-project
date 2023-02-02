package lk.ijse.librarydb.model;


import lk.ijse.librarydb.to.Supplier;
import lk.ijse.librarydb.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierModel {

    public static boolean add(Supplier supplier) throws SQLException, ClassNotFoundException {
    String SQL = "INSERT INTO supplier VALUES (?,?,?,?,?)";
    return CrudUtil.execute(SQL, supplier.getSupplierId(), supplier.getName(), supplier.getAge(), supplier.getAddress(), supplier.getPhone_no());
}
    public static Supplier search(String SupplierId) throws SQLException, ClassNotFoundException {
        String SQL = "SELECT * FROM supplier WHERE SupplierId = ?";
        ResultSet result = CrudUtil.execute(SQL, SupplierId);

        if (result.next()){
            return new Supplier(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5)
            );
        }
        return null;
    }
    public static boolean supplierUpdate(Supplier supplier) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("Update supplier set name=?, age =?, address=?, phone_no=? where supplierId=?",
                supplier.getName(),
                supplier.getAge(),
                supplier.getAddress(),
                supplier.getPhone_no(),
                supplier.getSupplierId()

        );
    }
    public static boolean delete(String text) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM supplier WHERE supplierId=?",text);
}
    }