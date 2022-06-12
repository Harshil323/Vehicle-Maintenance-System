package com.example.harshil.expensetracking.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
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
import com.example.harshil.expensetracking.model.Fuel;

import java.util.List;

public class RecyclerFuelAdapter extends RecyclerView.Adapter<RecyclerFuelAdapter.fuelHolder> {


    List<Fuel> fuelList;
    Fuel fuel;
    Context context;
    public RecyclerFuelAdapter(Context context,List<Fuel> fuelList){

        this.fuelList=fuelList;
        this.context = context;

    }
    @NonNull
    @Override
    public fuelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_list_refueling, parent, false);
        return new fuelHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final fuelHolder holder, int position) {

        fuel = fuelList.get(position);
        holder.date.setText(fuel.getDate());
        holder.cost.setText(fuel.getCost());
        holder.gasstation.setText(fuel.getGasstation());
        holder.odometer.setText(fuel.getOdometer());
//        System.out.println(fuel.getCost());
//        System.out.println(fuel.getLiters());
        System.out.println(fuel.getGasstation());
        holder.optionIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context,holder.optionIcon);
                popupMenu.inflate(R.menu.recycleroptionmenu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.view:
                                Intent intent = new Intent(context,com.example.harshil.expensetracking.view.refueling_view.class);
                                intent.putExtra("date",fuel.getDate());
                                intent.putExtra("Category",fuel.getOdometer());
                                intent.putExtra("location",fuel.getFuelType());
                                intent.putExtra("odometer",fuel.getLiters());
                                intent.putExtra("reason",fuel.getCost());
                                intent.putExtra("value",fuel.getGasstation());
                                intent.putExtra("reason1",fuel.getReason());
                                System.out.println(fuel.getLiters());
                                context.startActivity(intent);
                                break;
                            case R.id.Delete:
                                int position = (int) holder.getAdapterPosition();
                                System.out.println(position+1);
                                DatabaseHelper helper = new DatabaseHelper(context);
                                SQLiteDatabase database = helper.getWritableDatabase();
                                if (helper.deleteFuel(Integer.parseInt(fuel.getOdometer()),Integer.parseInt(fuel.getLiters()))){
                                    fuelList.remove(position);
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
        return fuelList.size();
    }

    class fuelHolder extends RecyclerView.ViewHolder {

        TextView date,cost,odometer,gasstation;
        CardView cardView;
        ImageView optionIcon;
        public fuelHolder(View itemView) {
            super(itemView);

            date = (TextView) itemView.findViewById(R.id.fuelDate);
            cost = (TextView) itemView.findViewById(R.id.cost);
            odometer = (TextView) itemView.findViewById(R.id.fuelOdometer);
            gasstation = (TextView) itemView.findViewById(R.id.gasstaion);
            cardView = (CardView) itemView.findViewById(R.id.refuelingcard);
            optionIcon = (ImageView) itemView.findViewById(R.id.optioniconrefueling);

        }


    }
}
