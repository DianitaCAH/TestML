package com.example.testml;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.android.volley.VolleyError;
import com.example.testml.databinding.ActivityProductDetailBinding;
import com.example.testml.presenter.ProductPresenter;
import com.example.testml.remote.Data.DataManager;
import com.example.testml.remote.Data.DataValues;
import com.example.testml.viewmodel.ProductViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

public class ProductDetailActivity extends AppCompatActivity {

    private DataManager dataManager;
    private String productId;
    private ActivityProductDetailBinding productDetailBinding;

    private String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_product_detail);

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

                            /*productDetailBinding.productID.setText(response.getString("id"));
                            productDetailBinding.productName.setText(response.getString("title"));
                            productDetailBinding.productPrice.setText(response.getString("currency_id") + response.getString("price"));
                            productDetailBinding.productQuantity.setText(response.getString("available_quantity"));*/
                            new DownloadBackground(productDetailBinding.relativeImage,
                                    productDetailBinding.getRoot().getRootView(), response.getString("thumbnail")).execute();

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
        Log.e("callApiProduct", "sdfghjk");
        new Thread(new Task()).start();
    }

    class Task implements Runnable {
        @Override
        public void run() {
            Log.e("Task", "RUN");
            productDetailBinding.getRequest().getProduct();

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
                Drawable image = new BitmapDrawable(getResources(), (Bitmap) o);
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
