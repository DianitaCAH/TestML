package com.example.testml.viewmodel;

import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;

import com.example.testml.R;
import com.example.testml.model.Product;
import com.squareup.picasso.Picasso;

public class ProductViewModel extends BaseObservable {

    public String id;
    public String price;
    public String searcher;
    public String name;
    public String currency;
    public String imgUrl;
    public String quantity;

    //public MutableLiveData<String> search;


    public ProductViewModel() {
    }

    public ProductViewModel(Product product) {
        this.id = product.id;
        this.name = product.name;
        this.currency = product.currency;
        this.price = product.price;
        this.quantity = product.quantity;
    }

    public String getSearcher() {
        return searcher;
    }

    public void setSearcher(String searcher) {
        this.searcher = searcher;
        notifyPropertyChanged(R.id.Searcher);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }


}
