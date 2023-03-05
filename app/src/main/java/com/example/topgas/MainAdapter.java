package com.example.topgas;

import android.app.Activity;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    Activity activity;
    ArrayList<VehicleModel> arrayList;
    int index=-1;

    public MainAdapter(Activity activity, ArrayList<VehicleModel> arrayList){
        this.activity=activity;
        this.arrayList=arrayList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vehicle,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        VehicleModel model = arrayList.get(position);
        holder.brand.setText(model.getBrand());
        holder.model.setText(model.getModel());
        holder.plate_no.setText(model.getPlate_no());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView model,brand,plate_no;
        ImageView vehicle_image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            model=(TextView) itemView.findViewById(R.id.vehicle_model);
            brand=(TextView) itemView.findViewById(R.id.vehicle_brand);
            plate_no=(TextView) itemView.findViewById(R.id.vehicle_plate_no);
            vehicle_image = (ImageView) itemView.findViewById(R.id.vehicle_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(activity.getApplicationContext(),model.getText().toString(),Toast.LENGTH_LONG).show();
                }
            });
        }

        void bind(final VehicleModel vehicle){

        }
    }
}
