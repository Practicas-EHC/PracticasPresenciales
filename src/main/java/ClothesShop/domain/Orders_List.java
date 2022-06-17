package ClothesShop.domain;


public class Orders_List {

        private int clothes_id;
        private int order_id;
        private String clothes_size;
        private Number quantity;

        public Orders_List(int clothes_id, int order_id, String clothe_size, Number quantity) {

                this.clothes_id = clothes_id;
                this.order_id = order_id;
                this.clothes_size = clothes_size;
                this.quantity = quantity;
        }

        public Orders_List(String clothes_id, String order_id, String clothe_size, String quantity) {
        }


        public int getClothes_id() {return clothes_id;}

        public void setClothes_id(int clothes_id) {this.clothes_id = clothes_id;}


        public int getOrder_id() {return order_id;}

        public void setOrder_id(int order_id) {this.order_id = order_id;}


        public String getClothe_size() {return clothes_size;}

        public void setClothe_size(String clothe_size) {this.clothes_size = clothe_size;}


        public Number getQuantity() {return quantity;}

        public void setQuantity(Number quantity) {this.quantity = quantity;}

        public String toString() {
                return "Orders_List{" +
                        "Order_id='" + order_id + '\'' +
                        ", Clothes_id='" + clothes_id + '\'' +
                        ", Clothe_size=" + clothes_size +
                        ", Quantity='" + quantity + '\'' +
                        '}';
        }


}
