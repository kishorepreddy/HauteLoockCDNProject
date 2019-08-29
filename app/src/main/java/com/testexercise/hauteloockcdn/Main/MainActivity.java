package com.testexercise.hauteloockcdn.Main;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.testexercise.hauteloockcdn.R;
import com.testexercise.hauteloockcdn.adapter.CustomListAdapter;
import com.testexercise.hauteloockcdn.app.AppController;
import com.testexercise.hauteloockcdn.model.Products;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class MainActivity extends Activity {
    public static final int RequestPermissionCode = 1;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String url = "https://www.hautelookcdn.com/mobile-apps/Interview/catalog.json";
    private ProgressDialog pDialog;
    private List<Products> productlist = new ArrayList<Products>();
    private ListView listView;
    private CustomListAdapter adapter;


    private void proceedAfterPermission()
    {
        if(true)
        {
            AppController.getInstance();
            listView = (ListView) findViewById(R.id.list);
            adapter = new CustomListAdapter(this, productlist);
            listView.setAdapter(adapter);
            pDialog = new ProgressDialog(this);
            pDialog.setMessage("Loading Product...");
            pDialog.show();
            JsonObjectRequest movieReq = new JsonObjectRequest(url,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response)
                        {
                            Log.e(TAG, response.toString());
                            hidePDialog();
                            for (int i = 0; i < 1; i++)
                            {
                                try
                                {
                                    JSONArray srcArry = response.getJSONArray("products");
                                    for (int j = 0; j < srcArry.length(); j++)
                                    {
                                        Products prod = new Products();
                                        Log.e("Number of Products", String.valueOf(j));
                                        JSONObject outer_object =  srcArry.getJSONObject(j);
                                        JSONObject nested_object = outer_object.getJSONObject("images");

                                        prod.setDivision(outer_object.getString("division"));
                                        prod.setDepartment(outer_object.getString("department"));
                                        prod.setBrand(outer_object.getString("brand"));
                                        prod.setName(outer_object.getString("name"));
                                        prod.setImages(nested_object.getString("thumbnail"));
                                        productlist.add(prod);
                                    }
                                }
                                catch (JSONException e)
                                {
                                    e.printStackTrace();
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                    hidePDialog();
                }
            });
            AppController.getInstance().addToRequestQueue(movieReq);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id)
                {
                    Products entry = (Products) parent.getItemAtPosition(position);
                    Intent intent = new Intent(getBaseContext(), Article.class);
                    intent.putExtra("ARTICLE", entry);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        PermissionUtil.checkPermission(MainActivity.this, Manifest.permission.INTERNET,
                new PermissionUtil.PermissionAskListener() {
                    @Override
                    public void onPermissionAsk() {
                        ActivityCompat.requestPermissions(
                                MainActivity.this,
                                new String[]{Manifest.permission.INTERNET},
                                RequestPermissionCode
                        );
                    }
                    @Override
                    public void onPermissionPreviouslyDenied() {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                        builder1.setTitle("Definition Found");
                        builder1.setMessage("Application needs to connect to internet in order to fetch GitHub Data");
                        builder1.setCancelable(true);

                        builder1.setPositiveButton(
                                "Okay",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                    }
                    @Override
                    public void onPermissionDisabled() {
                        Toast.makeText(MainActivity.this, "Permission Disabled.", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onPermissionGranted() {
                        Toast.makeText(MainActivity.this, "Permission Granted.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    @Override
    public void onStart()
    {
        super.onStart();
        proceedAfterPermission();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}