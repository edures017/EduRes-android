package com.example.edures;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class activity_alert_body extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_body);
        ArrayList<Alert> alerts = (ArrayList<Alert>) getIntent().getSerializableExtra("alertsList");
        Alert alert = alerts.get(getIntent().getIntExtra("pos",9));
        TextView title = findViewById(R.id.title);
        TextView body = findViewById(R.id.body);
        title.setText(alert.getTitle());
        body.setText(alert.getBody());
    }
}
