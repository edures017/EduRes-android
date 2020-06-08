package com.example.edures;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class activity_user extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    TextView nameView;
    TextView emailView;
    TextView pnoView;
    TextView addressView;
    TextView division;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            mDatabase.child("Users").child(currentUser.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String email = dataSnapshot.child("email").getValue().toString();
                    String address = dataSnapshot.child("address").getValue().toString();
                    String name = dataSnapshot.child("name").getValue().toString();
                    String pno = dataSnapshot.child("pno").getValue().toString();
                    String div = dataSnapshot.child("division").getValue().toString();
                    nameView = findViewById(R.id.name);
                    nameView.setText(name);
                    emailView = findViewById(R.id.email);
                    emailView.setText(email);
                    pnoView = findViewById(R.id.pno);
                    pnoView.setText(pno);
                    addressView = findViewById(R.id.address);
                    addressView.setText(address);
                    division = findViewById(R.id.division);
                    division.setText(div);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.w("TAG", "Failed to read value.");
                }
            });

        }
    }
}
