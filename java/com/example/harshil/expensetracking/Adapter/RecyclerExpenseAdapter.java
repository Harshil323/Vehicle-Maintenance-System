package com.example.harshil.expensetracking.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.harshil.expensetracking.Database.DatabaseHelper;
import com.example.harshil.expensetracking.R;
import com.example.harshil.expensetracking.model.Expense;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RecyclerExpenseAdapter extends RecyclerView.Adapter<RecyclerExpenseAdapter.expenseHolder> {

    List<Expense> expenseList;
    Expense expense;
    Context context;

    public RecyclerExpenseAdapter(List<Expense> expenseList,Context context){

        this.expenseList=expenseList;
        this.context=context;

    }

    @NonNull
    @Override
    public expenseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_expenselist, parent, false);

        return new expenseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final expenseHolder holder, int position) {

        expense = expenseList.get(position);
        holder.date.setText(expense.getDate());
        holder.location.setText(expense.getLocation());
        holder.type.setText(expense.getCategory());
        holder.value.setText(expense.getValue());
//        Calendar c = Calendar.getInstance();
//        SimpleDateFormat format = new SimpleDateFormat("dd / MM / yyyy ");
//        String date = format.format(c.getTime());
//        System.out.println(date);

        System.out.println(expense.getID());
        System.out.println(expense.getDate());
        System.out.println(expense.getCategory());
        System.out.println(expense.getLocation());
        System.out.println(expense.getOdometer());
        System.out.println(expense.getReason());
        System.out.println(expense.getValue());

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

                                Intent intent = new Intent(context,com.example.harshil.expensetracking.view.expense_view.class);
                                intent.putExtra("date",expense.getDate());
                                intent.putExtra("Category",expense.getCategory());
                                intent.putExtra("location",expense.getLocation());
                                intent.putExtra("odometer",expense.getOdometer());
                                intent.putExtra("reason",expense.getReason());
                                intent.putExtra("value",expense.getValue());
                                context.startActivity(intent);
                                break;
                            case R.id.Delete:
                                int position = holder.getAdapterPosition();
                                System.out.println(position+1);
                                DatabaseHelper helper = new DatabaseHelper(context);
                                SQLiteDatabase database = helper.getWritableDatabase();
                                if (helper.deleteExpense(Integer.parseInt(expense.getOdometer()),Integer.parseInt(expense.getValue()))){
                                    expenseList.remove(position);
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
        return expenseList.size();
    }

    class expenseHolder extends RecyclerView.ViewHolder {
        TextView date,value,type,location;
        CardView cardView;
        ImageView optionIcon;

        public expenseHolder(View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.fexpenseDate);
            value = itemView.findViewById(R.id.expensevalue);
            type = itemView.findViewById(R.id.expensetype);
            location = itemView.findViewById(R.id.location);
            cardView = itemView.findViewById(R.id.incomecard);
            optionIcon = itemView.findViewById(R.id.optioniconexpense);
        }



    }
}
