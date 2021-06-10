package hcmute.edu.vn.hlong18110314.database.Model;

public class CartModel {
    public int productId;
    public  String name;
    public  int price;
    public  byte[] image;
    public  int numberOfProduct;
    public  int totalPrice;

    public CartModel(int productId, String name, int price, byte[] image, int numberOfProduct, int totalPrice) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.image = image;
        this.numberOfProduct = numberOfProduct;
        this.totalPrice = totalPrice;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getNumberOfProduct() {
        return numberOfProduct;
    }

    public void setNumberOfProduct(int numberOfProduct) {
        this.numberOfProduct = numberOfProduct;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
