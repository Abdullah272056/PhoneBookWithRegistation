package com.example.phonebookwithregistation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.contactItemId:
                Intent intent=new Intent(HomeActivity.this, ContactActivity.class);
                startActivity(intent);
                return true;
            case R.id.LogoutItemId:
                Toast.makeText(getApplicationContext(),"LogoutItemId Selected",Toast.LENGTH_LONG).show();
                return true;
            default:

                return super.onOptionsItemSelected(item);
    }
}}
