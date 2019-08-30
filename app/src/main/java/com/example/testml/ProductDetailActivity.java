package com.example.testml;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.VolleyError;
import com.example.testml.databinding.ActivityProductDetailBinding;
import com.example.testml.presenter.ProductPresenter;
import com.example.testml.remote.Data.DataManager;
import com.example.testml.remote.Data.DataValues;
import com.example.testml.viewmodel.ProductViewModel;

import org.json.JSONException;
import org.json.JSONObject;

public class ProductDetailActivity extends AppCompatActivity {

    private DataManager dataManager;
    private String productId;
    private ActivityProductDetailBinding productDetailBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_product_detail);

        productId = getIntent().getStringExtra("productId");

        productDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail);
        dataManager = new DataManager(this);

        productDetailBinding.setRequest(new ProductPresenter() {
            @Override
            public void searchProducts() {}

            @Override
            public void itemClick() {}

            @Override
            public void getProduct() {
                String urlRequest = getString(R.string.url_search_items) + productId;
                Log.e("URL REQUEST", urlRequest);
                dataManager.sendRequest(urlRequest, new DataValues() {
                    @Override
                    public void setJsonDataResponse(JSONObject response) {
                        try {
                            Log.e("setRequest", response.toString());
                            ProductViewModel product = new ProductViewModel();
                            product.setId(response.getString("id"));
                            product.setName(response.getString("title"));
                            product.setCurrency(response.getString("currency"));
                            product.setPrice(response.getString("price"));
                            product.setQuantity(response.getString("available_quantity"));
                            productDetailBinding.setProduct(product);
                        } catch (JSONException e) {
                            Log.e("JsonError", e.getMessage().toString());
                        }
                    }

                    @Override
                    public void setVolleyError(VolleyError volleyError) {
                        Log.e("JsonError", volleyError.getMessage().toString());
                    }
                });
            }
        });
    }



}
