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
import com.example.harshil.expensetracking.model.Reminder;

import java.util.ArrayList;
import java.util.List;

public class RecyclerReminderAdapter extends RecyclerView.Adapter<RecyclerReminderAdapter.reminderHolder> {


    List<Reminder> reminderList;
    Reminder reminder;
    Context context;
    public RecyclerReminderAdapter(Context context,ArrayList<Reminder> arrayList) {
        this.reminderList=arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public reminderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_list_reminder, parent, false);
        return new reminderHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final reminderHolder holder, int position) {

        reminder = reminderList.get(position);
        holder.category.setText(reminder.getCategory());
        holder.date.setText(reminder.getDate());
//        System.out.println(reminder.getID());
        System.out.println(reminder.getRepeat());
        System.out.println(reminder.getCategory());
        System.out.println(reminder.getReminderfor());
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
                                Intent intent = new Intent(context,com.example.harshil.expensetracking.view.reminder_view.class);
                                intent.putExtra("date",reminder.getReminderfor());
                                intent.putExtra("Category",reminder.getCategory());
                                intent.putExtra("location",reminder.getRepeat());
                                intent.putExtra("odometer",reminder.getDate());
                                context.startActivity(intent);
                                break;
                            case R.id.Delete:
                                int position = (int) holder.getAdapterPosition();
                                System.out.println(position+1);
                                DatabaseHelper helper = new DatabaseHelper(context);
                                SQLiteDatabase database = helper.getWritableDatabase();
                                if (helper.deleteReminder(reminder.getCategory(),reminder.getRepeat())){
                                    reminderList.remove(position);
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
        return reminderList.size();
    }

    class reminderHolder extends RecyclerView.ViewHolder {
        TextView date,category;
        ImageView icon,optionIcon;
        CardView cardView;
        public reminderHolder(View itemView) {
            super(itemView);

            date = (TextView) itemView.findViewById(R.id.freminderDate);
            category = (TextView) itemView.findViewById(R.id.reminderCategoty);
            icon = (ImageView) itemView.findViewById(R.id.remindericon);
            cardView = (CardView) itemView.findViewById(R.id.remindercard);
            optionIcon = (ImageView) itemView.findViewById(R.id.optioniconreminder);
        }


    }
}
