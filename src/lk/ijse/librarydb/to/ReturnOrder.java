package lk.ijse.librarydb.to;

import java.util.ArrayList;

public class ReturnOrder {
    private String CusId;
    private String Book_id;
    private ArrayList<ReturnCartDetail> returnCartDetails = new ArrayList<>();

    public ReturnOrder(String cusId, String book_id, ArrayList<ReturnCartDetail> returnCartDetails) {
        this.setCusId(cusId);
        this.setBook_id(book_id);
        this.setReturnCartDetails(returnCartDetails);
    }

    public String getCusId() {
        return CusId;
    }

    public void setCusId(String cusId) {
        CusId = cusId;
    }

    public String getBook_id() {
        return Book_id;
    }

    public void setBook_id(String book_id) {
        Book_id = book_id;
    }

    public ArrayList<ReturnCartDetail> getReturnCartDetails() {
        return returnCartDetails;
    }

    public void setReturnCartDetails(ArrayList<ReturnCartDetail> returnCartDetails) {
        this.returnCartDetails = returnCartDetails;
    }

    @Override
    public String toString() {
        return "ReturnOrder{" +
                "CusId='" + CusId + '\'' +
                ", Book_id='" + Book_id + '\'' +
                ", returnCartDetails=" + returnCartDetails +
                '}';
    }
}
