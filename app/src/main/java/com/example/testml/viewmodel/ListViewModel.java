package com.example.testml.viewmodel;

import com.example.testml.model.Product;

public class ListViewModel {

    public int price;
    public String name;
    public String description;

    public ListViewModel(Product product) {
        this.name = product.getName();
        this.description = product.getDescripcion();
        this.price = product.getPrice();
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
