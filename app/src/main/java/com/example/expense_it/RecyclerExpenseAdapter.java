package com.example.expense_it;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

        holder.llrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.addexpensedialogbox);

                TextView txtTitle = dialog.findViewById(R.id.txtTitle);
                EditText expenseadd = dialog.findViewById(R.id.expenseadd);
                EditText amountadd = dialog.findViewById(R.id.anountadd);
                Button btnAdd = dialog.findViewById(R.id.btnAdd);

                txtTitle.setText("Update Expense");
                btnAdd.setText("Update");

                expenseadd.setText(expenselist.get(position).expensename);
                amountadd.setText(expenselist.get(position).expenseamount);

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String expense = "" , money = "";

                        if(!expenseadd.getText().toString().equals("")){
                            expense = expenseadd.getText().toString();
                        }
                        else{
                            Toast.makeText(context, "Enter the expense name", Toast.LENGTH_SHORT).show();
                        }

                        if(!amountadd.getText().toString().equals("")){
                            money = amountadd.getText().toString();
                        }
                        else{
                            Toast.makeText(context, "Enter amount", Toast.LENGTH_SHORT).show();
                        }

                        expenselist.set(position, new ExpenseModel(expense,money));
                        notifyItemChanged(position);
                        dialog.dismiss();

                    }
                });
                dialog.show();
            }
        });

        holder.llrow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Delete Contact")
                        .setMessage("Are you sure you want to delete?")
                        .setIcon(R.drawable.baseline_delete_24)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                expenselist.remove(position);
                                notifyItemChanged(position);

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                builder.show();


                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return expenselist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView expensename , expenseamount;
        LinearLayout llrow;

        public ViewHolder(View itemview){
            super(itemview);

            llrow = itemview.findViewById(R.id.llrow);
            expensename = itemview.findViewById(R.id.expensename);
            expenseamount = itemview.findViewById(R.id.expenseamount);
        }
    }


}
