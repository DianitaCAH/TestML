package com.example.testml.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.databinding.DataBindingUtil;

import com.example.testml.R;
import com.example.testml.databinding.ListRowBinging;
import com.example.testml.viewmodel.ProductViewModel;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ProductViewModel> productList;

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

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        /*ListRowBindin item = DataBindingUtil.inflate(inflater, R.layout.item_layout, viewGroup, false);

        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_layout, null);

            item = DataBindingUtil.bind(view);
            view.setTag(item);

        } else {
            item = (ListViewBinding)view.getTag();
        }

        item.setNewmodel(productList.get(position));

        return item.getRoot()*/;
        return null;
    }

}
