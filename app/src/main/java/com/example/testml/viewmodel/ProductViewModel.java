package com.example.testml.viewmodel;

import androidx.databinding.BaseObservable;

import com.example.testml.R;

public class ProductViewModel extends BaseObservable {

    public int id;
    public int price;
    public String searcher;
    public String name;
    public String currency;
    public String imgUrl;

    //public MutableLiveData<String> search;


    public ProductViewModel(int id, String name, String currency, int price) {
        this.id = id;
        this.name = name;
        this.currency = currency;
        this.price = price;
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
