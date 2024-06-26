package com.example.expense_it;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<ExpenseModel> expenselist = new ArrayList<>();
    FloatingActionButton btnOpenDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        RecyclerView recyclerView = findViewById(R.id.recyclerexpense);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        expenselist.add(new ExpenseModel("Grocery Items","+90Rs"));

        RecyclerExpenseAdapter adapter = new RecyclerExpenseAdapter(this,expenselist);
        recyclerView.setAdapter(adapter);

        btnOpenDialog = findViewById(R.id.btnOpenDialog);

        btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.addexpensedialogbox);

                EditText expenseadd = findViewById(R.id.expenseadd);
                EditText amountadd = findViewById(R.id.anountadd);
                Button btnAdd = findViewById(R.id.btnAdd);

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String expense = "" , money = "";

                        if(!expenseadd.getText().toString().equals("")){
                            expense = expenseadd.getText().toString();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Enter the expense name", Toast.LENGTH_SHORT).show();
                        }

                        if(!amountadd.getText().toString().equals("")){
                            money = amountadd.getText().toString();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Enter amount", Toast.LENGTH_SHORT).show();
                        }

                        expenselist.add(new ExpenseModel(expense,money));
                        adapter.notifyItemInserted(expenselist.size()-1);
                        recyclerView.scrollToPosition(expenselist.size()-1);

                        dialog.dismiss();

                    }
                });
                dialog.show();
            }
        });

    }
}