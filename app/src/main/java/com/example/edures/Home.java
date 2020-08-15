//package com.example.edures;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.iid.FirebaseInstanceId;
//import com.google.firebase.iid.InstanceIdResult;
//
//
//public class Home extends AppCompatActivity {
//    private FirebaseAuth mAuth;
//    Button alerts;
//    Button profile;
//    Button logout;
//    Button attendance;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mAuth = FirebaseAuth.getInstance();
//        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
//            @Override
//            public void onComplete(@NonNull Task<InstanceIdResult> task) {
//                    if (!task.isSuccessful()) {
//                        Log.w("TAG", "getInstanceId failed", task.getException());
//                        return;
//                    }
//                    // Get new Instance ID token
//                    String token = task.getResult().getToken();
//                    SharedPreferences tokenPref = getSharedPreferences("tokenFile", MODE_PRIVATE);
//                    tokenPref.edit().putString("token",token).commit();
//            }
//        });
//        SharedPreferences tokenPref = getSharedPreferences("tokenFile",MODE_PRIVATE);
//        String token = tokenPref.getString("token","DEFAULT");
//        Log.e("TOKEN", token);
//        // Write a message to the database
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null) {
//            String uid = user.getUid();
//            DatabaseReference myRef = database.getReference();
//            myRef.child("Users").child(uid).child("token").setValue(token);
//        } else {
//            Log.e("TAG", "ERROR: USER NOT LOGGED IN");
//        }
//        Toast.makeText(Home.this, token, Toast.LENGTH_SHORT).show();
//        setContentView(R.layout.activity_home);
//        logout = (Button) findViewById(R.id.logout);
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mAuth.signOut();
//                Intent main = new Intent(Home.this, MainActivity.class);
//                startActivity(main);
//            }
//        });
//
//        alerts = (Button) findViewById(R.id.alerts);
//        alerts.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent alerts = new Intent(Home.this, activity_alerts.class);
//                startActivity(alerts);
//            }
//        });
//
//        profile = findViewById(R.id.profile);
//        profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent profile = new Intent(Home.this, activity_user.class);
//                startActivity(profile);
//            }
//        });
//
//        attendance = findViewById(R.id.attendance);
//        attendance.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent attendance = new Intent(Home.this, activity_attendance.class);
//                startActivity(attendance);
//            }
//        });
//    }
//}
