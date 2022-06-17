package ClothesShop.domain;

import java.util.ArrayList;

// CREAR ENUM PARA COLECCIONES
public class Clothes {
    private int id;
    private String styles;
    private String collection;
    private String orders;
    private float price;
    private String name;

    public Clothes(){}

    public Clothes(String orders, String styles,String collection, float price,String name){
        this.orders=orders;
        this.styles=styles;
        this.collection=collection;
        this.price=price;
        this.name=name;
    }


    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public float getPrice() {return price;}

    public void setPrice(float price) {this.price = price;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getStyles() {return styles;}

    public void setStyles(String styles) {this.styles = styles;}

    public String getOrders() {return orders;}

    public void setOrders(String  orders) {this.orders = orders;}


    public String getCollection() {return collection;}

    public void setCollection(String collection) {this.collection = collection;}
}
