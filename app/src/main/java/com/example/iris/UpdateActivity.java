package com.example.iris;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

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

        mTextView=findViewById(R.id.num_text);
        add_btn=findViewById(R.id.add_btn);
        show_btn=findViewById(R.id.show_btn);


        Firebase.setAndroidContext(this);

        mref=new Firebase("https://iris-8e542.firebaseio.com/");
        uID= FirebaseAuth.getInstance().getCurrentUser().getUid();
    }
}
