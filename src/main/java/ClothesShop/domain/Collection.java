package ClothesShop.domain;

public class Collection {
    private int id;
    private String name;

    public Collection(){}

    public Collection(String name){this.name=name;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}
}
