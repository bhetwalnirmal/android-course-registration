package com.test.androittest12;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class SelectedCourseListAdapter extends BaseAdapter {
    LayoutInflater inflater;
    Context context;

    public SelectedCourseListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return MainActivity.myCourses.size();
    }

    @Override
    public Object getItem(int i) {
        return MainActivity.myCourses.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view == null) {
            view = inflater.inflate(R.layout.selected_course_row, null);
            viewHolder = new ViewHolder();
            viewHolder.courseName = (TextView) view.findViewById(R.id.courseName);
            viewHolder.courseHours = (TextView) view.findViewById(R.id.courseHour);
            viewHolder.coursePrice = (TextView) view.findViewById(R.id.coursePrice);
            viewHolder.remove = (ImageView) view.findViewById(R.id.remove);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Do you want to remove the course ?");
                builder.setTitle("warning");
                builder.setCancelable(true);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.myCourses.remove(i);
                        notifyDataSetChanged();
                    }
                });
                builder.show();
            }
        });

        viewHolder.courseName.setText(MainActivity.myCourses.get(i).getName());
        viewHolder.coursePrice.setText("" + MainActivity.myCourses.get(i).getFee());
        viewHolder.courseHours.setText("" + MainActivity.myCourses.get(i).getHours());

        return view;
    }

    public static class ViewHolder {
        TextView courseName, courseHours, coursePrice;
        ImageView remove;
    }
}
