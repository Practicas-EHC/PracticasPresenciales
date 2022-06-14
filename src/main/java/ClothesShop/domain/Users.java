package ClothesShop.domain;

public class Users {
    private String username;
    private String password;
    private String adress;
    private String email;
    private String telephone;

    public Users(){}

    public Users(String username, String password,String adress,String email,String telephone){
        this.username=username;
        this.password=password;
        this.adress=adress;
        this.email=email;
        this.telephone=telephone;

    }

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getAdress() {return adress;}

    public void setAdress(String adress) {this.adress = adress;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getTelephone() {return telephone;}

    public void setTelephone(String telephone) {this.telephone = telephone;}
}
