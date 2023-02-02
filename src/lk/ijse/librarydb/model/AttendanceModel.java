package lk.ijse.librarydb.model;

import lk.ijse.librarydb.to.Attendance;
import lk.ijse.librarydb.util.CrudUtil;
import java.sql.SQLException;

public class AttendanceModel {
    public static boolean add(Attendance attendance) throws SQLException, ClassNotFoundException {
        String SQL = "INSERT INTO attendance VALUES (?,?,?,?)";
        return CrudUtil.execute(SQL, attendance.getSid(), attendance.getDate(), attendance.getInt_time(), attendance.getOut_time());
    }

    public static boolean delete(String text) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM attendance WHERE Sid=?", text);
    }
}
