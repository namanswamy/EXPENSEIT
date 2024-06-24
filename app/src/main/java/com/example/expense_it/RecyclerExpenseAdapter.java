package com.example.expense_it;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerExpenseAdapter extends RecyclerView.Adapter<RecyclerExpenseAdapter.ViewHolder> {

    Context context;
    ArrayList<ExpenseModel> expenselist;

    RecyclerExpenseAdapter(Context context, ArrayList<ExpenseModel> expenselist){
        this.context=context;
        this.expenselist = expenselist;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.expense_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.expensename.setText(expenselist.get(position).expensename);
        holder.expenseamount.setText(expenselist.get(position).expenseamount);

    }

    @Override
    public int getItemCount() {
        return expenselist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView expensename , expenseamount;

        public ViewHolder(View itemview){
            super(itemview);

            expensename = itemview.findViewById(R.id.expensename);
            expenseamount = itemview.findViewById(R.id.expenseamount);
        }
    }


}
