package com.example.quochuy.smart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {

    ListView lvNotice;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayAdapter arrayAdapter;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    Date today=new Date(System.currentTimeMillis());
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("hh:mm dd/MM/yyyy");
    String timeNotice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        lvNotice=findViewById(R.id.idListView);
        arrayAdapter=new ArrayAdapter(NotificationActivity.this,android.R.layout.simple_list_item_1,arrayList);
        lvNotice.setAdapter(arrayAdapter);
        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference();
        databaseReference.child("nt").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                timeNotice=simpleDateFormat.format(today.getTime());
                arrayList.add("("+timeNotice+")"+dataSnapshot.getValue().toString());
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
