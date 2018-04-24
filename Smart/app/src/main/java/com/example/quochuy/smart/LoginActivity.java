package com.example.quochuy.smart;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference=database.getReference();

    EditText edtEmail,edtPassword;
    Button btnLogin,btnCreate;
    Boolean openDoor=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setID();
    }

    protected void setID(){
        edtEmail=findViewById(R.id.id_edt_seri);
        edtPassword=findViewById(R.id.id_edt_pass);
        btnLogin=findViewById(R.id.id_btn_open);
        btnCreate=findViewById(R.id.id_btn_set);
        databaseReference.child("door").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                openDoor=Boolean.parseBoolean(dataSnapshot.getValue().toString());
                if(openDoor)
                {
                    btnLogin.setText("Close");
                }
                else {
                    btnLogin.setText("Open");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        setControl();
    }

    protected void setControl(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignInFirebase(edtEmail.getText().toString(),edtPassword.getText().toString());
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,CreateActivity.class);
                startActivity(intent);
            }
        });
    }


    protected void SignInFirebase(String username,String password){
        firebaseAuth.signInWithEmailAndPassword(username,password).
                addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.e("Sign In:",task.isSuccessful()+"");
                        if(task.isSuccessful())
                        {
                            Toast.makeText(LoginActivity.this, "Open door", Toast.LENGTH_SHORT).show();
                            databaseReference.child("door").setValue(!openDoor);
                            if(openDoor)
                            {
                                btnLogin.setText("Close");
                            }
                            else {
                                btnLogin.setText("Open");
                            }
//                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Your Account doesn't active", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
