package com.lab1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class FavouritePlacesActivity extends AppCompatActivity {
private RecyclerView mRecylerView;
private ArrayList<SaveLocation> mList =new ArrayList<>();
private LocationAdapter mAdapter;
private LinearLayoutManager mLinearLayoutManager;
    private DBHandler database;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_places);
        mRecylerView = findViewById(R.id.list);
        mContext = this;
        database = new DBHandler(mContext);
        getLocationList();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mList.clear();
        getLocationList();
        if (mAdapter != null){
            mAdapter.notifyDataSetChanged();
        }
    }

    public void setAdapter() {
        mAdapter = new LocationAdapter(this, mList, new OnItemClick() {
            @Override
            public void deleteAddress(SaveLocation location) {
                mList.remove(location);
                database.deleteCourse(location.getAddress());
                mAdapter.notifyDataSetChanged();
                Toast.makeText(mContext, "Deleted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void editAddress(SaveLocation editLocation) {
                Intent it = new Intent(FavouritePlacesActivity.this, UpdateAddress.class);
                it.putExtra(Constants.LOCATION_NAME, editLocation);
                startActivity(it);
            }
        });
        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecylerView.setAdapter(mAdapter);
        mRecylerView.setLayoutManager(mLinearLayoutManager);
    }
    public void getLocationList() {
       mList = database.getFavouritesLocation();
        setAdapter();
    }
}