package com.example.edures.ui.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.edures.MainActivity;
import com.example.edures.R;
import com.example.edures.activity_attendance;
import com.example.edures.activity_user;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    TextView nameView;
    TextView emailView;
    TextView pnoView;
    TextView addressView;
    TextView division;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
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
                    nameView = root.findViewById(R.id.name);
                    nameView.setText(name);
                    emailView = root.findViewById(R.id.email);
                    emailView.setText(email);
                    pnoView = root.findViewById(R.id.pno);
                    pnoView.setText(pno);
                    addressView = root.findViewById(R.id.address);
                    addressView.setText(address);
                    division = root.findViewById(R.id.division);
                    division.setText(div);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.w("TAG", "Failed to read value.");
                }
            });

        }

        return root;
    }
}
