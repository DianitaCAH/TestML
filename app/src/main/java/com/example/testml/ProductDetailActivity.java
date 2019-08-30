package com.example.testml;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;

import android.os.Bundle;
import android.os.Handler;
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

    private Handler handler;

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
                String urlRequest = getString(R.string.url_get_item) + productId;
                Log.e("URL REQUEST", urlRequest);
                dataManager.sendGETRequest(urlRequest, new DataValues() {
                    @Override
                    public void setJsonDataResponse(JSONObject response) {
                        try {
                            Log.e("setRequest", response.toString());
                            productDetailBinding.productID.setText(response.getString("id"));
                            productDetailBinding.productName.setText(response.getString("title"));
                            productDetailBinding.productPrice.setText(response.getString("currency") + response.getString("price"));
                            productDetailBinding.productQuantity.setText(response.getString("available_quantity"));

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

        callApiProduct();
    }

    public void callApiProduct() {
        Log.e("callApiProduct", "sdfghjk");
        new Thread(new Task()).start();
    }

    class Task implements Runnable {
        @Override
        public void run() {
            Log.e("Task", "RUN");
            productDetailBinding.getRequest().getProduct();

        }
    }
}
