package com.example.phonebookwithregistation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText nameEditText,passwordEditText;

    String name,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.loginButtonId);
        nameEditText=findViewById(R.id.loginNameEditText);
        passwordEditText=findViewById(R.id.loginPasswordEditText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name=nameEditText.getText().toString();
                password=passwordEditText.getText().toString();
                if (name.isEmpty()){
                    nameEditText.setError("Your name is empty");
                }
               else if (password.isEmpty()){
                    passwordEditText.setError("Your password is empty");
                }
                else {
                    Intent intent =new Intent(MainActivity.this,SignUpActivity.class);
                    startActivity(intent);
                }


            }
        });


    }
}
