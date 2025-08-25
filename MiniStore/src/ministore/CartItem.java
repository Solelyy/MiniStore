package ministore;

public class CartItem {
    private int quantity;
    private String prodName;

    CartItem (String prodName, int quantity){
        this.prodName= prodName;
        this.quantity = quantity;
    }

    public String getName (){
        return prodName;
    }

    public int getQuantity () {
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
}
