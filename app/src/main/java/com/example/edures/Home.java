package com.example.edures;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;


public class Home extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                    if (!task.isSuccessful()) {
                        Log.w("TAG", "getInstanceId failed", task.getException());
                        return;
                    }

                    // Get new Instance ID token
                    String token = task.getResult().getToken();
//                    Log.e("TOKEN", token);
                    SharedPreferences tokenPref = getSharedPreferences("tokenFile", MODE_PRIVATE);
                    tokenPref.edit().putString("token",token).commit();
                //v.setTok(token);
            }
        });
        SharedPreferences tokenPref = getSharedPreferences("tokenFile",MODE_PRIVATE);
        String token = tokenPref.getString("token","DEFAULT");
//        Log.e("TOK", tok);
        Log.e("TOKEN", token);
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String uid = user.getUid();
            DatabaseReference myRef = database.getReference();
            myRef.child("Users").child(uid).child("token").setValue(token);
        } else {
            Log.e("TAG", "ERROR: USER NOT LOGGED IN");
        }
        Toast.makeText(Home.this, token, Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}
