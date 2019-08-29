package com.testexercise.hauteloockcdn.Main;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;
import com.testexercise.hauteloockcdn.R;
import com.testexercise.hauteloockcdn.adapter.CustomListAdapter;
import com.testexercise.hauteloockcdn.app.AppController;
import com.testexercise.hauteloockcdn.model.Products;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import com.testexercise.hauteloockcdn.model.Products;


public class Article extends Activity {
    private static final String TAG = Article.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article);

        Intent i = getIntent();
        Products prod = (Products)i.getSerializableExtra("ARTICLE");

        ImageView img = (ImageView)findViewById(R.id.image_prod);
        TextView division = (TextView) findViewById(R.id.division);
        TextView department = (TextView) findViewById(R.id.department);
        TextView name = (TextView) findViewById(R.id.name);
        TextView brand = (TextView) findViewById(R.id.brand);
        Picasso.get().load(prod.getImages()).into(img);
        division.setText(prod.getDivision());
        department.setText(prod.getDepartment());
        name.setText(prod.getName());
        brand.setText(prod.getBrand());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}