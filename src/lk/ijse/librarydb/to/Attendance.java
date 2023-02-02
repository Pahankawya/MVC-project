package lk.ijse.librarydb.to;

public class Attendance {
    private String Sid;
    private String Date;
    private String int_time;
    private String out_time;

    public Attendance(String sid, String date, String int_time, String out_time) {
        this.setSid(sid);
        this.setDate(date);
        this.setInt_time(int_time);
        this.setOut_time(out_time);
    }

    public String getSid() {
        return Sid;
    }

    public void setSid(String sid) {
        Sid = sid;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getInt_time() {
        return int_time;
    }

    public void setInt_time(String int_time) {
        this.int_time = int_time;
    }

    public String getOut_time() {
        return out_time;
    }

    public void setOut_time(String out_time) {
        this.out_time = out_time;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "Sid='" + Sid + '\'' +
                ", Date='" + Date + '\'' +
                ", int_time='" + int_time + '\'' +
                ", out_time='" + out_time + '\'' +
                '}';
    }
}
