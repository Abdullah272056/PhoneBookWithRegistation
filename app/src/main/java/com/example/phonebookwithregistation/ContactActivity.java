package com.example.phonebookwithregistation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ContactActivity extends AppCompatActivity {
    DataBaseHelper dataBaseHelper;
    FloatingActionButton addButton;
    Context context;
    RecyclerView recyclerView;
    CustomAdapter adapter;
    List<Notes> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        context=ContactActivity.this;

        recyclerView=findViewById(R.id.recyclerViewId);
        dataBaseHelper=new DataBaseHelper(ContactActivity.this);
        dataBaseHelper.getWritableDatabase();

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        loadData();

        addButton   =   findViewById(R.id.addButtonId);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Customialog();
            }
        });

    }

    private void loadData() {
        dataList  = new ArrayList<>();
        dataList = dataBaseHelper.getAllNotes();
        if (dataList.size() > 0){
            adapter = new CustomAdapter(context,dataList);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }else {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }
    }



    private void Customialog(){

        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        LayoutInflater layoutInflater =LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.custom_dialog,null);
        builder.setView(view);

        final AlertDialog alertDialog = builder.create();

        Button saveButton=view.findViewById(R.id.saveButtonId);
        final EditText nameEditText =view.findViewById(R.id.nameEditTextId);
        final EditText locationEditText =view.findViewById(R.id.locationEditTextId);
        final EditText phoneNumberEditText =view.findViewById(R.id.phoneEditTextId);





        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=nameEditText.getText().toString();
                String location=locationEditText.getText().toString();
                String phoneNumber=phoneNumberEditText.getText().toString();
                if (name.isEmpty()){
                    nameEditText.setError("Enter title");
                    return;
                }
                else if (location.isEmpty()){
                    locationEditText.setError("Enter title");
                    return;
                }
                else if (phoneNumber.isEmpty()){
                    phoneNumberEditText.setError("Enter title");
                    return;
                }

                long id=dataBaseHelper.insertData(new Notes(name,location,phoneNumber));


                if (id != -1){
                    alertDialog.dismiss();
                    loadData();
                    Toast.makeText(context, "Successfully Inserted", Toast.LENGTH_SHORT).show();
                }else {
                    alertDialog.dismiss();
                    Toast.makeText(context, "Failed to Insert", Toast.LENGTH_SHORT).show();
                }

                //  Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
            }
        });

        alertDialog.show();
    }

}
