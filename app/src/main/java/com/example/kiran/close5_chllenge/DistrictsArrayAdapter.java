package com.example.kiran.close5_chllenge;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.kiran.close5_chllenge.model.Districts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sambu on 10/19/16.
 */

public class DistrictsArrayAdapter extends ArrayAdapter<Districts> {

    private Context context;
    @SuppressWarnings("unused")
    private ArrayList<Districts> districtsList;

    public DistrictsArrayAdapter(Context context, int resource, ArrayList<Districts> objects) {
        super(context, resource, objects);
        this.context = context;
        this.districtsList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_district, parent, false);
        Districts district = districtsList.get(position);

        TextView addressTv = (TextView) view.findViewById(R.id.address);
        addressTv.setText(district.getAddress());

        TextView categoryTv = (TextView) view.findViewById(R.id.category);
        categoryTv.setText(district.getCategory());

        TextView dateTv = (TextView) view.findViewById(R.id.date);
        dateTv.setText(district.getDate());

        return view;
    }
}
