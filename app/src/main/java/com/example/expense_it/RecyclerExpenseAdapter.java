package com.example.expense_it;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerExpenseAdapter extends RecyclerView.Adapter<RecyclerExpenseAdapter.ViewHolder> {

    Context context;

    RecyclerExpenseAdapter(Context context){
        this.context=context;
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

    }

    @Override
    public int getItemCount() {
        return 0;
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
