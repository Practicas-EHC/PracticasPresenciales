package ClothesShop.domain;

import java.util.ArrayList;

public class Clothes {
    private int id;
    private String styles;
    private String orders;
    private float price;
    private String name;

    public Clothes(){}

    public Clothes(String orders, String styles, float price,String name){
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

    public String getStyles() {return styles;}

    public void setStyles(String styles) {this.styles = styles;}

    public String getOrders() {return orders;}

    public void setOrders(String  orders) {this.orders = orders;}



}
