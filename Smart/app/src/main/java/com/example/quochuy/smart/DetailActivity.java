package com.example.quochuy.smart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    int room;
    ListView lvControl;
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference=firebaseDatabase.getReference();

    ArrayList<Device> livingObject=new ArrayList<>();
    ArrayList<Device> kitchenObject=new ArrayList<>();
    ArrayList<Device> gardenObject=new ArrayList<>();
    ArrayList<Device> bedroodObject=new ArrayList<>();

    ArrayList<String> livingRoom=new ArrayList<>();
    ArrayList<String> kitchen=new ArrayList<>();
    ArrayList<String> garden=new ArrayList<>();
    ArrayList<String> bedroom=new ArrayList<>();

    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        room=getIntent().getIntExtra("roomID",0);
        setID();
        setControlRoom();
    }

    protected void setID(){
        lvControl=findViewById(R.id.idListView);
        setDevice();
    }

    protected void setControlRoom(){
        Intent intent=getIntent();
        room=intent.getIntExtra("roomID",0);
        switch (room){
            case 0:{
                arrayAdapter=new ArrayAdapter(DetailActivity.this,android.R.layout.simple_list_item_1,livingRoom);
            }break;

            case 1:{
                arrayAdapter=new ArrayAdapter(DetailActivity.this,android.R.layout.simple_list_item_1,kitchen);
            }break;

            case 2:{
                arrayAdapter=new ArrayAdapter(DetailActivity.this,android.R.layout.simple_list_item_1,garden);
            }break;

            case 3:{
                arrayAdapter=new ArrayAdapter(DetailActivity.this,android.R.layout.simple_list_item_1,bedroom);
            }break;
        }
        lvControl.setAdapter(arrayAdapter);
        lvControl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (room){
                    case 0:{
                        databaseReference.child(livingObject.get(position).getKey())
                                .setValue(!livingObject.get(position).getTurn());
                    }break;

                    case 1:{
                        databaseReference.child(kitchenObject.get(position).getKey())
                                .setValue(!kitchenObject.get(position).getTurn());
                    }break;

                    case 2:{
                        databaseReference.child(gardenObject.get(position).getKey())
                                .setValue(!gardenObject.get(position).getTurn());
                    }break;

                    case 3:{
                        databaseReference.child(bedroodObject.get(position).getKey())
                                .setValue(!bedroodObject.get(position).getTurn());
                    }break;
                }

            }
        });
    }



    protected void setDevice(){
        livingObject.add(new Device("lv_dt",false,"Đèn trần","lv00"));
        livingObject.add(new Device("lv_dh",false,"Điều hòa","lv01"));

        kitchenObject.add(new Device("kc_dt",false,"Đèn trần","kc00"));
        kitchenObject.add(new Device("kc_vn",false,"Vòi nước","kc01"));

        gardenObject.add(new Device("gd_dt",false,"Đèn trái","gd00"));
        gardenObject.add(new Device("gd_dp",false,"Đèn phải","gd01"));

        bedroodObject.add(new Device("br_dt",false,"Đèn ngủ","br00"));
        setTurnDevice();
        setItemRoom();
    }

    protected void setTurnDevice(){
        if(room==0)
        {
            databaseReference.child("lv_dt").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    livingObject.get(0).setTurn(Boolean.parseBoolean(dataSnapshot.getValue().toString()));
                    Log.e("connecting:",dataSnapshot.getValue().toString());
                    livingRoom.set(0,livingObject.get(0).getNameDevice());
                    arrayAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            databaseReference.child("lv_dh").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    livingObject.get(1).setTurn(Boolean.parseBoolean(dataSnapshot.getValue().toString()));
                    Log.e("connecting:",livingObject.get(1).getNameDevice());
                    livingRoom.set(1,livingObject.get(1).getNameDevice());
                    arrayAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
//            setItemRoom();
        }
        else if(room==1)
        {
            databaseReference.child("kc_dt").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    kitchenObject.get(0).setTurn(Boolean.parseBoolean(dataSnapshot.getValue().toString()));
                    Log.e("connecting:",dataSnapshot.getValue().toString());
                    kitchen.set(0,kitchenObject.get(0).getNameDevice());
                    arrayAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            databaseReference.child("kc_vn").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    kitchenObject.get(1).setTurn(Boolean.parseBoolean(dataSnapshot.getValue().toString()));
                    Log.e("connecting:",dataSnapshot.getValue().toString());
                    kitchen.set(1,kitchenObject.get(1).getNameDevice());
                    arrayAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        else if(room==2)
        {
            databaseReference.child("gd_dt").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    gardenObject.get(0).setTurn(Boolean.parseBoolean(dataSnapshot.getValue().toString()));
                    Log.e("connecting:",dataSnapshot.getValue().toString());
                    garden.set(0,gardenObject.get(0).getNameDevice());
                    arrayAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            databaseReference.child("gd_dp").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    gardenObject.get(1).setTurn(Boolean.parseBoolean(dataSnapshot.getValue().toString()));
                    Log.e("connecting:",dataSnapshot.getValue().toString());
                    garden.set(1,gardenObject.get(1).getNameDevice());
                    arrayAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        else{
            databaseReference.child("br_dt").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    bedroodObject.get(0).setTurn(Boolean.parseBoolean(dataSnapshot.getValue().toString()));
                    bedroom.set(0,bedroodObject.get(0).getNameDevice());
                    arrayAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    protected void setItemRoom(){
        livingRoom.add(livingObject.get(0).getNameDevice());
        livingRoom.add(livingObject.get(1).getNameDevice());

        kitchen.add(kitchenObject.get(0).getNameDevice());
        kitchen.add(kitchenObject.get(1).getNameDevice());

        garden.add(gardenObject.get(0).getNameDevice());
        garden.add(gardenObject.get(1).getNameDevice());

        bedroom.add(bedroodObject.get(0).getNameDevice());
    }
}
