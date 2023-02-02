package lk.ijse.librarydb.model;

import lk.ijse.librarydb.to.Customer;
import lk.ijse.librarydb.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {
    public static ArrayList<String> loadCusIds() throws SQLException, ClassNotFoundException {
        String sql = "SELECT Cusid FROM customer";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> codeList = new ArrayList<>();

        while (result.next()) {
            codeList.add(result.getString(1));
        }
        return codeList;
    }
    public static boolean add(Customer customer) throws SQLException, ClassNotFoundException {

        String SQL = "INSERT INTO customer VALUES(?,?,?,?,?,?)";
        return CrudUtil.execute(SQL, customer.getCusid(), customer.getName(), customer.getAge(), customer.getPhone_no(), customer.getAddress(), customer.getEmail());
    }

    public static Customer search(String Cusid) throws SQLException, ClassNotFoundException {
    String SQL = "SELECT * FROM customer WHERE Cusid = ?";
    ResultSet result = CrudUtil.execute(SQL, Cusid);

        if (result.next()){
        return new Customer(
                result.getString(1),
                result.getString(2),
                result.getString(3),
                result.getString(4),
                result.getString(5),
                result.getString(6)
        );
    }
        return null;
}

    public static boolean delete(String text) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM customer WHERE Cusid=?",text);
    }
    public static boolean customerUpdate(Customer customer) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE customer SET name=?, age=?, phone_no=?, address=?, email=? WHERE Cusid=?",
                customer.getName(),
                customer.getAge(),
                customer.getPhone_no(),
                customer.getAddress(),
                customer.getEmail(),
                customer.getCusid()

        );
    }
    }

