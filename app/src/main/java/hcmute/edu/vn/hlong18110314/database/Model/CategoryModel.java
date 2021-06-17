package hcmute.edu.vn.hlong18110314.database.Model;

public class CategoryModel {
    private Integer id;
    private String name;
    private int image;

    public CategoryModel() {
    }

    public CategoryModel(String _name, int _image) {
        this.name = _name;
        this.image = _image;
    }

    public CategoryModel(int _id, String _name, int _image) {
        id = _id;
        name = _name;
        image = _image;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
