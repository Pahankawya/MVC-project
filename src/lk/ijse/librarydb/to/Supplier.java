package lk.ijse.librarydb.to;

public class Supplier {
    private String supplierId;
    private String name;
    private String age;
    private String address;
    private String phone_no;

    public Supplier(String supplierId, String name, String age, String address, String phone_no) {
        this.setSupplierId(supplierId);
        this.setName(name);
        this.setAge(age);
        this.setAddress(address);
        this.setPhone_no(phone_no);
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierId='" + supplierId + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", address='" + address + '\'' +
                ", phone_no='" + phone_no + '\'' +
                '}';
    }
}
