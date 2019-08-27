package com.example.testml.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.databinding.DataBindingUtil;

import com.example.testml.R;
import com.example.testml.ViewModel.ProductViewModel;
import com.example.testml.databinding.ListViewBinding;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ProductViewModel> productList;
    private ListViewBinding listViewBinding;

    public CustomAdapter(Context context, ArrayList<ProductViewModel> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_layout, null);

            listViewBinding = DataBindingUtil.bind(view);
            view.setTag(listViewBinding);

        } else {
            listViewBinding = (ListViewBinding)view.getTag();
        }

        listViewBinding.setNewmodel(productList.get(position));

        return listViewBinding.getRoot();
    }

}
