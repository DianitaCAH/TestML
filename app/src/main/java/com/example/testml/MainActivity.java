package com.example.testml;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.testml.ViewModel.ListViewModel;
import com.example.testml.ViewModel.ProductViewModel;
import com.example.testml.databinding.ListViewBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList <ProductViewModel> productList;
    private Ac listViewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.productList);

        //Consultar API para traer Productos
    }


}
