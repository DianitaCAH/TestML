package com.example.testml;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;


import com.android.volley.VolleyError;
import com.example.testml.adapter.CustomAdapter;
import com.example.testml.databinding.ActivityRecyclerBinding;
import com.example.testml.model.Product;
import com.example.testml.remote.Data.DataManager;
import com.example.testml.presenter.ProductPresenter;
import com.example.testml.remote.Data.DataValues;
import com.example.testml.viewmodel.ProductViewModel;
import com.example.testml.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {

    private ArrayList <ProductViewModel> productList;
    private ActivityRecyclerBinding activityRecyclerBinding;
    private ProductViewModel productViewModel;
    private CustomAdapter adapter;
    private DataManager dataManager;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_recycler);

        //Binding layout to activity
        activityRecyclerBinding = DataBindingUtil.setContentView(this, R.layout.activity_recycler);

        recyclerView = findViewById(R.id.Recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this ));

        dataManager = new DataManager(this);

        activityRecyclerBinding.setSearchClick(new ProductPresenter() {
            @Override
            public void searchProducts() {
                productViewModel = new ProductViewModel();
                productViewModel.setSearcher(activityRecyclerBinding.Searcher.getText().toString());
                if (!productViewModel.getSearcher().isEmpty()) {
                    String urlRequest = getString(R.string.url_search_items) + productViewModel.getSearcher();
                    Log.e("URL REQUEST", urlRequest);
                    dataManager.sendRequest(urlRequest, new DataValues() {
                        @Override
                        public void setJsonDataResponse(JSONObject response) {

                            productList = new ArrayList<ProductViewModel>();
                            try {
                                JSONArray listProduct = response.getJSONArray("results");
                                if (listProduct.length() > 0) {
                                    for (int i = 0; i < listProduct.length(); i++) {
                                        JSONObject product = listProduct.getJSONObject(i);
                                        ProductViewModel productVM = new ProductViewModel();
                                        productVM.setId(product.getString("id"));
                                        productVM.setName(product.getString("title"));
                                        productVM.setImgUrl(product.getString("thumbnail"));
                                        productVM.setCurrency(product.getString("currency_id"));
                                        productVM.setPrice(product.getString("price"));

                                        productList.add(productVM);
                                    }
                                    adapter = new CustomAdapter(MainActivity.this, productList);
                                    recyclerView.setAdapter(adapter);
                                    if (productList.size() > 0) {
                                        activityRecyclerBinding.Recycler.setVisibility(View.VISIBLE);
                                    } else {
                                        Toast.makeText(MainActivity.this, getResources().getString(R.string.msg_no_search_results), Toast.LENGTH_LONG).show();
                                    }
                                }
                            } catch (JSONException e) {
                                Log.e("JsonError", e.getMessage().toString());
                            }

                        }


                        @Override
                        public void setVolleyError(VolleyError volleyError) {

                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.msg_empty_field), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void itemClick() {}

            @Override
            public void getProduct() {}

        });
    }

}
