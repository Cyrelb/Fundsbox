package com.luxembourgaifm.fundsbox.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.luxembourgaifm.fundsbox.model.Asset;

import com.luxembourgaifm.fundsbox.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by NgocTri on 10/22/2016.
 */

public class AssetListViewAdapter extends ArrayAdapter<Asset> implements Filterable {

    private AssetFilter assetFilter;
    private  List<Asset> assetList;
    private  List<Asset> assetListFiltered;

    public AssetListViewAdapter(Context context, int resource, List<Asset> objects) {
        super(context, resource, objects);
        assetList = objects;
        assetListFiltered = objects;


        getFilter();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if(null == v) {
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_asset, null);
        }
        Asset asset = getItem(position);
        TextView assetTitle = (TextView) v.findViewById(R.id.txtAsset);
        TextView assetAmount = (TextView) v.findViewById(R.id.txtAmount);

        assetTitle.setText(asset.getName());
        assetAmount.setText(asset.getAmount());

        return v;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        if (assetFilter == null) {
            assetFilter = new AssetFilter();
        }

        return assetFilter;
    }

    @Override
    public int getCount() {
        return assetListFiltered.size();
    }



    @Override
    public Asset getItem(int i) {
        return assetListFiltered.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }

    public List<Asset> getAssetListFiltered() {
        return assetListFiltered;
    }

    private class AssetFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();

            if (constraint!=null && constraint.length()>0) {
                ArrayList<Asset> tempList = new ArrayList<Asset>();

                for (Asset asset : assetList) {
                    if (asset.getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        tempList.add(asset);
                    }
                }

                filterResults.count = tempList.size();
                filterResults.values = tempList;
            } else {
                filterResults.count = assetList.size();
                filterResults.values = assetList;
            }

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            assetListFiltered = (ArrayList<Asset>) results.values;
            notifyDataSetChanged();
        }
    }

}
