//package com.example.edures;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ListView;
//
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.ChildEventListener;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//
//class AlertL implements java.io.Serializable {
//    String title;
//    String body;
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getBody() {
//        return body;
//    }
//
//    public void setBody(String body) {
//        this.body = body;
//    }
//}
//
//
//public class activity_alerts extends AppCompatActivity {
//    ListView listView;
//    private DatabaseReference mDatabase;
//    private FirebaseAuth mAuth;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_alerts);
//
//        listView = findViewById(R.id.listView);
//        final ArrayList<String> arrayList = new ArrayList<>();
//        final ArrayList<AlertL> alerts = new ArrayList();
////        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayList);
//        final AlertAdapter arrayAdapter;
//        arrayAdapter = new AlertAdapter(this,alerts);
//        listView.setAdapter(arrayAdapter);
//        mAuth = FirebaseAuth.getInstance();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//        mDatabase.child("Users").child(currentUser.getUid()).child("Alerts").addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                AlertL user = dataSnapshot.getValue(AlertL.class);
//                arrayList.add(user.getTitle());
//                alerts.add(user);
//                arrayAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                arrayAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent alert = new Intent(activity_alerts.this, activity_alert_body.class);
//                alert.putExtra("pos", position);
//                alert.putExtra("alertsList",alerts);
//                startActivity(alert);
//            }
//        });
//    }
//
//    ArrayList<String> getAlerts() {
//        mAuth = FirebaseAuth.getInstance();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//        final ArrayList<String> arrayList = new ArrayList<>();
//        if (currentUser != null) {
//            mDatabase.child("Users").child(currentUser.getUid()).child("Alerts").addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    arrayList.add("HEHE");
//                    for(DataSnapshot alert: dataSnapshot.getChildren()) {
//                        String title = alert.child("title").getValue().toString();
//                        arrayList.add(title);
//                        Log.e("TAG",arrayList.get(0));
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//                    Log.w("TAG", "Failed to read value.");
//                }
//            });
//        }
//        return arrayList;
//    }
//
//
//}
