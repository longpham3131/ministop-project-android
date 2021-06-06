package hcmute.edu.vn.hlong18110314.database.Model;

public class OrderModel {


    private Integer id;
    private String name;
    public String dateCreated;
    private Integer total;
    private Integer userID;
    private String status;



    public OrderModel() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer quantity) {
        this.total = quantity;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
