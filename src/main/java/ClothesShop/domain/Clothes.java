package ClothesShop.domain;

public class Clothes {
    private int id;
    private Styles styles;
    private Orders orders;
    private float price;
    private String name;

    public Clothes(){}

    public Clothes(Orders orders, Styles styles, float price,String name){
        this.orders=orders;
        this.styles=styles;
        this.price=price;
        this.name=name;
    }


    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public float getPrice() {return price;}

    public void setPrice(float price) {this.price = price;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public Styles getStyles() {return styles;}

    public void setStyles(Styles styles) {this.styles = styles;}

    public Orders getOrders() {return orders;}

    public void setOrders(Orders orders) {this.orders = orders;}
}
