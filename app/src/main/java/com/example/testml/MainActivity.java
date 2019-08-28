package com.example.testml;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

import com.example.testml.Data.DataManager;
import com.example.testml.Presenter.ProductPresenter;
import com.example.testml.ViewModel.ListViewModel;
import com.example.testml.ViewModel.ProductViewModel;
import com.example.testml.databinding.ActivityMainBinding;
import com.example.testml.databinding.ListViewBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList <ProductViewModel> productList;
    private ActivityMainBinding activityMainBinding;
    private DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.productList);

        dataManager = new DataManager(this);
        final EditText editSearcher = (EditText) findViewById(R.id.Searcher);

        //Consultar API para traer Productos
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setListModel(new ProductPresenter() {
            @Override
            public void searchProducts() {
                String searcher = editSearcher.getText().toString();
                String urlRequest = getString(R.string.url_search_items) + searcher;
                Log.e("valueSearch", searcher);
                dataManager.sendRequest(urlRequest);
            }

            @Override
            public void getProduct() {

            }
        });
    }


}
