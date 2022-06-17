package ClothesShop.domain;

import java.util.Date;

public class Orders {
    private int id;
    private Date date;
    private String username;

    private String clothes;

    public Orders() {
    }

    public Orders(Date date, String username) {
        this.date = date;
        this.username = username;
    }

    public Date getDate() {return date;}

    public void setDate(Date date) {this.date = date;}

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getClothes() {return clothes;}

    public void setClothes(String clothes) {this.clothes = clothes;}
}


