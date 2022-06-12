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
import com.example.harshil.expensetracking.model.Income;

import java.util.ArrayList;
import java.util.List;

public class RecyclerIncomeAdapter extends RecyclerView.Adapter<RecyclerIncomeAdapter.incomeViewHolder> {

    List<Income> incomeList;
    Income income;
    Context context;
    public  RecyclerIncomeAdapter(Context context,ArrayList<Income> incomeList){
        this.context=context;
        this.incomeList=incomeList ;

    }

    @NonNull
    @Override
    public incomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_incomelist,parent,false);
        return new incomeViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final incomeViewHolder holder, int position) {

        income = incomeList.get(position);
        holder.incomeDate.setText(income.getDate().toString());
        holder.incomeValue.setText(income.getValues());
        holder.incomeType.setText(income.getCategory());
        holder.odometer.setText(income.getOdometer());
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
                                Intent intent = new Intent(context,com.example.harshil.expensetracking.view.income_view.class);
                                intent.putExtra("date",income.getDate());
                                intent.putExtra("Category",income.getCategory());
                                intent.putExtra("odometer",income.getOdometer());
                                intent.putExtra("value",income.getValues());
                                context.startActivity(intent);
                                break;
                            case R.id.Delete:
                                int position = (int) holder.getAdapterPosition();
                                DatabaseHelper helper = new DatabaseHelper(context);
                                SQLiteDatabase database = helper.getWritableDatabase();
                                if (helper.deleteIncome(Integer.parseInt(income.getOdometer()),Integer.parseInt(income.getValues()))){
                                    incomeList.remove(position);
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
        return incomeList.size();
    }

    class incomeViewHolder extends RecyclerView.ViewHolder {

        TextView incomeDate,incomeValue,incomeType,odometer;
        ImageView incomeIcon,optionIcon;
        CardView cardView;
        public incomeViewHolder(View itemView) {
            super(itemView);

            incomeDate = itemView.findViewById(R.id.fincomeDate);
            incomeValue = itemView.findViewById(R.id.incomevalue);
            incomeType = (TextView) itemView.findViewById(R.id.incometype);
            odometer = (TextView) itemView.findViewById(R.id.Odometer);
            incomeIcon = (ImageView) itemView.findViewById(R.id.incomeicon);
            cardView = (CardView) itemView.findViewById(R.id.incomecard);
            optionIcon = (ImageView) itemView.findViewById(R.id.optioniconincome);

        }


    }
}
