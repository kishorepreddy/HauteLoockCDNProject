package com.testexercise.hauteloockcdn.adapter;

import com.testexercise.hauteloockcdn.app.AppController;
import com.testexercise.hauteloockcdn.R;
import com.testexercise.hauteloockcdn.model.Products;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Products> products;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapter(Activity activity, List<Products> products) {
        this.activity = activity;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int location) {
        return products.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView division = (TextView) convertView.findViewById(R.id.division);
        TextView department = (TextView) convertView.findViewById(R.id.department);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView brand = (TextView) convertView.findViewById(R.id.brand);


        Products m = products.get(position);
        thumbNail.setImageUrl(m.getImages(), imageLoader);
        division.setText(m.getDivision());
        department.setText(m.getDepartment());
        name.setText(m.getName());
        brand.setText(m.getBrand());

        return convertView;
    }
}
