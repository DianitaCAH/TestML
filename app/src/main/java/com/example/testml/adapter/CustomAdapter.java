package com.example.testml.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testml.ProductDetailActivity;
import com.example.testml.R;
import com.example.testml.databinding.ProductBinding;
import com.example.testml.presenter.ProductPresenter;
import com.example.testml.viewmodel.ProductViewModel;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Map;

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
            ImageView image = productBinding.productImage;

            new DownloadBackground(productBinding.relativeImage,
                    productBinding.getRoot().getRootView(),
                    productViewModel.getImgUrl()).execute();
        }

        public ProductBinding getProductBinding() {
            return productBinding;
        }
    }

    private class DownloadBackground extends AsyncTask {

        RelativeLayout fondo;
        String url;
        View rootView;

        public DownloadBackground(RelativeLayout fondo, View rootView, String url) {
            this.fondo = fondo;
            this.rootView = rootView;
            this.url = url;
        }

        @Override
        protected Object doInBackground(Object[] params) {
            try{

                return downloadImage(url);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            if (o != null) {
                Drawable image = new BitmapDrawable(context.getResources(), (Bitmap) o);
                fondo.setBackground(image);
                Log.e("Fondo", "NOT NULL");

            } else {
                Log.e("Fondo", "NUUULL");
            }
        }

        // Creates Bitmap from InputStream and returns it
        private Bitmap downloadImage(String url) {
            Bitmap bitmap = null;
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inSampleSize = 1;

            try {
                InputStream stream = getHttpConnection(url);
                bitmap = BitmapFactory.decodeStream(stream, null, bmOptions);
                stream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            Log.e("IMAGEN ", String.valueOf(bitmap));
            return bitmap;
        }

        // Makes HttpURLConnection and returns InputStream
        private InputStream getHttpConnection(String urlString)
                throws IOException {
            InputStream stream = null;
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();

            try {
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setRequestMethod("GET");
                httpConnection.connect();

                if ( httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    stream = httpConnection.getInputStream();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return stream;
        }

    }

}
