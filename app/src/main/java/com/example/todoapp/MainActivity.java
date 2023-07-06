package com.example.todoapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText todo;
    Button add;
    ListView listView;
    ArrayList<String> todolist = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todo = findViewById(R.id.todo);
        add = findViewById(R.id.add);
        listView = findViewById(R.id.List);

        todolist = fileHelper.readData(this);

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1,todolist);

        listView.setAdapter(arrayAdapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = todo.getText().toString();
                todolist.add(itemName);
                todo.setText("");
                fileHelper.writeData(todolist,getApplicationContext());
                arrayAdapter.notifyDataSetChanged();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Delete");
                alert.setMessage("Do you want to delete item from the list");
                alert.setCancelable(false);
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        todolist.remove(position);
                        arrayAdapter.notifyDataSetChanged();
                        fileHelper.writeData(todolist,getApplicationContext());
                    }
                });

                AlertDialog alertDialog = alert.create();
                alert.show();
            }
        });
    }
}