package com.example.testml.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testml.databinding.ProductBinding;
import com.example.testml.viewmodel.ProductViewModel;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomView> {

    private ArrayList<ProductViewModel> productList;
    private LayoutInflater layoutInflater;

    public CustomAdapter(ArrayList<ProductViewModel> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public CustomView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        ProductBinding productBinding = ProductBinding.inflate(layoutInflater, parent, false);

        return new CustomView(productBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomView holder, int position) {

        ProductViewModel productViewModel = productList.get(position);
        holder.bind(productViewModel);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    public class CustomView extends RecyclerView.ViewHolder{

        private ProductBinding productBinding;

        public CustomView(ProductBinding productBinding) {
            super(productBinding.getRoot());

            this.productBinding = productBinding;
        }

        public void bind(ProductViewModel productViewModel) {
            this.productBinding.setNewmodel(productViewModel);
            this.productBinding.executePendingBindings();
        }

        public ProductBinding getProductBinding() {
            return productBinding;
        }
    }
    /*@Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        *//*ListRowBindin item = DataBindingUtil.inflate(inflater, R.layout.item_layout, viewGroup, false);

        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_layout, null);

            item = DataBindingUtil.bind(view);
            view.setTag(item);

        } else {
            item = (ListViewBinding)view.getTag();
        }

        item.setNewmodel(productList.get(position));

        return item.getRoot()*//*;
        return null;
    }*/

}
