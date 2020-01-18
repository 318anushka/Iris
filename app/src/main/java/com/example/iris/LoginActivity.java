package com.example.iris;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private Button login_btn;
    private TextView mTextView;

    private String mMAil;
    private String mPasssword;

    private EditText mMAilView;
    private EditText mPAssView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMAilView=findViewById(R.id.email_login);
        mPAssView=findViewById(R.id.password_login);

        mMAil=mMAilView.getText().toString().trim();
        mPasssword=mPAssView.getText().toString().trim();



    }
}
