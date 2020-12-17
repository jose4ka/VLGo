package com.vlgo.vlgo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.vlgo.vlgo.R;

import java.util.List;

public class AdapterImages extends RecyclerView.Adapter<AdapterImages.ViewHolder> {


    private int[] images;
    private LayoutInflater layoutInflater;
    private Context context;

    public AdapterImages(int[] images, Context context) {
        this.images = images;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.element_image, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.with(context).load(images[position]).fit().centerCrop().into(holder.imgImage);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgImage = itemView.findViewById(R.id.imgElement);
        }
    }
}
