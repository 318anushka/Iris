package com.example.iris;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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


        mListView=findViewById(R.id.myList);
        database=FirebaseDatabase.getInstance();
        // Firebase.setAndroidContext(this);

        //  mRef=new Firebase ("https://make-her-safe.firebaseio.com/user/uID");


        uID= FirebaseAuth.getInstance().getCurrentUser().getUid();
        mRef=database.getReference().child(uID);



        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mArrayList);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snap:dataSnapshot.getChildren()) {
                    //  String key=dataSnapshot.getKey();
                    String value = snap.getValue(String.class);

                    //Log.i("This","value");
                    // for(String  ans :value.getmNum()){
                    //  System.out.println(ans);
                    //   mArrayList=value.getmNum();
                    mArrayList.add(value);


                }
                mListView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
