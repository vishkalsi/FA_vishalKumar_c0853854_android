package com.lab1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {
    private ArrayList<SaveLocation> mData;
    private LayoutInflater mInflater;
    private OnItemClick mClickListener;
    // data is passed into the constructor
    LocationAdapter(Context context, ArrayList<SaveLocation> data, OnItemClick mClickListener) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mClickListener = mClickListener;
    }
    @NonNull
    @Override
    public LocationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationAdapter.ViewHolder holder, int position) {
        String address = mData.get(position).getAddress();
        holder.myTextView.setText(address);
        holder.deleteAddress.setOnClickListener(v-> mClickListener.deleteAddress(mData.get(position)));
        holder.editAddress.setOnClickListener(v-> mClickListener.editAddress(mData.get(position)));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView myTextView;
        ImageView deleteAddress, editAddress;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.location_name);
            editAddress = itemView.findViewById(R.id.edit);
            deleteAddress = itemView.findViewById(R.id.delete);
//            itemView.setOnClickListener(this);
        }
    }
}
