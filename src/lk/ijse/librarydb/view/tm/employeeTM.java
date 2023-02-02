package lk.ijse.librarydb.view.tm;

public class employeeTM {
    private String Sid;
    private String firstname;
    private String lastname;
    private String age;
    private String nic;
    private String phone_no;
    private String address;
    private String userName;
    private String password;

    public employeeTM(String sid, String firstname, String lastname, String age, String nic, String phone_no, String address, String userName, String password) {
        setSid(sid);
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setAge(age);
        this.setNic(nic);
        this.setPhone_no(phone_no);
        this.setAddress(address);
        this.setUserName(userName);
        this.setPassword(password);
    }


    public String getSid() {
        return Sid;
    }

    public void setSid(String sid) {
        Sid = sid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "Sid='" + Sid + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age='" + age + '\'' +
                ", nic='" + nic + '\'' +
                ", phone_no='" + phone_no + '\'' +
                ", address='" + address + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

