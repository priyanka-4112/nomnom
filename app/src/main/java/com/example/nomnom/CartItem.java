package com.example.nomnom;

    import java.io.Serializable;

public class CartItem implements Serializable {
    private String name;
    private int price;

    public CartItem(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
