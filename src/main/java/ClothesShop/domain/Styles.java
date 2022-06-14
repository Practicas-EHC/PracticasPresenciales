package ClothesShop.domain;

public class Styles {
    private int id;
    private String name;

    public Styles(){};

    public Styles(String name){
        this.name=name;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}
}
