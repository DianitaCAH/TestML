package com.example.testml;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.example.testml.databinding.ActivityProductDetailBinding;
import com.example.testml.presenter.ProductPresenter;
import com.example.testml.remote.Data.DataManager;
import com.example.testml.remote.Data.DataValues;
import com.example.testml.viewmodel.ProductViewModel;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class ProductDetailActivity extends AppCompatActivity {

    private DataManager dataManager;
    private String productId;
    private ActivityProductDetailBinding productDetailBinding;

    private String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
                            ProductViewModel productVM = new ProductViewModel();
                            productVM.setId(response.getString("id"));
                            productVM.setName(response.getString("title"));
                            productVM.setCurrency(response.getString("currency_id"));
                            productVM.setPrice(response.getString("price"));
                            productVM.setQuantity(response.getString("available_quantity"));
                            productDetailBinding.setProduct(productVM);

                            ImageView image = productDetailBinding.relativeImage;
                            Picasso picasso = Picasso.get();
                            picasso.load(response.getString("thumbnail")).into(image);

                            link = response.getString("permalink");
                            productDetailBinding.productURL.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent viewIntent =
                                            new Intent("android.intent.action.VIEW",
                                                    Uri.parse(link));
                                    startActivity(viewIntent);

                                }
                            });
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
        new Thread(new Task()).start();
    }

    class Task implements Runnable {
        @Override
        public void run() {
            productDetailBinding.getRequest().getProduct();

        }
    }

}
