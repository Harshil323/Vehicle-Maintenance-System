package com.example.harshil.expensetracking.Adapter;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.harshil.expensetracking.Database.DatabaseHelper;
import com.example.harshil.expensetracking.R;
import com.example.harshil.expensetracking.Vehicle_List;
import com.example.harshil.expensetracking.model.Vehicle;

import java.util.ArrayList;

public class RecyclevehicleAdapter extends RecyclerView.Adapter<RecyclevehicleAdapter.vehicleViewHolder>
{
    ArrayList<Vehicle> vehicleList = new ArrayList<>();
    Vehicle vehicle;
    Context context;


    public RecyclevehicleAdapter(ArrayList<Vehicle> vehicleList,Context context){

        this.vehicleList= (ArrayList<Vehicle>) vehicleList;
        this.context=context;
    }

    @NonNull
    @Override
    public vehicleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_vehiclelistitem, parent, false);
        vehicleViewHolder vehicleViewHolder = new vehicleViewHolder(view);
        return vehicleViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final vehicleViewHolder holder, int position) {

        vehicle = vehicleList.get(position);

        holder.vehicleModel.setText(vehicle.getVehiclename());
        holder.vehicleManufacturer.setText(vehicle.getVehiclemanufacturer());
        holder.plateNumber.setText(vehicle.getVehiclemodel());
        holder.buyingYear.setText(vehicle.getYear());

        System.out.println(vehicle.getVehiclemodel());
        ArrayList<String> manufacture_bike=new ArrayList<String>();
        manufacture_bike.add("Honda");
        manufacture_bike.add("Bajaj");
        manufacture_bike.add("Hero");
        manufacture_bike.add("TVS motors");
        manufacture_bike.add("Yamaha");
        manufacture_bike.add("Royal Enfield");
        manufacture_bike.add("Suzuki");
        manufacture_bike.add("KTM");
        manufacture_bike.add("Ducati");
        manufacture_bike.add("Mahindra");
        manufacture_bike.add("Kawasaki");
        manufacture_bike.add("Others");

        final ArrayList<String> manufecture_car=new ArrayList<String>();
        manufecture_car.add("Hyndai");
        manufecture_car.add("Honda");
        manufecture_car.add("Maruti Suzuki");
        manufecture_car.add("TATA");
        manufecture_car.add("Ford");
        manufecture_car.add("Mahindra");
        manufecture_car.add("Skoda");
        manufecture_car.add("Audi");
        manufecture_car.add("Bentley");
        manufecture_car.add("BMW");
        manufecture_car.add("Others");

        final ArrayList<String> manufacture_truck=new ArrayList<String>();
        manufacture_truck.add("Tata Motors");
        manufacture_truck.add("Eicher Motors");
        manufacture_truck.add("Swaraj Mazda");
        manufacture_truck.add("Mahindra & Mahindra");
        manufacture_truck.add("Asia Motorworks");
        manufacture_truck.add("Hindustran Motors");
        manufacture_truck.add("Force Motors");

        final ArrayList<String> manufacture_bus=new ArrayList<String>();
        manufacture_truck.add("Tata Motors");
        manufacture_truck.add("Eicher Motors");
        manufacture_truck.add("Swaraj Mazda");
        manufacture_truck.add("Mahindra & Mahindra");
        manufacture_truck.add("Asia Motorworks");
        manufacture_truck.add("Hindustran Motors");
        manufacture_truck.add("Force Motors");

        String manufacturer = vehicle.getVehiclename();

        if (manufacture_bike.contains(manufacturer))
        {
            holder.image.setImageResource(R.drawable.bikeicon);
        }

        else if (manufecture_car.contains(manufacturer))
        {
            holder.image.setImageResource(R.drawable.ic_directions_car_black_24dp);
        }
        else if (manufacture_truck.contains(manufacturer))
        {
            holder.image.setImageResource(R.drawable.truckicon);
        }
        else if (manufacture_bus.contains(manufacturer))
        {
            holder.image.setImageResource(R.drawable.bikeicon);
        }

        System.out.println(vehicle.getID());
//        System.out.println(vehicle.getYear());
        holder.optionMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context,holder.optionMenu);
                popupMenu.inflate(R.menu.recycleroptionmenu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.view:
                                Toast.makeText(context,"View",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.Delete:
                                int position = (int) holder.getAdapterPosition();
                                DatabaseHelper helper = new DatabaseHelper(context);
                                SQLiteDatabase database = helper.getWritableDatabase();
                                if (helper.deleteVehicle(vehicle.getVehiclemodel())){
                                    vehicleList.remove(position);
                                    notifyItemRemoved(position);
                                    Toast.makeText(context,"Delete Successful",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(context,"Update",Toast.LENGTH_SHORT).show();
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


//        notifyDataSetChanged();


    }

    @Override
    public int getItemCount() {
        return vehicleList.size();
    }

    public class vehicleViewHolder extends RecyclerView.ViewHolder {

        TextView vehicleModel,vehicleManufacturer,plateNumber,buyingYear;
        ImageView image,optionMenu;
        CardView cardView;

        public vehicleViewHolder(View itemView) {
            super(itemView);

            vehicleModel = (TextView) itemView.findViewById(R.id.vehiclemodel);
            vehicleManufacturer = (TextView) itemView.findViewById(R.id.vehicleMenufacturer);
            plateNumber = (TextView) itemView.findViewById(R.id.vehiclePlateNumber);
            buyingYear = (TextView) itemView.findViewById(R.id.vehicleBuyYear);
            image = (ImageView) itemView.findViewById(R.id.vehicleTypeIcon);
            cardView = (CardView) itemView.findViewById(R.id.vehicleCard);
            optionMenu = (ImageView) itemView.findViewById(R.id.optionmenu);




        }



    }


}
