package com.vlgo.vlgo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.vlgo.vlgo.R;
import com.vlgo.vlgo.component.Entity;

import java.util.List;

public class AdapterEntities extends RecyclerView.Adapter<AdapterEntities.ViewHolder> {


    private List<Entity> entities;
    private Context context;
    private SelectItem selectItem;
    private LayoutInflater layoutInflater;

    public AdapterEntities(List<Entity> entities, Context context, SelectItem selectItem) {
        this.entities = entities;
        this.context = context;
        this.selectItem = selectItem;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.entity_element, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final Entity current = entities.get(position);

        holder.tvName.setText(current.getName());

        Picasso.with(context).load(current.getImagesIds()[0]).fit().centerCrop().into(holder.imgImage);
        holder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, current.getName(), Toast.LENGTH_SHORT).show();
                selectItem.more(position);
            }
        });

        holder.btnWay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, current.getName(), Toast.LENGTH_SHORT).show();
                selectItem.makeWay(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return entities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvName;
        private ImageView imgImage;
        private Button btnMore, btnWay;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvElementname);
            imgImage = itemView.findViewById(R.id.imvListElement);
            btnMore = itemView.findViewById(R.id.btnElementMore);
            btnWay = itemView.findViewById(R.id.btnElementMakeWay);
        }
    }

    public interface SelectItem{
        void more(int index);
        void makeWay(int index);
    }
}
