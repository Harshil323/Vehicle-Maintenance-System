package com.example.harshil.expensetracking.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.harshil.expensetracking.Database.DatabaseHelper;
import com.example.harshil.expensetracking.R;
import com.example.harshil.expensetracking.model.Service;

import java.util.List;

public class RecyclerServiceAdapter extends RecyclerView.Adapter<RecyclerServiceAdapter.serviceHolder> {

    List<Service> serviceList;
    Service service;
    Context context;

    public RecyclerServiceAdapter(List<Service> serviceList,Context context){
        super();
        this.serviceList=serviceList;
        this.context=context;
    }

    @NonNull
    @Override
    public serviceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_list_service, parent, false);
        return new serviceHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final serviceHolder holder, int position) {

        service = serviceList.get(position);
        holder.category.setText(service.getCategory());
        holder.value.setText(service.getValue());
        holder.odometer.setText(service.getOdometer());
        holder.date.setText(service.getDate());
        holder.optionIcon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.optionIcon1);
                popupMenu.inflate(R.menu.recycleroptionmenu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId())
                        {
                            case R.id.view:
                                Intent intent = new Intent(context,com.example.harshil.expensetracking.view.service_view.class);
                                intent.putExtra("date",service.getDate());
                                intent.putExtra("Category",service.getCategory());
                                intent.putExtra("odometer",service.getOdometer());
                                intent.putExtra("reason",service.getReason());
                                intent.putExtra("value",service.getValue());
                                context.startActivity(intent);
                                break;
                            case R.id.Delete:
                                int position = (int) holder.getAdapterPosition();
                                DatabaseHelper helper = new DatabaseHelper(context);
                                SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
                                if (helper.deleteService(Integer.parseInt(service.getOdometer()),Integer.parseInt(service.getValue()))){
                                    System.out.println(service.getDate());
                                    serviceList.remove(position);
                                    notifyItemRemoved(position);
                                    Toast.makeText(context,"Delete Successful",Toast.LENGTH_SHORT).show();
                                }
                                break;
//                            case R.id.Update:
//                                Toast.makeText(context,"Update",Toast.LENGTH_SHORT).show();
//                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    class serviceHolder extends RecyclerView.ViewHolder{


        TextView date,odometer,value,category;
        ImageView icon,optionIcon1;
        CardView cardView;

        public serviceHolder(View itemView) {
            super(itemView);

            date = (TextView) itemView.findViewById(R.id.fserviceDate);
            odometer = (TextView) itemView.findViewById(R.id.serviceOdometer);
            value = (TextView) itemView.findViewById(R.id.servicevalue);
            category = (TextView) itemView.findViewById(R.id.serviceType);
            icon = (ImageView) itemView.findViewById(R.id.serviceicon);
            cardView = (CardView) itemView.findViewById(R.id.servicecard);
            optionIcon1 = (ImageView) itemView.findViewById(R.id.optionmenuservice);


        }



    }
}
