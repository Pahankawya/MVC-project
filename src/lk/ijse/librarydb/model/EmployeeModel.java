package lk.ijse.librarydb.model;

import lk.ijse.librarydb.to.Employee;
import lk.ijse.librarydb.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeModel {
    public static boolean add(Employee employee) throws SQLException, ClassNotFoundException {
        String SQL = "INSERT INTO staff VALUES (?,?,?,?,?,?,?,?,?)";
        return CrudUtil.execute(SQL, employee.getSid(), employee.getFirstname(), employee.getLastname(), employee.getAge(), employee.getNic(), employee.getPhone_no(), employee.getAddress(),
                employee.getUserName(), employee.getPassword());
    }
    public static boolean delete(String text) throws SQLException, ClassNotFoundException {
            return CrudUtil.execute("DELETE FROM staff WHERE Sid=?",text);

    }
    public static boolean staffUpdate(Employee employee) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("Update staff set firstname=?, lastname =?, age=?, nic=?, phone_no=?, address=?, username=?, password=? where Sid=?",
                employee.getFirstname(),
                employee.getLastname(),
                employee.getAge(),
                employee.getNic(),
                employee.getPhone_no(),
                employee.getAddress(),
                employee.getUserName(),
                employee.getPassword(),
                employee.getSid()

        );
    }
    public static Employee search(String Sid) throws SQLException, ClassNotFoundException {
    String SQL = "SELECT * FROM staff WHERE Sid = ?";
    ResultSet result = CrudUtil.execute(SQL, Sid);

        if (result.next()){
        return new Employee(
                result.getString(1),
                result.getString(2),
                result.getString(3),
                result.getString(4),
                result.getString(5),
                result.getString(6),
                result.getString(7),
                result.getString(8),
                result.getString(9)
        );
    }
        return null;
}
}
