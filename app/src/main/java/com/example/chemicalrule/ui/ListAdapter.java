package com.example.chemicalrule.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chemicalrule.R;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<ListElement> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapter(List<ListElement> itemList, Context context){
        this.mInflater =LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.list_item,null);
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder,final int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<ListElement> items){ mData = items;}

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageCard;
        TextView name,address,review;

        ViewHolder(View itemView){
            super(itemView);
            imageCard = itemView.findViewById(R.id.imageCard);
            name = itemView.findViewById(R.id.titleCard);
            address = itemView.findViewById(R.id.addressCard);
            review = itemView.findViewById(R.id.review);
        }

        void bindData(final ListElement item){

            name.setText(item.getName());
            address.setText(item.getAddress());
            review.setText(item.getReview());
        }
    }

}
