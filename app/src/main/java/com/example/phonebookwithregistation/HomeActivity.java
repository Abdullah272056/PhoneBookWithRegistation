package com.example.phonebookwithregistation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    Context context;
    SharePref sharePref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.setTitle("Home page");

        sharePref=new SharePref();
        context =HomeActivity.this;

    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(HomeActivity.this);
        builder.setTitle("Do you want exit  app ?");
        builder.setMessage("Please press any button for action.");


        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.show();
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
                Customialog();
                return true;
            case R.id.LogoutItemId:
               sharePref.rememberData(HomeActivity.this,"","");
                Intent intent =new Intent(HomeActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:

                return super.onOptionsItemSelected(item);
    }
}


    private void Customialog(){

        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        LayoutInflater layoutInflater =LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.varify_alertdialog,null);
        builder.setView(view);

        final AlertDialog alertDialog = builder.create();

        Button okButton=view.findViewById(R.id.okButtonId);
        Button cancelButton=view.findViewById(R.id.cancelButtonId);

        final EditText passwordEditText =view.findViewById( R.id.verifyPasswordEditTextId );

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password =  passwordEditText.getText().toString();
                String savepassword = sharePref.loadPassword(context);
                if (password.isEmpty()){
                    passwordEditText.setError("enter your password");
                }
                else {
                    if (savepassword.equals(password)){
                        Intent intent=new Intent(HomeActivity.this, ContactActivity.class);
                        startActivity(intent);
                        alertDialog.dismiss();
                    }
                    else {
                        passwordEditText.setError("Enter correct password");
                    }
                }


            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });




//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String name=nameEditText.getText().toString();
//                String location=locationEditText.getText().toString();
//                String phoneNumber=phoneNumberEditText.getText().toString();
//                if (name.isEmpty()){
//                    nameEditText.setError("Enter title");
//                    return;
//                }
//                else if (location.isEmpty()){
//                    locationEditText.setError("Enter title");
//                    return;
//                }
//                else if (phoneNumber.isEmpty()){
//                    phoneNumberEditText.setError("Enter title");
//                    return;
//                }
//
//                long id=dataBaseHelper.insertData(new Notes(name,location,phoneNumber));
//
//
//                if (id != -1){
//                    alertDialog.dismiss();
//                    loadData();
//                    Toast.makeText(context, "Successfully Inserted", Toast.LENGTH_SHORT).show();
//                }else {
//                    alertDialog.dismiss();
//                    Toast.makeText(context, "Failed to Insert", Toast.LENGTH_SHORT).show();
//                }
//
//                //  Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
//            }
//        });

        alertDialog.show();
    }


}
