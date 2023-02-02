package lk.ijse.librarydb.to;

import java.util.ArrayList;

public class PlaceOrder {

    private String cusid;
    private String brwId;
    private ArrayList<CartDetail> orderDetails = new ArrayList<>();

    public PlaceOrder(String cusid, String brwId, ArrayList<CartDetail> orderDetails) {
        this.setCusid(cusid);
        this.setBrwId(brwId);
        this.setOrderDetails(orderDetails);
    }

    public String getCusid() {
        return cusid;
    }

    public void setCusid(String cusid) {
        this.cusid = cusid;
    }

    public String getBrwId() {
        return brwId;
    }

    public void setBrwId(String brwId) {
        this.brwId = brwId;
    }

    public ArrayList<CartDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(ArrayList<CartDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "PlaceOrder{" +
                "cusid='" + cusid + '\'' +
                ", brwId='" + brwId + '\'' +
                ", orderDetails=" + orderDetails +
                '}';
    }
}