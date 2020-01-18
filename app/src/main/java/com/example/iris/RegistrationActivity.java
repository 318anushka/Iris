package com.example.iris;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {


    private EditText reg_email;
    private Firebase mRef;
    private EditText reg_password;
    private Button register;
    private TextView login_text_view;
    private FirebaseAuth mAuth;
    private ProgressDialog mDialog;
    String mMail;
    String mPassword;
    //String uID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Firebase.setAndroidContext(this);
        mRef=new Firebase("https://make-her-safe.firebaseio.com/");






        mAuth = FirebaseAuth.getInstance();

        //  uID=FirebaseAuth.getInstance().getCurrentUser().getUid();
        reg_email = findViewById(R.id.email_reg);
        reg_password = findViewById(R.id.password_reg);
        register = findViewById(R.id.reg_btn);
        mDialog = new ProgressDialog(this);
        login_text_view = findViewById(R.id.login_text_view);







        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mMail = reg_email.getText().toString().trim();
                mPassword = reg_password.getText().toString().trim();



                mDialog.setMessage("Processing..");
                mDialog.show();
                if (TextUtils.isEmpty(mMail)) {
                    reg_email.setError("Required Field..");
                    return;
                }
                if (TextUtils.isEmpty(mPassword)) {
                    reg_password.setError("Required Field");
                    return;
                }


                mAuth.createUserWithEmailAndPassword(mMail, mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {  //creating a new firebase user
                        if (task.isSuccessful()) {
                            FirebaseUser my_user = mAuth.getCurrentUser();
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                            Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_LONG).show();
                          //  Log.i("user status","success");
                            mDialog.dismiss();
                        } else {
                            //Log.i("user creation","failure");

                            if(mPassword.length()<8)
                            {
                                Toast.makeText(getApplicationContext(), "Minimum Password length is 8", Toast.LENGTH_SHORT).show();

                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
                            }
                            mDialog.dismiss();
                            //  Log.i("failure","-1");
                        }

                    }
                });
            }
        });




        login_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                Log.i("open login page","successful");
            }
        });
    }
}

