package com.example.iris;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ShowNumberActivity extends AppCompatActivity {

    private DatabaseReference mRef;
    // private Firebase mRef;
    private FirebaseDatabase database;
    private ListView mListView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> mArrayList=new  ArrayList<>();
    String uID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_number);
    }
}
