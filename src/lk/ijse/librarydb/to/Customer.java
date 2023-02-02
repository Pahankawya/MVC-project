package lk.ijse.librarydb.to;

public class Customer {
      private String Cusid;
      private String name;
      private String age;
      private String phone_no;
      private String address;
      private String email;

    public Customer() {
    }

    public Customer(String cusid, String name, String age, String phone_no, String address, String email) {
        this.setCusid(cusid);
        this.setName(name);
        this.setAge(age);
        this.setPhone_no(phone_no);
        this.setAddress(address);
        this.setEmail(email);
    }



    public String getCusid() {
        return Cusid;
    }

    public void setCusid(String cusid) {
        Cusid = cusid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "Cusid='" + Cusid + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", phone_no='" + phone_no + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}