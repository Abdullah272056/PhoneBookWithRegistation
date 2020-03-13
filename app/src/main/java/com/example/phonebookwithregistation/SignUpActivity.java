package com.example.phonebookwithregistation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    EditText singUpNameEditText,singUpEmailEditText,singUpPasswordEditText,singUpConfirmPasswordEditText,singUpPhoneEditText;
    Button signUpButton;
    TextView signInTextView;

    String singUpName,singUpEmail,singUpPassword,singUpConfirmPassword,singUpPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //finding start
        singUpNameEditText=findViewById(R.id.signUpNameEditTextId);
        singUpEmailEditText=findViewById(R.id.signUpEmailEditTextId);
        singUpPasswordEditText=findViewById(R.id.signUpPasswordEditTextId);
        singUpConfirmPasswordEditText=findViewById(R.id.signUpConfirmPasswordEditTextId);
        singUpPhoneEditText=findViewById(R.id.signUpPhoneEditTextId);
        signUpButton=findViewById(R.id.signUpButtonId);
        signInTextView=findViewById(R.id.SignInTextViewId);
        //finding end


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (singUpNameEditText.length()<=0){
                    singUpNameEditText.setError("Fill the gap");
                }
                else if (singUpEmailEditText.length()<=0){
                    singUpEmailEditText.setError("Fill the gap");
                }
                else if (singUpPasswordEditText.length()<=0){
                    singUpPasswordEditText.setError("Fill the gap");
                }
                else if (singUpConfirmPasswordEditText.length()<=0){
                    singUpConfirmPasswordEditText.setError("Fill the gap");
                }
                else if (singUpPhoneEditText.length()<=0){
                    singUpPhoneEditText.setError("Fill the gap");
                }

                else {

                    if (singUpPasswordEditText.getText().toString().equals(singUpConfirmPasswordEditText.getText().toString()) ){
                        singUpName=singUpNameEditText.getText().toString();
                        singUpEmail=singUpEmailEditText.getText().toString();
                        singUpPassword=singUpPasswordEditText.getText().toString();
                        singUpConfirmPassword=singUpConfirmPasswordEditText.getText().toString();
                        singUpPhone=singUpPhoneEditText.getText().toString();
                        SharePref sharePref=new SharePref();
                        sharePref.saveDetails(SignUpActivity.this,singUpEmail,singUpPassword);

                        Intent intent =new Intent(SignUpActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();

                    }
                    else {

                        Toast.makeText(SignUpActivity.this, "please match  password with confirm password ", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        signInTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SignUpActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
