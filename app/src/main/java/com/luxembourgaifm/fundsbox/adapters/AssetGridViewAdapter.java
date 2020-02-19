package com.luxembourgaifm.fundsbox.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.luxembourgaifm.fundsbox.R;
import com.luxembourgaifm.fundsbox.model.Asset;

import java.util.List;


public class AssetGridViewAdapter extends ArrayAdapter<Asset> {
    public AssetGridViewAdapter(Context context, int resource, List<Asset> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if(null == v) {
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.grid_asset, null);
        }
        Asset asset = getItem(position);

        TextView txtTitle = (TextView) v.findViewById(R.id.txtAsset);
        TextView txtDescription = (TextView) v.findViewById(R.id.txtAmount);

        txtTitle.setText(asset.getName());
        txtDescription.setText(asset.getAmount());

        return v;
    }
}
