package com.example.iris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button button1 = findViewById(R.id.activateservice);
        Button button2 = findViewById(R.id.stayconnected);
        Button button3 = findViewById(R.id.updatecontacts);
        Button button4 = findViewById(R.id.tips);
        Button button5 = findViewById(R.id.help);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(HomeActivity.this , ActivateService.class);
                startActivity(intent1);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent2 = new Intent(HomeActivity.this , StayConnected.class);
                startActivity(intent2);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent new_intent=new Intent(HomeActivity.this,UpdateActivity.class);
                startActivity(new_intent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent new_intent=new Intent(HomeActivity.this,TipsActivity.class);
                startActivity(new_intent);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent new_intent=new Intent(HomeActivity.this,HelpActivity.class);
                startActivity(new_intent);
            }
        });



    }

}
