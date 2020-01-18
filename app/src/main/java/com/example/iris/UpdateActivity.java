package com.example.iris;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

public class UpdateActivity extends AppCompatActivity {

    private EditText mTextView;
    private Button add_btn;
    private Button show_btn;

    //  private String mNum=null;
    private String uID;

    private Firebase mref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
    }
}
