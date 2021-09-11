package com.example.chemicalrule.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chemicalrule.R;
import com.example.chemicalrule.model.PublicationModel;

import java.io.ByteArrayOutputStream;
import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<PublicationModel> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapter(List<PublicationModel> itemList, Context context){
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
        holder.cardItem.setOnClickListener((v)->{
           Intent intent = new Intent(v.getContext(), ItemLocationActivity.class);
           intent.putExtra("title",holder.name.getText().toString());
           intent.putExtra("bImage",mData.get(position).getImage());
           intent.putExtra("address",holder.address.getText().toString());
           intent.putExtra("review",holder.review.getText().toString());
//           intent.putExtra("description",holder.description.getText().toString());
           v.getContext().startActivity(intent);
           notifyDataSetChanged();
        });
    }

    public void setItems(List<PublicationModel> items){ mData = items;}

    public  static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardItem;
        ImageView imageCard;
        TextView name,address,review,description;
        int srcImage;

        ViewHolder(View itemView){
            super(itemView);
            cardItem = itemView.findViewById(R.id.cv);
            imageCard = itemView.findViewById(R.id.imageCard);
            name = itemView.findViewById(R.id.titleCard);
            address = itemView.findViewById(R.id.addressCard);
            review = itemView.findViewById(R.id.review);
            description = itemView.findViewById(R.id.description);
            itemView.setOnClickListener((v)->{
//                Intent intent = new Intent(v.getContext(),ItemLocationActivity.class);
//                intent.putExtra("title", "saa");
//                v.getContext().startActivity(intent);
//                Toast.makeText()
            });
        }
        @SuppressLint("SetTextI18n")
        void bindData(final PublicationModel item){
            byte[] image = item.getImage();
            Bitmap bmp = BitmapFactory.decodeByteArray(image,0,image.length);
            imageCard.setImageBitmap(bmp);
            name.setText(item.getName());
//            description.setText(item.getDescription());
            address.setText(item.getLongitude()+";"+item.getLatitude());
            review.setText("Buena");
        }
    }

}
