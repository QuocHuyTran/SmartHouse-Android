package com.example.quochuy.smart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ControlActivity extends AppCompatActivity {
    ListView lvRoom;
    ArrayList<String> Rooms=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        setID();
    }

    protected void setRoom(){
        Rooms.add("Living room");
        Rooms.add("Kitchen");
        Rooms.add("Garden");
        Rooms.add("Room 1");
    }

    protected void setID(){
        setRoom();
        lvRoom=findViewById(R.id.idListView);
        ArrayAdapter arrayAdapter=new ArrayAdapter(ControlActivity.this,android.R.layout.simple_list_item_1,Rooms);
        lvRoom.setAdapter(arrayAdapter);
        lvRoom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(ControlActivity.this,DetailActivity.class);
                intent.putExtra("roomID",position);
                startActivity(intent);
            }
        });
    }


}
