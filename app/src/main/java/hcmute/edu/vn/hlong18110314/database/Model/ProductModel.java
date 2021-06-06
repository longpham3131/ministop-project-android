package hcmute.edu.vn.hlong18110314.database.Model;

import java.util.ArrayList;

public class ProductModel {
    private Integer id;
    private String name;
    private byte[] image;
    private Integer categoryId;
    private String description;
    private int price;

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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public ProductModel(){}
    public ProductModel(String name, Integer price){
        this.name = name;
        this.price = price;
    }
    public ProductModel( String name, byte[] image, Integer categoryId, String description,int price){
        this.name = name;
        this.image=image;
        this.categoryId = categoryId;
        this.description = description;
        this.price = price;
    }
    public static ArrayList<ProductModel> createContactsList(int numContacts) {
        ArrayList<ProductModel> contacts = new ArrayList<ProductModel>();

        for (int i = 1; i <= numContacts; i++) {
            contacts.add(new ProductModel( "Aloaloalo", 20000));
        }

        return contacts;
    }
}
