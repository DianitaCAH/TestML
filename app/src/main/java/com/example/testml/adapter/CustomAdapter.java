package com.example.testml.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testml.ProductDetailActivity;
import com.example.testml.databinding.ProductBinding;
import com.example.testml.presenter.ProductPresenter;
import com.example.testml.viewmodel.ProductViewModel;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomView> {

    private ArrayList<ProductViewModel> productList;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomAdapter(Context context,ArrayList<ProductViewModel> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public CustomView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        final ProductBinding productBinding = ProductBinding.inflate(layoutInflater, parent, false);

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

            this.productBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ProductDetailActivity.class);
                    intent.putExtra("productId", productBinding.productID.getText());
                    context.startActivity(intent);
                    Log.e("CustomAdapter", "GOING TO PRODUCT DETAIL!!!");
                }
            });
        }

        public ProductBinding getProductBinding() {
            return productBinding;
        }
    }

}
