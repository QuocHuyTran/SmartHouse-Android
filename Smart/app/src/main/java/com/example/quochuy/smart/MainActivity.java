package com.example.quochuy.smart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tvCount,tvDay;
    ListView lvRoom;
    ArrayList<String> Rooms=new ArrayList<>();
    ArrayAdapter arrayAdapter;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference=database.getReference();
    int UsageLiving=0, UsageKitchen=0, UsageGarden=0, UsageBedroom=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setID();
    }

    protected void setID(){
        tvCount=findViewById(R.id.id_tv_count);
        tvDay=findViewById(R.id.id_tv_day);
        lvRoom=findViewById(R.id.idListView);
        setRoom();
    }

    protected void setRoom(){
        Rooms.add("Living room" +"("+UsageLiving+")");
        Rooms.add("Kitchen" +"("+UsageKitchen+")");
        Rooms.add("Garden" +"(" + UsageGarden+")");
        Rooms.add("Bedroom"+"("+UsageBedroom+")");
        arrayAdapter=new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,Rooms);
        lvRoom.setAdapter(arrayAdapter);
        setUsage();
    }

    protected void setUsage(){
        databaseReference.child("lv00").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UsageLiving=Integer.parseInt(dataSnapshot.getValue().toString());
                Rooms.set(0,"Living room" +"("+UsageLiving+")");
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference.child("lv01").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UsageLiving=Integer.parseInt(dataSnapshot.getValue().toString());
                Rooms.set(0,"Living room" +"("+UsageLiving+")");
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference.child("kc00").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UsageKitchen=Integer.parseInt(dataSnapshot.getValue().toString());
                Rooms.set(1,"Kitchen" +"("+UsageKitchen+")");
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference.child("kc01").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UsageKitchen=Integer.parseInt(dataSnapshot.getValue().toString());
                Rooms.set(1,"Kitchen" +"("+UsageKitchen+")");
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference.child("gd00").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UsageGarden=Integer.parseInt(dataSnapshot.getValue().toString());
                Rooms.set(2,"Garden" +"(" + UsageGarden+")");
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference.child("gd01").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UsageGarden=Integer.parseInt(dataSnapshot.getValue().toString());
                Rooms.set(2,"Garden" +"(" + UsageGarden+")");
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference.child("br00").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UsageBedroom=Integer.parseInt(dataSnapshot.getValue().toString());
                Rooms.set(3,"Bedroom"+"("+UsageBedroom+")");
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.setting_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.id_mn_control:{
                Intent intent=new Intent(MainActivity.this,ControlActivity.class);
                startActivity(intent);
            }break;

            case R.id.id_mn_security:{
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }break;

            case R.id.id_mn_notification:{
                Intent intent=new Intent(MainActivity.this,NotificationActivity.class);
                startActivity(intent);
            }break;

            case R.id.id_mn_rate:{

            }break;

            case R.id.id_mn_back:{

            }break;
        }
        return super.onOptionsItemSelected(item);
    }
}
