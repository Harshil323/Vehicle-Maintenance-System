package com.example.harshil.expensetracking.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.harshil.expensetracking.Database.DatabaseHelper;
import com.example.harshil.expensetracking.R;
import com.example.harshil.expensetracking.model.Route;

import java.util.ArrayList;
import java.util.List;

public class RecyclerRouteAdapter extends RecyclerView.Adapter<RecyclerRouteAdapter.routeHolder> {

    List<Route> routeList;
    Route route;
    Context context;

    public RecyclerRouteAdapter(List<Route> routeList,Context context){
        this.routeList=routeList;
        this.context=context;
    }

    @NonNull
    @Override
    public routeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_listroute_list,parent,false);
        return new  routeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final routeHolder holder, int position) {

        route = routeList.get(position);
        holder.startlocation.setText(route.getStartLocation());
        holder.endlocation.setText(route.getEndLocation());
        holder.startodomeetr.setText(route.getStartOdometer());
        holder.endodometer.setText(route.getEndOdometer());
        holder.startdate.setText(route.getStartDate());
        holder.enddate.setText(route.getEndDate());

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
                                Intent intent = new Intent(context,com.example.harshil.expensetracking.view.route_view.class);
                                intent.putExtra("date",route.getStartDate());
                                intent.putExtra("Category",route.getStartOdometer());
                                intent.putExtra("location",route.getStartLocation());
                                intent.putExtra("odometer",route.getEndOdometer());
                                intent.putExtra("reason",route.getEndDate());
                                intent.putExtra("value",route.getEndLocation());
                                intent.putExtra("value1",route.getReason());
                                context.startActivity(intent);
                                break;
                            case R.id.Delete:
                                int position = (int) holder.getAdapterPosition();
                                System.out.println(position+1);
                                DatabaseHelper helper = new DatabaseHelper(context);
                                SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
                                if (helper.deleteRoute(Integer.parseInt(route.getStartOdometer()),Integer.parseInt(route.getEndOdometer()))){
                                    System.out.println(route.getStartOdometer());
                                    System.out.println(route.getEndOdometer());
                                    routeList.remove(position);
                                    notifyItemRemoved(position);
                                    Toast.makeText(context,"Delete Successful",Toast.LENGTH_SHORT).show();
                                }
                                else {
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
    }

    @Override
    public int getItemCount() {
        return routeList.size();
    }

    public static class routeHolder extends RecyclerView.ViewHolder {

        TextView startlocation,endlocation,startodomeetr,endodometer,startdate,enddate;
        CardView cardView;
        ImageView optionIcon;
        public routeHolder(View itemView) {
            super(itemView);

            startlocation = (TextView) itemView.findViewById(R.id.start_location);
            endlocation = (TextView) itemView.findViewById(R.id.endLocationr);
            startodomeetr = (TextView) itemView.findViewById(R.id.start_odometer);
            endodometer = (TextView) itemView.findViewById(R.id.last_odometer);
            startdate = (TextView) itemView.findViewById(R.id.start_date);
            enddate = (TextView) itemView.findViewById(R.id.end_date);
            cardView = (CardView) itemView.findViewById(R.id.routeCard);
            optionIcon = (ImageView) itemView.findViewById(R.id.optionmenuroute);
        }

    }
}
