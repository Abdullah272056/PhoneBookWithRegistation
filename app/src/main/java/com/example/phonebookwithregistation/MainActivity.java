package com.example.phonebookwithregistation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText emailEditText,passwordEditText;
    CheckBox rememberCheckBox;
    TextView createAccountTextView;

    String email,password;
    SharePref sharePref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Login page");


        //finding start
        button=findViewById(R.id.loginButtonId);
        emailEditText=findViewById(R.id.loginEmailEditText);
        passwordEditText=findViewById(R.id.loginPasswordEditText);
        rememberCheckBox=findViewById(R.id.rememberCheckBoxId);
        createAccountTextView=findViewById(R.id.createAccountTextViewId);

        //finding end


        sharePref=new SharePref();

        String emailValue= sharePref.loadRememberEmail(MainActivity.this);
        String passwordValue= sharePref.loadRememberPassword(MainActivity.this);


        if (!emailValue.isEmpty() && !passwordValue.isEmpty()){
            emailEditText.setText(emailValue);
            passwordEditText.setText(passwordValue);
            if (emailEditText.getText().toString().equals(emailValue) && passwordEditText.getText().toString().equals(passwordValue)){
                Intent intent =new Intent(MainActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }
        else {
           // Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show();
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=emailEditText.getText().toString();
                password=passwordEditText.getText().toString();

                if (email.isEmpty()){
                    emailEditText.setError("Your name is empty");
                }
               else if (password.isEmpty()){
                    passwordEditText.setError("Your password is empty");
                }
                else {
                    sharePref=new SharePref();
                    String emailValue= sharePref.loadEmail(MainActivity.this);
                    String passwordValue= sharePref.loadPassword(MainActivity.this);

                    if (rememberCheckBox.isChecked()){
                        sharePref.rememberData(MainActivity.this,email,password);
                    }

                    if (email.equals(emailValue) && password.equals(passwordValue)){
                        Intent intent =new Intent(MainActivity.this,HomeActivity.class);
                        startActivity(intent);
                        finish();

                    }
                    else {
                        Toast.makeText(MainActivity.this, "Can not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        createAccountTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }


}
