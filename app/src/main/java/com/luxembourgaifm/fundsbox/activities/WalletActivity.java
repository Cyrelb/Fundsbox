package com.luxembourgaifm.fundsbox.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.luxembourgaifm.fundsbox.R;
import com.luxembourgaifm.fundsbox.adapters.AssetGridViewAdapter;
import com.luxembourgaifm.fundsbox.adapters.AssetListViewAdapter;
import com.luxembourgaifm.fundsbox.api.RetrofitClient;
import com.luxembourgaifm.fundsbox.model.Asset;
import com.luxembourgaifm.fundsbox.model.AssetsResponse;
import com.luxembourgaifm.fundsbox.storage.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WalletActivity extends AppCompatActivity {

    private ViewStub stubGrid;
    private ViewStub stubList;
    private ListView listView;
    private GridView gridView;
    private AssetListViewAdapter listViewAdapter;
    private AssetGridViewAdapter gridViewAdapter;
    private List<Asset> assetList;
    private int currentViewMode = 0;

    static final int VIEW_MODE_LISTVIEW = 0;
    static final int VIEW_MODE_GRIDVIEW = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        stubList = (ViewStub) findViewById(R.id.stub_list);
        stubGrid = (ViewStub) findViewById(R.id.stub_grid);
        stubList.inflate();
        stubGrid.inflate();


        listView = (ListView) findViewById(R.id.mylistview);
        gridView = (GridView) findViewById(R.id.mygridview);

        //get list of product
        getAssetsList();


        //Get current view mode in share reference
        SharedPreferences sharedPreferences = getSharedPreferences("ViewMode", MODE_PRIVATE);
        currentViewMode = sharedPreferences.getInt("currentViewMode", VIEW_MODE_LISTVIEW);//Default is view listview

        //Register item lick
        listView.setOnItemClickListener(onItemClick);
        gridView.setOnItemClickListener(onItemClick);

        switchView();

        listView.setTextFilterEnabled(false);
        gridView.setTextFilterEnabled(false);

    }

    public void getAssetsList() {

        Call<AssetsResponse> call = RetrofitClient.getmInstance().getApi().getMyInvestments2(SharedPrefManager.getInstance(this).getUser());

        call.enqueue(new Callback<AssetsResponse>() {
            @Override
            public void onResponse(Call<AssetsResponse> call, Response<AssetsResponse> response) {
                assetList = response.body().getAssets();
                setAdapters();
            }

            @Override
            public void onFailure(Call<AssetsResponse> call, Throwable t) {

            }

        });
    }
    private void setAdapters() {
        if (assetList  != null ){
            if (VIEW_MODE_LISTVIEW == currentViewMode) {
                listViewAdapter = new AssetListViewAdapter(this, R.layout.list_asset, assetList);
                listView.setAdapter(listViewAdapter);
            } else {
                gridViewAdapter = new AssetGridViewAdapter(this, R.layout.grid_asset, assetList);
                gridView.setAdapter(gridViewAdapter);
            }
        }
    }
    private void switchView() {

        if(VIEW_MODE_LISTVIEW == currentViewMode) {
            //Display listview
            stubList.setVisibility(View.VISIBLE);
            //Hide gridview
            stubGrid.setVisibility(View.GONE);
        } else {
            //Hide listview
            stubList.setVisibility(View.GONE);
            //Display gridview
            stubGrid.setVisibility(View.VISIBLE);
        }
        setAdapters();
    }

    AdapterView.OnItemClickListener onItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //Do any thing when user click to item
            //Toast.makeText(getApplicationContext(), transactionList.get(position).getUser_from() + " - " + transactionList.get(position).getUser_to(), Toast.LENGTH_SHORT).show();
        }
    };
}

