package com.example.testml.viewmodel;

import androidx.databinding.BaseObservable;

import com.example.testml.R;
import com.example.testml.model.Product;

public class ProductViewModel extends BaseObservable {

    public int id;
    public int price;
    public String searcher;
    public String name;
    public String currency;
    public String imgUrl;

    //public MutableLiveData<String> search;


    public ProductViewModel() {
    }

    public ProductViewModel(Product product) {
        this.id = product.id;
        this.name = product.name;
        this.currency = product.currency;
        this.price = product.price;
    }

    /*public MutableLiveData getSearch(){
        if (search == null) {
            search = new MutableLiveData<>();
        }

        return search;
    }*/

    public String getSearcher() {
        return searcher;
    }

    public void setSearcher(String searcher) {
        this.searcher = searcher;
        notifyPropertyChanged(R.id.Searcher);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
