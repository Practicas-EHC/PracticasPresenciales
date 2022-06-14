package ClothesShop.domain;

import java.time.LocalDate;
import java.util.Date;

public class Orders {
    private int id;
    private Date date;
    private Users users;

    public Orders(){}

    public Orders(Date date, Users users){
        this.date=date;
        this.users=users;
    }

    public Date getDate() {return date;}

    public void setDate(Date date) {this.date = date;}

    public Users getUsers() {return users;}

    public void setUsers(Users users) {this.users = users;}
}
