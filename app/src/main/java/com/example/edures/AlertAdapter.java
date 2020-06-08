package com.example.edures;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.LayoutInflater;
import android.content.Context;
import android.widget.TextView;

import java.util.ArrayList;

public class AlertAdapter extends BaseAdapter {
    Activity context;
    ArrayList<Alert> alerts;
    private static LayoutInflater inflater;

    public AlertAdapter(Activity context, ArrayList<Alert> alerts) {
        this.context = context;
        this.alerts = alerts;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return alerts.size();
    }

    @Override
    public Object getItem(int position) {
        return alerts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        itemView = (itemView == null) ? inflater.inflate(R.layout.list_item,null): itemView;
        TextView title = itemView.findViewById(R.id.title);
        Alert userAlert = alerts.get(position);
        title.setText(userAlert.getTitle());
        return itemView;
    }
}
