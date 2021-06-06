package hcmute.edu.vn.hlong18110314.database.Model;

public class OrderDetailModel {
    private Integer id;
    private Integer orderId;
    private Integer productId;
    private Integer quantity;
    private Integer total;

    public OrderDetailModel(){}
    public OrderDetailModel(Integer orderId, Integer productId,Integer quantity,Integer total){
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
