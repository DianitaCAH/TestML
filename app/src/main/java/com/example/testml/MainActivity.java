package com.example.testml;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;


import com.example.testml.adapter.CustomAdapter;
import com.example.testml.model.Product;
import com.example.testml.remote.Data.DataManager;
import com.example.testml.presenter.ProductPresenter;
import com.example.testml.viewmodel.ProductViewModel;
import com.example.testml.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList <ProductViewModel> productList;
    private ActivityMainBinding activityMainBinding;
    private ProductViewModel productViewModel;
    private CustomAdapter adapter;
    private DataManager dataManager;
//------------------------------------------
    private RecyclerView recyclerView;
    private ArrayList<Product> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        //Binding layout to activity
        //activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_recycler);

        recyclerView = findViewById(R.id.Recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this ));

        productList = new ArrayList<>();
        adapter = new CustomAdapter(productList);
        recyclerView.setAdapter(adapter);
        setData();

        /*activityMainBinding.setSearchClick(new ProductPresenter() {
            @Override
            public void searchProducts() {
                toastData(activityMainBinding.getProducViewModel().getSearcher());
            }

            @Override
            public void getProduct() {

            }
        });
*/
        //dataManager = new DataManager(this);

       // productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);


        /*/Calling API to get products
        activityMainBinding.setProductPresenter (new ProductPresenter() {
            @Override
            public void searchProducts() {
                String searcher = activityMainBinding.Searcher.getText().toString();
                String urlRequest = getString(R.string.url_search_items) + searcher;
                Log.e("valueSearch", searcher);
                dataManager.sendRequest(urlRequest, new DataValues() {
                    @Override
                    public void setJsonDataResponse(JSONObject response) {

                        productList = new ArrayList<ProductViewModel>();
                        try {
                            JSONArray listProduct = response.getJSONArray("results");
                            if (listProduct.length() > 0) {
                                for (int i=0; i < listProduct.length(); i++) {
                                    JSONObject product = listProduct.getJSONObject(i);
                                    productViewModel.setId(product.getInt("id"));
                                    productViewModel.setName(product.getString("title"));
                                    productViewModel.setImgUrl(product.getString("thumbnail"));
                                    productViewModel.setCurrency(product.getString("currency_id"));
                                    productViewModel.setPrice(product.getInt("price"));

                                    productList.add(productViewModel);
                                }
                                adapter = new CustomAdapter(MainActivity.this, productList);
                                activityMainBinding.productList.setVisibility(View.VISIBLE);
                                activityMainBinding.productList.setAdapter(adapter);
                            }
                        } catch (JSONException e){
                            Log.e("JsonError", e.getMessage().toString());
                        }
                    }

                    @Override
                    public void setVolleyError(VolleyError volleyError) {

                    }
                });
            }

            @Override
            public void getProduct() {

            }
        });*/
    }

    void setData() {
        ProductViewModel prod1 = new ProductViewModel();
        prod1.setName("111111");
        prod1.setCurrency("USD");
        prod1.setImgUrl("wwwasbdajsbd");
        productList.add(prod1);

        ProductViewModel prod5 = new ProductViewModel();
        prod5.setName("222222");
        prod5.setCurrency("USD");
        prod5.setImgUrl("wwwasbdajsbd");
        productList.add(prod5);

        ProductViewModel prod2 = new ProductViewModel();
        prod2.setName("33333");
        prod2.setCurrency("USD");
        prod2.setImgUrl("wwwasbdajsbd");
        productList.add(prod2);

        ProductViewModel prod3 = new ProductViewModel();
        prod3.setName("4444");
        prod3.setCurrency("USD");
        prod3.setImgUrl("wwwasbdajsbd");
        productList.add(prod3);
    }

    void toastData(String data){
        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
    }


}
